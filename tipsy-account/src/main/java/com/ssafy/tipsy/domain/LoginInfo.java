package com.ssafy.tipsy.domain;

import com.ssafy.tipsy.common.exception.ValidationException;
import com.ssafy.tipsy.common.util.PasswordEncoderHolder;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Builder
public class LoginInfo {
    public String loginId;
    public String pwd;

    public LoginInfo encryptPwd(String pwd,PasswordEncoderHolder passwordEncoderHolder) {
        return LoginInfo.builder()
                .loginId(this.loginId)
                .pwd(passwordEncoderHolder.encode(pwd))
                .build();
    }

    public void validateId(){
        Pattern pattern = Pattern.compile("[^a-zA-Z][^0-9]");
        Matcher matcher = pattern.matcher(this.loginId);

        if(matcher.find()){
            throw new ValidationException(this.getClass().getName(), loginId);
        }

    }

}
