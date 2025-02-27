package com.example.game.user.dto;

import com.example.game.user.entity.User;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private String username;
    private int money;
    private int maxChicken;
    private int eggCount;

    public UserInfoResponseDto(User user) {
        this.username = user.getUsername();
        this.money = user.getUserGameInfo().getMoney();
        this.maxChicken = user.getUserGameInfo().getMaxChicken();
        this.eggCount = user.getUserGameInfo().getEggCount();
    }
}
