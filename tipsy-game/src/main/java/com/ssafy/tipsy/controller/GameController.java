package com.ssafy.tipsy.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name ="Game", description = "Game Api")
public class GameController {

    @GetMapping("/game/room")
    @Operation(summary = "방 정보 확인", description = "요청받은 rid의 방 정보를 확인하고 결과 반환")
    public String checkGameRoom(@RequestParam Long uid, @RequestParam String rid) {

    }


}
