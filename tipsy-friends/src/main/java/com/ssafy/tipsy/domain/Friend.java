package com.ssafy.tipsy.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Friend {
    private final Long id;
    private final UserInfo ownerInfo;
    private final List<UserInfo> friendsList;

    private final List<ReceivedInvitation> receivedInvitationList;
    private final List<SentInvitation> sentInvitationList;

    @Builder
    public Friend(Long id, UserInfo ownerInfo, List<UserInfo> friendsList, List<ReceivedInvitation> receivedInvitationList, List<SentInvitation> sentRequestsList) {
        this.id = id;
        this.ownerInfo = ownerInfo;
        this.friendsList = friendsList;
        this.receivedInvitationList = receivedInvitationList;
        this.sentInvitationList = sentRequestsList;
    }
}
