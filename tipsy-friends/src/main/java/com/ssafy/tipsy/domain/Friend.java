package com.ssafy.tipsy.domain;

import lombok.Builder;

import java.util.List;

public class Friend {
    private final OwnerInfo ownerInfo;
    private final List<FriendInfo> friendsList;

    private final List<ReceivedRequest> receivedRequestList;
    private final List<SentRequest> sentRequestsList;

    @Builder
    public Friend(OwnerInfo ownerInfo, List<FriendInfo> friendsList, List<ReceivedRequest> receivedRequestList, List<SentRequest> sentRequestsList) {
        this.ownerInfo = ownerInfo;
        this.friendsList = friendsList;
        this.receivedRequestList = receivedRequestList;
        this.sentRequestsList = sentRequestsList;
    }
}
