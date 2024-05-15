package com.ssafy.tipsy.infrastructure;

import com.ssafy.tipsy.domain.Friend;
import com.ssafy.tipsy.domain.ReceivedInvitation;
import com.ssafy.tipsy.domain.SentInvitation;
import com.ssafy.tipsy.infrastructure.port.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {

    private final FriendJpaRepository friendJpaRepository;
    @Override
    public List<Friend> findListByOwnerId(Long ownerId) {
        return friendJpaRepository.findByOwnerId(ownerId);
    }

    @Override
    public SentInvitation saveSentInvitation(Boolean status) {
        return null;
    }

    @Override
    public Void saveReceivedInvitation(Boolean status) {
        return null;
    }

    @Override
    public List<ReceivedInvitation> getInvitationList() {
        return null;
    }
}
