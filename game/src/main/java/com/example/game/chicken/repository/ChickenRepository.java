package com.example.game.chicken.repository;

import com.example.game.chicken.entity.Chicken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChickenRepository extends JpaRepository<Chicken, Long> {
}
