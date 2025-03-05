package com.example.game.chicken.dto;

import com.example.game.chicken.entity.Chicken;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.user.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
        this.remainingCapacity = userChicken.getRemainingCapacity();
        this.maxCapacity = userChicken.getMaxCapacity();
    }
}
