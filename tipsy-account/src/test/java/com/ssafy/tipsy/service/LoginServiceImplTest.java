package com.ssafy.tipsy.service;


import com.ssafy.tipsy.common.util.PasswordEncoderHolder;
import com.ssafy.tipsy.domain.LoginInfo;
import com.ssafy.tipsy.domain.User;
import com.ssafy.tipsy.infrastructure.port.LoginRepository;
import com.ssafy.tipsy.service.port.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest{

    @Mock
    private PasswordEncoderHolder passwordEncoder;
    @Mock
    private LoginRepository loginRepository;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    void 서버에_존재하는_id와_password로_로그인_할_수_있다(){
        //given
        String id="testId";
        String password="testPwd";
        User saveUser = User.builder()
                .id(1L)
                .loginInfo(LoginInfo.builder()
                        .loginId(id)
                        .pwd(password)
                        .build())
                .build();
        BDDMockito.given(loginRepository.findByLoginIdAndPassword(id,password)).willReturn(Optional.of(saveUser));

        //when
        Optional<User> result = loginService.login(id,password);
        //then
        assertThat(result).isPresent();
        assertThat(result.get()).isNotNull();
        assertThat(result.get()).isEqualTo(saveUser);
    }

    @Test
    void 존재하지_않는_id와_password거나_id와_password의_정보가_불일치한_상태로_로그인을_시도하면_빈_객체를_반환한다(){
        //given
        String id="존재하지않음";
        String password="존재하지않음";

        BDDMockito.given(loginRepository.findByLoginIdAndPassword(id,password)).willReturn(Optional.empty());

        //when
        Optional<User> result = loginService.login(id,password);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    void User정보를_이용해_회원가입_할_수_있다(){
        //given
        String id="testId";
        String password="testPwd";

        User saveUser = User.builder()
                .id(1L)
                .loginInfo(LoginInfo.builder()
                        .loginId(id)
                        .pwd(passwordEncoder.encode(password))
                        .build())
                .build();
        BDDMockito.given(loginRepository.save(any(User.class))).willReturn(saveUser);

        //when
        User result = loginService.regist(saveUser);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getLoginInfo().getPwd()).isEqualTo(passwordEncoder.encode(password));
    }
}
