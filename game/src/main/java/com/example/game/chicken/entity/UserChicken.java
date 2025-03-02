package com.example.game.chicken.entity;

import com.example.game.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private int remainingCapacity;
    private int maxCapacity;

}
