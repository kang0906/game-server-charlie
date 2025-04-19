package com.example.game.item.entity;

import com.example.game.common.exception.ErrorCode;
import com.example.game.common.exception.GlobalException;
import com.example.game.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "USER_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;

    public UserItem(User user, Item item, int quantity) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
    }

    public int addQuantity(int amount) {
        if (amount < 0) {
            throw new GlobalException(ErrorCode.CAN_NOT_USE_NEGATIVE_NUMBER);
        }
        this.quantity += amount;
        return quantity;
    }

    public int reduceQuantity(int amount) {
        if (amount < 0) {
            throw new GlobalException(ErrorCode.CAN_NOT_USE_NEGATIVE_NUMBER);
        }
        this.quantity -= amount;
        return quantity;
    }

}
