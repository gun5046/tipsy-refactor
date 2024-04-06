package com.ssafy.tipsy.domain;

import com.ssafy.tipsy.common.infrastructure.TestPasswordEncoder;
import com.ssafy.tipsy.controller.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    void SignUpRequest_객체로_User를_생성할_수_있다(){
        //given
        SignUpRequest signUpRequest = new SignUpRequest("test","남자","testId","testPwd","0101","//image");
        //when
        User user = User.signUp(signUpRequest);
        //then
        assertThat(user).isNotNull();
        assertThat(user.getLoginInfo().getLoginId()).isEqualTo("testId");
        assertThat(user.getInfo().getImage()).isEqualTo("//image");
        assertThat(user.getInfo().getNickname()).isEqualTo("test");
        assertThat(user.getInfo().getBirth()).isEqualTo("0101");
        assertThat(user.getInfo().getGender()).isEqualTo("남자");
    }

    @Test
    void SignUpRequest_객체로_User를_Update할_수_있다(){
        //given
        SignUpRequest signUpRequest = new SignUpRequest("test","남자","testId","testPwd","0101","//image");

        User user = User.builder()
                .id(1L)
                .loginInfo(LoginInfo.builder().build())
                .info(Info.builder().build())
                .build();
        //when
        user = user.update(signUpRequest);
        //then
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getInfo().getImage()).isEqualTo("//image");
        assertThat(user.getInfo().getNickname()).isEqualTo("test");
        assertThat(user.getInfo().getBirth()).isEqualTo("0101");
        assertThat(user.getInfo().getGender()).isEqualTo("남자");
    }

    @Test
    void Plain패스워드를_받아_Encrypt하고_update_할_수_있다(){
        //given
        String loginId="test";
        String plainPwd="test";
        TestPasswordEncoder passwordEncoder = new TestPasswordEncoder();
        String encPwd = passwordEncoder.encode(plainPwd);
        User user = User.builder()
                .id(1L)
                .loginInfo(LoginInfo.builder()
                        .loginId(loginId)
                        .pwd(plainPwd)
                        .build())
                .info(Info.builder()
                        .birth("0101")
                        .image("//image")
                        .gender("gender")
                        .nickname("닉네임")
                        .build())
                .build();
        //when
        User result = user.changePwd(encPwd);
        //then
        assertThat(result.getLoginInfo().getPwd()).isEqualTo(encPwd);
    }
}
