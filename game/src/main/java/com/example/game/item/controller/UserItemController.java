package com.example.game.item.controller;

import com.example.game.common.dto.ResponseDto;
import com.example.game.config.UserDetailsImpl;
import com.example.game.item.dto.UserItemListResponseDto;
import com.example.game.item.dto.ItemSellRequestDto;
import com.example.game.item.service.UserItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserItemController {

    private final UserItemService userItemService;

    @PostMapping("/item/{itemId}/sell")
    public ResponseDto<String> itemSell(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long itemId, @RequestBody ItemSellRequestDto requestDto) {
        return ResponseDto.success(userItemService.itemSell(userDetails.getUser(), itemId, requestDto.getAmount()));
    }

    @GetMapping("/item")
    public ResponseDto<UserItemListResponseDto> itemList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseDto.success(userItemService.itemList(userDetails.getUser()));
    }
}
