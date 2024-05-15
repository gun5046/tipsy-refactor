package com.ssafy.tipsy.service;

import com.ssafy.tipsy.domain.SentInvitation;
import com.ssafy.tipsy.infrastructure.port.FriendRepository;
import com.ssafy.tipsy.service.dto.FriendDto;
import com.ssafy.tipsy.service.dto.InvitationDto;
import com.ssafy.tipsy.service.port.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    @Override
    public List<FriendDto> getList(Long ownerId) {
        return null;
    }

    @Override
    public SentInvitation sendInvitation(InvitationDto invitationDto) {
        return null;
    }

    @Override
    public Void acceptInvitation() {
        return null;
    }

    @Override
    public Void denyInvitation() {
        return null;
    }

    @Override
    public List<InvitationDto> getInvitationList() {
        return null;
    }
}
