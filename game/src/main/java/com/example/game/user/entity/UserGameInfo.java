package com.example.game.user.entity;

import com.example.game.common.exception.ErrorCode;
import com.example.game.common.exception.GlobalException;
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
        if (amount < 0) {
           throw new GlobalException(ErrorCode.CAN_NOT_USE_NEGATIVE_NUMBER);
        }
        eggCount += amount;
    }

    public void addMoney(int amount) {
        if (amount < 0) {
            throw new GlobalException(ErrorCode.CAN_NOT_USE_NEGATIVE_NUMBER);
        }
        money += amount;
    }
}
