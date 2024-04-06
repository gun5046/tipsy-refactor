package com.ssafy.tipsy.infrastructure.port;

import com.ssafy.tipsy.domain.User;

import java.util.Optional;

public interface LoginRepository {
    User save(User user);

    Optional<User> findByLoginIdAndPassword(String id, String password);
}
