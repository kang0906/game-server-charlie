package com.example.game.chicken.controller;

import com.example.game.chicken.dto.UserChickenListDto;
import com.example.game.chicken.service.ChickenService;
import com.example.game.common.dto.ResponseDto;
import com.example.game.config.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChickenController {

    private final ChickenService chickenService;

    @GetMapping("/chicken")
    public ResponseDto<UserChickenListDto> getChickenList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(chickenService.getChickenList(userDetails.getUser()));
    }

    @PostMapping("/chicken/{chickenId}/egg")
    public ResponseDto<Boolean> getEggFromChicken(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long chickenId) {
        return ResponseDto.success(chickenService.getEggFromChicken(userDetails.getUser(), chickenId));
    }
}
