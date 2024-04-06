package com.ssafy.tipsy.infrastructure.port;

import com.ssafy.tipsy.domain.User;

import java.util.Optional;

public interface UserRepository {

    User update(User user);

    Optional<User> findByNickname(String nickname);

    Optional<User> findById(Long id);
}
