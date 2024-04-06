package com.ssafy.tipsy.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuth {

    Role role;
    String nickname;
}
