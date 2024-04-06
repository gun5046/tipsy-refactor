package com.ssafy.tipsy.domain;

import lombok.Builder;

public class ReceivedRequest {
    private final Long fromUserId;
    private final String fromUserNickname;

    private final String receivedTime;

    @Builder
    public ReceivedRequest(Long fromUserId, String fromUserNickname, String receivedTime) {
        this.fromUserId = fromUserId;
        this.fromUserNickname = fromUserNickname;
        this.receivedTime = receivedTime;
    }
}
