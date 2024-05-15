package com.ssafy.tipsy.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfo {
    private final Long id;
    private final String nickname;

    @Builder
    public UserInfo(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
