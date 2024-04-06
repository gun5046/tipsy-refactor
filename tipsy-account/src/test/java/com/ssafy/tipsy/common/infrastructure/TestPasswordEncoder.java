package com.ssafy.tipsy.common.infrastructure;

import com.ssafy.tipsy.common.util.PasswordEncoderHolder;

public class TestPasswordEncoder implements PasswordEncoderHolder {
    @Override
    public String encode(String plainText) {
        return "encrypted";
    }
}
