package com.ssafy.tipsy.infrastructure.port;

import com.ssafy.tipsy.domain.Friend;
import com.ssafy.tipsy.domain.ReceivedInvitation;
import com.ssafy.tipsy.domain.SentInvitation;

import java.util.List;

public interface FriendRepository {
    public List<Friend> findListByOwnerId(Long ownerId);
    public SentInvitation saveSentInvitation(Boolean status);

    public Void saveReceivedInvitation(Boolean status);


    public List<ReceivedInvitation> getInvitationList();
}
