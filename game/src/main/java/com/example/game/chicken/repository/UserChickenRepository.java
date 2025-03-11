package com.example.game.chicken.repository;

import com.example.game.chicken.entity.UserChicken;
import com.example.game.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChickenRepository extends JpaRepository<UserChicken, Long> {
    UserChicken findTopByUser(User user);
    List<UserChicken> findAllByUser(User user);
}
