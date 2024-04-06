package com.ssafy.tipsy.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthFail {

    WRONG(401, "Malformed_TOKEN"),
    INVALID(401, "INVALID_TOKEN"),
    EXPIRED(403, "EXPIRED_TOKEN");
    private final int code;
    private final String msg;

}
