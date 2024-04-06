package com.ssafy.tipsy.common.infrastructure;

import com.ssafy.tipsy.common.util.PasswordEncoderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SecurityPasswordEncoderHolder implements PasswordEncoderHolder {

    private final PasswordEncoder securityEncoder;

    public String encode(String plainText){
        return securityEncoder.encode(plainText);
    }
}
