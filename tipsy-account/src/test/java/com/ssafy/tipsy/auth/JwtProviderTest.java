package com.ssafy.tipsy.auth;


import com.ssafy.tipsy.auth.model.AuthFail;
import com.ssafy.tipsy.auth.model.Role;
import com.ssafy.tipsy.auth.model.UserAuth;
import com.ssafy.tipsy.common.infrastructure.SystemClockHolder;
import com.ssafy.tipsy.common.infrastructure.TestClockHolder;
import com.ssafy.tipsy.common.util.ClockHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@TestPropertySource("classpath:test-application.properties")
public class JwtProviderTest {


    String salt="TestTestTestTestTestTestTestTestTestTestTestTestTestTest";
    Key secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));

    Date date = new Date();

    private final long accessTokenValidTime = 30 * 10 * 1000L;

    private JwtProvider jwtProvider = new JwtProvider(new TestClockHolder(60L));
    @BeforeEach
    void init(){
        jwtProvider.salt=salt;
        jwtProvider.secretKey = secretKey;
    }

    @Test
    void Nickname과_Role으로_JWTToken을_만들_수_있다(){
        //given
        String nickname = "TestName";
        Role role=Role.USER;

        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("role",role);
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT-ACCESSTOKEN");
        header.put("alg","HS256");

        String jwt = Jwts.builder().setClaims(claims).setIssuedAt(new Date(60L))
                .setExpiration(new Date(60L + accessTokenValidTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        //when
        String token = jwtProvider.createJwtToken(nickname,role);

        //then
        assertThat(token).isNotNull();
        assertThat(token).startsWith("eyJhbGciOiJIUzI1NiJ9");
        assertThat(token).isEqualTo(jwt);
    }

    @Test
    void 유효한_Token을_검증하면_Null을_리턴한다(){
        //given
        String nickname = "닉네임";
        Role role = Role.USER;
        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("role",role);
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT-ACCESSTOKEN");
        header.put("alg","HS256");

        String token =  Jwts.builder().setClaims(claims).setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+accessTokenValidTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();


        //when
        AuthFail fail = jwtProvider.validateToken(token);

        //then
        assertThat(fail).isNull();
    }

    @Test
    void Token값이_null_이면_AuthFail_WRONG을_리턴한다(){
        //given
        String nullToken = null;

        //when
        AuthFail fail = jwtProvider.validateToken(nullToken);
        //then

        assertThat(fail).isEqualTo(AuthFail.WRONG);
    }

    @Test
    void 만료된_토큰이면__AuthFail_EXPIRED를_리턴한다(){
        //given
        String nickname= "test";
        Role role = Role.USER;
        String expiredToken = jwtProvider.createJwtToken(nickname, role);

        //when
        AuthFail fail = jwtProvider.validateToken(expiredToken);

        //then

        assertThat(fail).isEqualTo(AuthFail.EXPIRED);
    }
    @Test
    void Jwt형식이_아닌_토큰이면_AuthFail_WRONG을_리턴한다(){
        //given
        String malformedToken = "Something";

        //when
        AuthFail fail = jwtProvider.validateToken(malformedToken);

        //then

        assertThat(fail).isEqualTo(AuthFail.WRONG);

    }
    @Test
    void 조작된Jwt_토큰이면_AuthFail_INVALID를_리턴한다(){
        //given
        String nickname = "닉네임";
        Role role = Role.USER;
        String notSignaturedToken = jwtProvider.createJwtToken(nickname, role);
        notSignaturedToken+="asd";
        //when
        AuthFail fail = jwtProvider.validateToken(notSignaturedToken);

        //then
        assertThat(fail).isEqualTo(AuthFail.INVALID);

    }

    @Test
    void 유효한_Token으로_사용자_인증_객체를_가져올_수_있다(){
        //given
        String nickname = "닉네임";
        Role role = Role.USER;
        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("role",role);
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT-ACCESSTOKEN");
        header.put("alg","HS256");

        String token =  Jwts.builder().setClaims(claims).setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+accessTokenValidTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        //when
        Authentication authentication = jwtProvider.getAuthentication(token);

        //then

        assertThat(authentication.getName()).isEqualTo(nickname);
        assertThat(authentication.getAuthorities()).isNotNull();
    }
}