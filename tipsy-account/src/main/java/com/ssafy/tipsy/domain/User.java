package com.ssafy.tipsy.domain;

import com.ssafy.tipsy.common.util.PasswordEncoderHolder;
import com.ssafy.tipsy.controller.request.SignUpRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private final Long id;
    private final Info info;
    private final LoginInfo loginInfo;

    @Builder
    public User(Long id, LoginInfo loginInfo, Info info) {
        this.id = id;
        this.info = info;
        this.loginInfo=loginInfo;
    }

    public static User signUp(SignUpRequest signUpRequest){
        return User.builder()
                .info(Info.builder()
                        .birth(signUpRequest.getBirth())
                        .gender(signUpRequest.getGender())
                        .image(signUpRequest.getImage())
                        .nickname(signUpRequest.getNickname())
                        .build())
                .loginInfo(LoginInfo.builder()
                        .loginId(signUpRequest.getLoginId())
                        .pwd(signUpRequest.getPwd())
                        .build())
                .build();
    }

    public User encryptPwd(String pwd, PasswordEncoderHolder passwordEncoderHolder) {
        return User.builder()
                .info(info)
                .loginInfo(loginInfo.encryptPwd(pwd,passwordEncoderHolder))
                .build();
    }

    public User update(SignUpRequest signUpRequest){
        return User.builder()
                .id(id)
                .loginInfo(loginInfo)
                .info(Info.builder()
                        .birth(signUpRequest.getBirth())
                        .gender(signUpRequest.getGender())
                        .image(signUpRequest.getImage())
                        .nickname(signUpRequest.getNickname())
                        .build())
                .build();
    }
}
