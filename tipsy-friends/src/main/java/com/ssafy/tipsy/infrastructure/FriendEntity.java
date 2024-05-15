package com.ssafy.tipsy.infrastructure;

import com.ssafy.tipsy.domain.UserInfo;
import com.ssafy.tipsy.domain.ReceivedInvitation;
import com.ssafy.tipsy.domain.SentInvitation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Table(name="friends")
@NoArgsConstructor
@Setter
public class FriendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_info_id")
    private UserInfoEntity ownerInfo;

    @OneToMany(mappedBy = "" ,fetch = FetchType.LAZY)
    private List<UserInfoEntity> friendsList;

    private List<ReceivedInvitationEntity> receivedInvitation;
    private List<SentInvitationEntity> sentInvitation;


}
