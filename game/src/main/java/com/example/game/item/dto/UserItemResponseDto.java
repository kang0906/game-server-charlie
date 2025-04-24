package com.example.game.item.dto;

import com.example.game.item.entity.Item;
import com.example.game.item.entity.ItemType;
import com.example.game.item.entity.UserItem;
import com.example.game.user.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UserItemResponseDto {

    private Long userItemId;
    private Long userId;
    private int quantity;

    private Long itemId;
    private String name;
    private ItemType itemType;
    private String description;
    private int price;

    public UserItemResponseDto(UserItem userItem) {
        this.userItemId = userItem.getUserItemId();
        this.userId = userItem.getUser().getUserId();
        this.quantity = userItem.getQuantity();

        Item item = userItem.getItem();
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.itemType = item.getItemType();
        this.description = item.getDescription();
        this.price = item.getPrice();
    }
}
