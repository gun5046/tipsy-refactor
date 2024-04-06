package com.ssafy.tipsy.infrastructure;

import com.ssafy.tipsy.domain.User;
import com.ssafy.tipsy.infrastructure.port.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LoginRepositoryImpl implements LoginRepository {
    private final UserJpaRepository userJpaRepository;


    @Override
    public User save(User user) {
        return userJpaRepository.save(UserEntity.fromModel(user)).toModel();
    }

    @Override
    public Optional<User> findByLoginIdAndPassword(String id, String pwd) {
        return userJpaRepository.findByLoginIdAndPwd(id,pwd).map(UserEntity::toModel);
    }
}
