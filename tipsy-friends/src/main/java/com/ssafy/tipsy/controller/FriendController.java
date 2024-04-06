package com.ssafy.tipsy.controller;

import com.ssafy.tipsy.controller.request.FriendRequest;
import org.apache.catalina.core.ApplicationFilterChain;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.DelegatingFilterProxy;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @GetMapping()
    public ResponseEntity<?> getFriendList(@RequestParam Long userId) {

    }


    @PostMapping("/req")
    public ResponseEntity<?> sendRequest(@RequestBody FriendRequest friendRequest) {

    }

    @PutMapping("/accepted-req")
    public ResponseEntity<?> acceptRequest(@RequestBody FriendRequest friendRequest) {

    }

    @PutMapping("/denied-req")
    public ResponseEntity<?> denyRequest(@RequestBody FriendRequest friendRequest) {

    }

    @GetMapping("/req")
    public ResponseEntity<?> getReceivedRequestList(@RequestParam Long userId) {


    }

}