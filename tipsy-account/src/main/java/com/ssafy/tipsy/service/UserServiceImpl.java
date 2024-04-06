package com.ssafy.tipsy.service;

import com.ssafy.tipsy.common.exception.ResourceNotFoundException;
import com.ssafy.tipsy.controller.request.SignUpRequest;

import com.ssafy.tipsy.infrastructure.port.UserRepository;
import com.ssafy.tipsy.service.port.UserService;
import com.ssafy.tipsy.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(this.getClass().getName(),"(Long)id")
        );
    }

    @Override
    public Boolean checkNickname(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    @Override
    public User update(SignUpRequest signUpRequest) {
        User user = userRepository.findById(signUpRequest.getId()).orElseThrow(
                ()->new ResourceNotFoundException(this.getClass().getName(),"(Long)id"));
        return userRepository.update(user.update(signUpRequest));
    }
}
