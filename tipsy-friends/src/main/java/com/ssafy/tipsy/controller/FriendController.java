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
    public ResponseEntity<?> sendInvitation(@RequestBody FriendRequest friendRequest) {

    }

    @PutMapping("/accepted-req")
    public ResponseEntity<?> acceptInvitation(@RequestBody FriendRequest friendRequest) {

    }

    @PutMapping("/denied-req")
    public ResponseEntity<?> denyInvitation(@RequestBody FriendRequest friendRequest) {

    }

    @GetMapping("/req")
    public ResponseEntity<?> getReceivedInvitationList(@RequestParam Long userId) {


    }

}