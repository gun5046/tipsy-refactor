package com.ssafy.tipsy.infrastructure;

import com.ssafy.tipsy.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendJpaRepository extends JpaRepository<Friend, Long> {
}
