package com.example.game.item.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String name;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private String description;
    private int price;
    private int basePrice;

    public Item(String name, ItemType itemType, String description, int price, int basePrice) {
        this.name = name;
        this.itemType = itemType;
        this.description = description;
        this.price = price;
        this.basePrice = basePrice;
    }
}
