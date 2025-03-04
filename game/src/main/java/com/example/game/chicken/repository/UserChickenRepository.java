package com.example.game.chicken.repository;

import com.example.game.chicken.entity.UserChicken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChickenRepository extends JpaRepository<UserChicken, Long> {
}
