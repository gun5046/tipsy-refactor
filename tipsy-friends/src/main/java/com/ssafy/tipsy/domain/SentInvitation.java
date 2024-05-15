package com.ssafy.tipsy.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SentInvitation {
    private final Long toUserId;
    private final String toUserNickname;

    private final String sentTime;

    private final Boolean status;

    @Builder
    public SentInvitation(Long toUserId, String toUserNickname, String sentTime, Boolean status) {
        this.toUserId = toUserId;
        this.toUserNickname = toUserNickname;
        this.sentTime = sentTime;
        this.status = status;
    }
}
