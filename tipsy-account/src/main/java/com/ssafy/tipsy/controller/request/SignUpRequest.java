package com.ssafy.tipsy.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    public Long id;
    public String nickname;
    public String gender;
    public String loginId;
    public String pwd;
    public String birth;
    public String image;

    public SignUpRequest(String nickname, String gender, String loginId, String pwd, String birth, String image) {
        this.nickname = nickname;
        this.gender = gender;
        this.loginId = loginId;
        this.pwd = pwd;
        this.birth = birth;
        this.image = image;
    }
}
