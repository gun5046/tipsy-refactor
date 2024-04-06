package com.ssafy.tipsy.service.port;

import com.ssafy.tipsy.domain.User;

import java.util.Optional;

public interface LoginService {
    Optional<User> login(String id, String password);

    User regist(User user);
}
