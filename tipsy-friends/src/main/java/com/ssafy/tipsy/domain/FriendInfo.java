package com.ssafy.tipsy.domain;

import lombok.Builder;

public class FriendInfo {
    private final Long id;
    private final String nickname;

    @Builder
    public FriendInfo(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
