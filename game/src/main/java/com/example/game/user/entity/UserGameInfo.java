package com.example.game.user.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGameInfo {
    private int maxChicken;
    private int maxItem;
    private int money;
    private int eggCount;
}
