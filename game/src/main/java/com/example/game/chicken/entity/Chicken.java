package com.example.game.chicken.entity;

import com.example.game.item.entity.Item;
import com.example.game.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "CHICKEN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chicken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chickenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item produceItem;

    private String chickenName;
    private int maxCapacity;

    public Chicken(String chickenName, int maxCapacity) {
        this.chickenName = chickenName;
        this.maxCapacity = maxCapacity;
    }
}
