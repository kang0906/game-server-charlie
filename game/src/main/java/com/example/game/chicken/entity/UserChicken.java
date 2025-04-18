package com.example.game.chicken.entity;

import com.example.game.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Table(name = "USER_CHICKEN")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserChicken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userChickenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chicken_id")
    private Chicken chicken;

    private LocalDateTime lastProduceTime;
    private int currentEgg;
    private int maxCapacity;

    public UserChicken(User user, Chicken chicken) {
        this.user = user;
        this.chicken = chicken;
        this.lastProduceTime = LocalDateTime.now();
        this.currentEgg = chicken.getMaxEggCapacity();
        this.maxCapacity = chicken.getMaxEggCapacity();
    }
    public UserChicken(User user, Chicken chicken, LocalDateTime lastProduceTime, int currentEgg, int maxCapacity) {
        this.user = user;
        this.chicken = chicken;
        this.lastProduceTime = lastProduceTime;
        this.currentEgg = currentEgg;
        this.maxCapacity = maxCapacity;
    }

    public void addCurrentEgg() {
        if(currentEgg < maxCapacity) {
            long between = ChronoUnit.SECONDS.between(lastProduceTime, LocalDateTime.now());
            int amount = (int) (between / chicken.getEggProduceSpeedSec());

            currentEgg += amount;
            if(currentEgg > maxCapacity) {
                currentEgg = maxCapacity;
            }

            lastProduceTime = LocalDateTime.now();
        }
    }

    public boolean decreaseCurrentEgg() {
        if (currentEgg > 0) {
            currentEgg--;
            return true;
        } else {
            return false;
        }
    }
}
