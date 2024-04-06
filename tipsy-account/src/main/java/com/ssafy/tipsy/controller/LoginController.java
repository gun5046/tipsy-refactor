package com.ssafy.tipsy.controller;


import com.ssafy.tipsy.auth.JwtProvider;
import com.ssafy.tipsy.auth.model.Role;
import com.ssafy.tipsy.service.port.LoginService;
import com.ssafy.tipsy.controller.request.LoginRequest;
import com.ssafy.tipsy.controller.request.SignUpRequest;
import com.ssafy.tipsy.controller.response.LoginResponse;
import com.ssafy.tipsy.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "Login", description = "로그인 API")
public class LoginController {
	private final LoginService loginService;
	private final JwtProvider jwtProvider;
	@PostMapping
	@Operation(summary = "로그인", description = "로그인 후 서버에 정보가 있는지 확인 후 결과 전송")
	public ResponseEntity<LoginResponse> login(@RequestBody(required = false) LoginRequest loginRequest) {
		Optional<User> user = loginService.login(loginRequest.getId(),loginRequest.getPassword());
        if(user.isPresent()){
			return ResponseEntity.ok()
					.header("Authorization", jwtProvider.createJwtToken(user.get().getInfo().nickname,Role.USER))
					.body(LoginResponse.from(user.get()));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).header("MESSAGE","Authentication-Failed").build();

	}


	@PostMapping("/new")
	@Operation(summary = "회원 가입", description = "name, nickname 은 NonNull")
	public ResponseEntity<LoginResponse> regist(@RequestBody SignUpRequest signUpRequest) {


		return ResponseEntity.status(HttpStatus.CREATED)
				.body(LoginResponse.from(loginService.regist(User.signUp(signUpRequest))));
	}



	@GetMapping("/token")
	public void checkToken() {
		return;
	}


	@GetMapping("/logout")
	@Operation(summary ="로그아웃", description = "쿠키 제거")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		return;
	}
}
