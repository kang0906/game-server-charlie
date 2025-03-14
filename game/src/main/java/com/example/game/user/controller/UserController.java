package com.example.game.user.controller;

import com.example.game.common.dto.ResponseDto;
import com.example.game.config.UserDetailsImpl;
import com.example.game.user.dto.RequestUserEmblemChange;
import com.example.game.user.dto.UserInfoResponseDto;
import com.example.game.user.dto.RequestLogin;
import com.example.game.user.dto.RequestUsernameChange;
import com.example.game.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign")
    public void signup(@Valid @RequestBody RequestLogin requestLogin) {
        userService.signup(requestLogin);
    }

    @GetMapping("/user/game/info")
    public ResponseDto<UserInfoResponseDto> myPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(userService.getMyInfo(userDetails.getUser()));
    }


    @PostMapping("/user/name")
    public ResponseDto<String> changeUsername(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid RequestUsernameChange requestDto) {
        userService.changeUsername(userDetails.getUser(), requestDto.getUsername());
        return ResponseDto.success("success");
    }
}
