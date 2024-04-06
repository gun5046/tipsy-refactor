package com.ssafy.tipsy.infrastructure;

import com.ssafy.tipsy.domain.Info;
import com.ssafy.tipsy.domain.LoginInfo;
import com.ssafy.tipsy.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoginRepositoryImplTest {

    @Mock
    private UserJpaRepository userJpaRepository;
    @InjectMocks
    private LoginRepositoryImpl loginRepository;


    @Test
    void User를_저장할_수_있다() {
        // given
        User user = User.builder()
                .info(Info.builder()
                        .birth("birth")
                        .nickname("nickname")
                        .gender("gender")
                        .build())
                .loginInfo(LoginInfo.builder()
                        .loginId("test")
                        .pwd("test")
                        .build())
                .build();

        UserEntity userEntity = UserEntity.fromModel(user);
        BDDMockito.given(userJpaRepository.save(any(UserEntity.class))).willReturn(userEntity);
        // When
        User savedUser = loginRepository.save(user);

        // Then
        assertThat(user.getLoginInfo().loginId).isEqualTo(savedUser.getLoginInfo().loginId);
        assertThat(user.getLoginInfo().getPwd()).isEqualTo(savedUser.getLoginInfo().pwd);
    }

    @Test
    void id와_password로_유저를_찾을_수_있다() {
        // given
        String loginId = "testuser";
        String password = "testpassword";
        UserEntity userEntity = new UserEntity();
        userEntity.setLoginId(loginId);
        userEntity.setPwd(password);
        BDDMockito.given(userJpaRepository.findByLoginIdAndPwd(loginId,password)).willReturn(Optional.of(userEntity));

        // when
        Optional<User> result = loginRepository.findByLoginIdAndPassword(loginId, password);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getLoginInfo().loginId).isEqualTo(loginId);
        assertThat(result.get().getLoginInfo().pwd).isEqualTo(password);
    }
}
