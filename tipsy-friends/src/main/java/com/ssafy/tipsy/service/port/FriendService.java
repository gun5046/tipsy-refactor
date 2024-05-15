package com.ssafy.tipsy.service.port;

import com.ssafy.tipsy.domain.SentInvitation;
import com.ssafy.tipsy.service.dto.FriendDto;
import com.ssafy.tipsy.service.dto.InvitationDto;

import java.util.List;

public interface FriendService {
    public List<FriendDto> getList(Long ownerId);
    public SentInvitation sendInvitation(InvitationDto invitationDto);

    public Void acceptInvitation();

    public Void denyInvitation();

    public List<InvitationDto> getInvitationList();
}
