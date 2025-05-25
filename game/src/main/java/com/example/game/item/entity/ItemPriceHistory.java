package com.example.game.item.entity;

import com.example.game.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "ITEM_PRICE_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemPriceHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemPriceHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int newPrice;
    private int oldPrice;

    public ItemPriceHistory(Item item, int newPrice, int oldPrice) {
        this.item = item;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        item.setPrice(newPrice);
    }
}
