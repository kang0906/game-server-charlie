package com.example.game.item.repository;

import com.example.game.item.entity.ItemPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPriceHistoryRepository extends JpaRepository<ItemPriceHistory, Long> {
}
