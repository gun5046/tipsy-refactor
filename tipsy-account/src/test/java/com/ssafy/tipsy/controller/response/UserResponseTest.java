package com.ssafy.tipsy.controller.response;


import com.ssafy.tipsy.domain.Info;
import com.ssafy.tipsy.domain.LoginInfo;
import com.ssafy.tipsy.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseTest {


    @Test
    void User객체를_UserResponse로_변경할_수_있다(){
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
        UserResponse userResponse = UserResponse.from(user);
        //then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getId()).isEqualTo(1L);
        assertThat(userResponse.getImage()).isEqualTo("//image");
        assertThat(userResponse.getNickname()).isEqualTo("닉네임");
        assertThat(userResponse.getBirth()).isEqualTo("0101");
        assertThat(userResponse.getGender()).isEqualTo("gender");
    }
}
