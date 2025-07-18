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
    private int maxEggCapacity;
    private int eggProduceSpeedSec;
    private int randomDrawProbability;

    public Chicken(String chickenName, int maxEggCapacity, int eggProduceSpeedSec) {
        this.chickenName = chickenName;
        this.maxEggCapacity = maxEggCapacity;
        this.eggProduceSpeedSec = eggProduceSpeedSec;
    }

    public Chicken(String chickenName, int maxEggCapacity, int eggProduceSpeedSec, int randomDrawProbability) {
        this.chickenName = chickenName;
        this.maxEggCapacity = maxEggCapacity;
        this.eggProduceSpeedSec = eggProduceSpeedSec;
        this.randomDrawProbability = randomDrawProbability;
    }
}
