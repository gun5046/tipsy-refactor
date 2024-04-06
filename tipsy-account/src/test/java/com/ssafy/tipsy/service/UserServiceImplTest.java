package com.ssafy.tipsy.service;


import com.ssafy.tipsy.common.exception.ResourceNotFoundException;
import com.ssafy.tipsy.controller.request.SignUpRequest;
import com.ssafy.tipsy.domain.User;
import com.ssafy.tipsy.infrastructure.port.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest{

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void 존재하는_user의_식별자_값으로_User정보를_가져올_수_있다(){
        //given
        User user = User.builder()
                .id(1L)
                .build();
        BDDMockito.given(userRepository.findById(1L)).willReturn(Optional.of(user));
        //when
        User result = userService.getById(1L);
        //then

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
    }
    @Test
    void 존재하지_않는_user의_식별자_값으로_User정보를_조회하면_에러를_던진다(){
        //given

        BDDMockito.given(userRepository.findById(1L)).willReturn(Optional.empty());
        //when
        //then
        assertThrows(ResourceNotFoundException.class,()->userService.getById(1L));
    }

    @Test
    void 이미_존재하는_nickname을_조회하면_true를_리턴한다(){
        //given
        String nickname="existedNickname";
        User user = User.builder().build();
        BDDMockito.given(userRepository.findByNickname(nickname)).willReturn(Optional.of(user));
        //when
        Boolean result = userService.checkNickname(nickname);
        //then
        assertThat(result).isTrue();
    }

    @Test
    void 존재하지_않는_nickname을_조회하면_false를_리턴한다(){
        //given
        String nickname="notExistedNickname";
        BDDMockito.given(userRepository.findByNickname(nickname)).willReturn(Optional.empty());
        //when
        Boolean result = userService.checkNickname(nickname);
        //then
        assertThat(result).isFalse();
    }

    @Test
    void SignUpRequest로_User를_업데이트할_수_있다(){
        // given
        Long userId = 1L;
        User existingUser = User.builder()
                .id(userId)
                .build();

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setId(1L);

        User updatedUser = User.builder()
                .id(2L)
                .build();
        BDDMockito.given(userRepository.findById(userId)).willReturn(Optional.of(existingUser));
        BDDMockito.given(userRepository.update(any())).willReturn(updatedUser);

        //when
        User result = userService.update(signUpRequest);

        //then
        assertThat(result).isNotNull();
       assertThat(result).isEqualTo(updatedUser);
    }

    @Test
    void 존재하지_않는_User를_업데이트하려하면_에러를_던진다(){
        // given
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setId(1L);
        BDDMockito.given(userRepository.findById(any())).willReturn(Optional.empty());
        //when
        //then
        assertThrows(ResourceNotFoundException.class,()->userService.update(signUpRequest));
    }
}
