package com.example.game.user.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserGameInfo {
    private int maxChicken;
    private int chickenCount;
    private int maxItem;
    private int money;
    private int eggCount;

    public void addEggCount(int amount) {
        eggCount += amount;
    }
}
