package com.example.game.item.repository;

import com.example.game.chicken.entity.UserChicken;
import com.example.game.item.entity.Item;
import com.example.game.item.entity.UserItem;
import com.example.game.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    Optional<UserItem> findByUserAndItem(User user, Item item);

}
