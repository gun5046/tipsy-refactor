package com.ssafy.tipsy.service.port;

import com.ssafy.tipsy.controller.request.SignUpRequest;
import com.ssafy.tipsy.domain.User;

public interface UserService {

    User getById(Long uid);

    Boolean checkNickname(String nickname);

    User update(SignUpRequest signUpRequest);
}
