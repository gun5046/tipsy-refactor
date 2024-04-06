package com.ssafy.tipsy.service;

import com.ssafy.tipsy.common.util.PasswordEncoderHolder;
import com.ssafy.tipsy.service.port.LoginService;
import com.ssafy.tipsy.domain.User;
import com.ssafy.tipsy.infrastructure.port.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoderHolder passwordEncoderHolder;
    @Override
    public Optional<User> login(String id, String password) {
        return loginRepository.findByLoginIdAndPassword(id,password);
    }

    @Override
    public User regist(User user) {
        user.getLoginInfo().validateId();
        String plainPwd = user.getLoginInfo().getPwd();
        String encPwd = passwordEncoderHolder.encode(plainPwd);
        User registableUser = user.changePwd(encPwd);
        return loginRepository.save(registableUser);
    }
}
