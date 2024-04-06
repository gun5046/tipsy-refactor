package com.ssafy.tipsy.controller.response;

import com.ssafy.tipsy.domain.Info;
import com.ssafy.tipsy.domain.LoginInfo;
import com.ssafy.tipsy.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginResponseTest {


    @Test
    void User객체를_LoginResponse로_변경할_수_있다(){
        //given
        User user = User.builder()
                .id(1L)
                .loginInfo(LoginInfo.builder()
                        .loginId("test")
                        .pwd("test")
                        .build())
                .info(Info.builder()
                        .birth("0101")
                        .image("//image")
                        .gender("gender")
                        .nickname("닉네임")
                        .build())
                .build();
        //when
        LoginResponse loginResponse = LoginResponse.from(user);

        //then
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getId()).isEqualTo(1L);
        assertThat(loginResponse.getLoginId()).isEqualTo("test");
        assertThat(loginResponse.getImage()).isEqualTo("//image");
        assertThat(loginResponse.getNickname()).isEqualTo("닉네임");
        assertThat(loginResponse.getBirth()).isEqualTo("0101");
        assertThat(loginResponse.getGender()).isEqualTo("gender");
    }
}
