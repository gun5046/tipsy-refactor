package com.ssafy.tipsy.controller.response;

import com.ssafy.tipsy.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    public Long id;
    public String loginId;
    public String nickname;
    public String image;
    public String birth;
    public String gender;


    public static LoginResponse from(User user){
        return LoginResponse.builder()
                .id(user.getId())
                .loginId(user.getLoginInfo().getLoginId())
                .nickname(user.getInfo().nickname)
                .image(user.getInfo().image)
                .birth(user.getInfo().birth)
                .gender(user.getInfo().gender)
                .build();
    }
}
