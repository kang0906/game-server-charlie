package com.example.game.user.dto;

import com.example.game.chicken.dto.UserChickenDto;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.user.entity.User;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private String username;
    private int money;
    private int maxChicken;
    private int chickenCount;
    private int eggCount;

    private UserChickenDto userChickenDto;

    public UserInfoResponseDto(User user, UserChicken userChicken) {
        this.username = user.getUsername();
        this.money = user.getUserGameInfo().getMoney();
        this.maxChicken = user.getUserGameInfo().getMaxChicken();
        this.eggCount = user.getUserGameInfo().getEggCount();
        this.chickenCount = user.getUserGameInfo().getChickenCount();
        this.userChickenDto = new UserChickenDto(userChicken);
    }
}
