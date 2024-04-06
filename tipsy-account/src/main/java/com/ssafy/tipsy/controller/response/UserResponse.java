package com.ssafy.tipsy.controller.response;

import com.ssafy.tipsy.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    public Long id;
    public String nickname;
    public String image;
    public String birth;
    public String gender;

    public static UserResponse from(User user){
        return UserResponse.builder()
                .id(user.getId())
                .nickname(user.getInfo().nickname)
                .image(user.getInfo().image)
                .birth(user.getInfo().birth)
                .gender(user.getInfo().gender)
                .build();
    }
}
