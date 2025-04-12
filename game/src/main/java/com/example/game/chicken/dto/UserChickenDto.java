package com.example.game.chicken.dto;

import com.example.game.chicken.entity.UserChicken;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserChickenDto {
    private String chickenName;
    private Long userChickenId;
    private Long userId;
    private LocalDateTime lastProduceTime;
    private int remainingCapacity;
    private int maxCapacity;

    public UserChickenDto(UserChicken userChicken) {
        this.chickenName = userChicken.getChicken().getChickenName();
        this.userChickenId = userChicken.getUserChickenId();
        this.userId = userChicken.getUser().getUserId();
        this.lastProduceTime = userChicken.getLastProduceTime();
        this.remainingCapacity = userChicken.getCurrentEgg();
        this.maxCapacity = userChicken.getMaxCapacity();
    }
}
