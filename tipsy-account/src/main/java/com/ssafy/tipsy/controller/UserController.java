package com.ssafy.tipsy.controller;

import com.ssafy.tipsy.service.port.UserService;
import com.ssafy.tipsy.controller.request.SignUpRequest;
import com.ssafy.tipsy.controller.response.UserResponse;
import com.ssafy.tipsy.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 정보 Api")
public class UserController {

    private final UserService userService;
    @GetMapping("/mypage")
    @Operation(summary = "유저 정보 조회", description = "uid를 보내면 서버에서 사용자 정보를 보내줌")
    public ResponseEntity<UserResponse> getUserInfo(@RequestParam Long uid) {

        return ResponseEntity.ok().body(UserResponse.from(userService.getById(uid)));
    }


    @GetMapping("/nickname")
    @Operation(summary = "중복 확인", description = "사용자가 입력한 닉네임이 중복인지 확인")
    public ResponseEntity<Void> checkName(@RequestParam String nickname) {
        if(userService.checkNickname(nickname)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    @PutMapping("/mypage/modify")
    @Operation(summary = "유저 정보 수정", description = "사용자 정보를 보내면 DB에 업데이트 한 결과 알려줌")
    public ResponseEntity<UserResponse> updateUserInfo(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                UserResponse.from(
                        userService.update(signUpRequest)
                        )
                );
    }

    @DeleteMapping("/delete")
    @Operation(summary = "회원탈퇴", description = "uid를 통해 사용자정보를 삭제한다.")
    public ResponseEntity<Void> deleteUser(@RequestParam Long uid) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
