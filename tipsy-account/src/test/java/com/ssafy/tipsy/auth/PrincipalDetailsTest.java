package com.ssafy.tipsy.auth;

import com.ssafy.tipsy.auth.model.Role;
import com.ssafy.tipsy.auth.model.UserAuth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class PrincipalDetailsTest {

    private PrincipalDetails principalDetails;

    @BeforeEach
    void init(){
        principalDetails = new PrincipalDetails(new UserAuth(Role.USER,"닉네임"));
    }

    @Test
    void 권한_목록을_가져올_수_있다(){
        //given
        //when
        //then
        assertThat(principalDetails.getAuthorities()).isNotNull();
        assertThat(principalDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .toList().get(0)).isEqualTo("USER");
    }

    @Test
    void 유저_닉네임을_가져올_수_있다(){
        //given
        //when
        //then
        assertThat(principalDetails.getUsername()).isEqualTo("닉네임");
    }
}