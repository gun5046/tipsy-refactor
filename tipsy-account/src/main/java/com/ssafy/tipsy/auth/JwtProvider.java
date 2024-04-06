package com.ssafy.tipsy.auth;


import com.ssafy.tipsy.auth.model.AuthFail;
import com.ssafy.tipsy.auth.model.Role;
import com.ssafy.tipsy.auth.model.UserAuth;
import com.ssafy.tipsy.common.infrastructure.SystemClockHolder;
import com.ssafy.tipsy.common.util.ClockHolder;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${JWT-SECRET}")
    private String salt;

    private Key secretKey;
    private final long accessTokenValidTime = 30 * 10 * 1000L;

    private final ClockHolder clockHolder;

    public JwtProvider(String salt,Key secretKey, ClockHolder clockHolder){
        this.salt=salt;
        this.secretKey =secretKey;
        this.clockHolder= clockHolder;
    }

    @PostConstruct
    protected void init(){
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createJwtToken(String nickname, Role role) {
        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("role",role);
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT-ACCESSTOKEN");
        header.put("alg","HS256");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(clockHolder.getDate())
                .setExpiration(new Date(clockHolder.getTime() + accessTokenValidTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public  Authentication getAuthentication(String token){
        UserDetails userDetails = new PrincipalDetails(new UserAuth(getRole(token), getNickname(token)));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 유저 account 획득
    private String getNickname(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰 검증
    public AuthFail validateToken(String token){
        try {
            if(token == null){
                return AuthFail.WRONG;
            }
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        }catch (ExpiredJwtException e){
            return AuthFail.EXPIRED;
        }catch (MalformedJwtException e){
            return AuthFail.WRONG;
        }catch (SignatureException e){
            return AuthFail.INVALID;
        }
        return null;
    }

    private Role getRole(String token){
        return Role.valueOf(Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role").toString());
    }
}