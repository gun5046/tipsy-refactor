package com.ssafy.tipsy.infrastructure;

import com.ssafy.tipsy.domain.Info;
import com.ssafy.tipsy.domain.LoginInfo;
import com.ssafy.tipsy.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String loginId;

    @Column
    private String pwd;

    @Column
    private String nickname;

    @Column
    private String image;

    @Column
    private String birth;

    @Column
    private String gender;


    @Builder
    public UserEntity(Long id,String loginId,String pwd, String nickname, String image, String birth, String gender) {
        this.id = id;
        this.loginId=loginId;
        this.pwd=pwd;
        this.nickname = nickname;
        this.image = image;
        this.birth = birth;
        this.gender = gender;
    }

    public static UserEntity fromModel(User user){
        return UserEntity.builder()
                .id(user.getId())
                .loginId(user.getLoginInfo().loginId)
                .pwd(user.getLoginInfo().loginId)
                .nickname(user.getInfo().nickname)
                .birth(user.getInfo().birth)
                .image(user.getInfo().image)
                .gender(user.getInfo().gender)
                .build();
    }

    public User toModel(){
        return User.builder()
                .id(id)
                .info(Info.builder()
                        .nickname(nickname)
                        .birth(birth)
                        .image(image)
                        .gender(gender)
                        .build())
                .loginInfo(LoginInfo.builder()
                        .loginId(loginId)
                        .pwd(pwd)
                        .build())
                .build();
    }
}
