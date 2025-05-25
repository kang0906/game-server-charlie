package com.example.game.item.service;

import com.example.game.chicken.service.ChickenService;
import com.example.game.item.entity.Item;
import com.example.game.item.entity.ItemPriceHistory;
import com.example.game.item.entity.ItemType;
import com.example.game.item.repository.ItemPriceHistoryRepository;
import com.example.game.item.repository.ItemRepository;
import com.example.game.item.repository.UserItemRepository;
import com.example.game.user.entity.User;
import com.example.game.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemPriceFluctuationServiceTest {

    @Autowired
    private ItemPriceFluctuationService itemPriceFluctuationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemPriceHistoryRepository itemPriceHistoryRepository;

    @DisplayName("아이템 가격 변동 테스트")
    @Test
    void makeNowPriceTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Item testItem = itemRepository.save(new Item("testItem", ItemType.ITEM, "", 100, 100));

        // when
        for (int i = 0; i < 50; i ++) {
            itemPriceFluctuationService.changePrice();
        }
        // then
        List<ItemPriceHistory> all = itemPriceHistoryRepository.findAll();
        for (ItemPriceHistory itemPriceHistory : all) {
            System.out.println(itemPriceHistory.getNewPrice());
        }

    }

}
