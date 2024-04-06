package com.ssafy.tipsy.common.util;

import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

public interface HttpRequest {

    public Mono<ResponseBody> post(String baseUrl, RequestBody requestBody, HttpHeaders... httpHeaders);
    public Mono<ResponseBody> get(String baseUrl, HttpHeaders... httpHeaders);
}
