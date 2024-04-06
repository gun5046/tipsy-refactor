package com.ssafy.tipsy.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByLoginIdAndPwd(String id, String pwd);

    Optional<UserEntity> findByNickname(String nickname);
}
