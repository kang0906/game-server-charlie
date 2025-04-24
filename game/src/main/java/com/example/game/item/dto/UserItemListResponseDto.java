package com.example.game.item.dto;

import com.example.game.item.entity.Item;
import com.example.game.item.entity.UserItem;
import lombok.Data;

import java.util.List;

@Data
public class UserItemListResponseDto {

    private List<UserItemResponseDto> userChickenList;

    public UserItemListResponseDto(List<UserItem> userChickenList) {
        this.userChickenList = userChickenList.stream().map(UserItemResponseDto::new).toList();
    }
}
