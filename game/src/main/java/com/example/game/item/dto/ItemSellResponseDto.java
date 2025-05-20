package com.example.game.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSellResponseDto {
    private int userMoney;
    private int totalSellAmount;
}
