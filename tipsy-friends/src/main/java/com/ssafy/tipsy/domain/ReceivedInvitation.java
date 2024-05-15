package com.ssafy.tipsy.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReceivedInvitation {

    private final Long fromUserId;
    private final String fromUserNickname;

    private final String receivedTime;

    private final Boolean status;

    @Builder
    public ReceivedInvitation(Long fromUserId, String fromUserNickname, String receivedTime, Boolean status) {
        this.fromUserId = fromUserId;
        this.fromUserNickname = fromUserNickname;
        this.receivedTime = receivedTime;
        this.status = status;
    }
}
