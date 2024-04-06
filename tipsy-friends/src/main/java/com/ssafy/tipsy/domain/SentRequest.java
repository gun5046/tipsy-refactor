package com.ssafy.tipsy.domain;

import lombok.Builder;

public class SentRequest {
    private final Long toUserId;
    private final String toUserNickname;

    private final String sentTime;

    @Builder
    public SentRequest(Long toUserId, String toUserNickname, String sentTime) {
        this.toUserId = toUserId;
        this.toUserNickname = toUserNickname;
        this.sentTime = sentTime;
    }
}
