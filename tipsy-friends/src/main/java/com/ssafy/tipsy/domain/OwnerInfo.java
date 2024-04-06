package com.ssafy.tipsy.domain;

import lombok.Builder;

public class OwnerInfo {
    private final Long id;
    private final String nickname;
    @Builder
    public OwnerInfo(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
