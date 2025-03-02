package com.example.game.chicken.entity;

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

    private int maxCapacity;
}
