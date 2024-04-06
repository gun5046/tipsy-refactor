package com.ssafy.tipsy.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Info {
    public String nickname;
    public String image;
    public String birth;
    public String gender;

    @Builder
    public Info(String nickname, String image, String birth, String gender) {
        this.nickname = nickname;
        this.image = image;
        this.birth = birth;
        this.gender = gender;
    }
}
