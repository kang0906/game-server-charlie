package com.example.game.item.service;

import com.example.game.chicken.entity.Chicken;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.chicken.repository.ChickenRepository;
import com.example.game.chicken.repository.UserChickenRepository;
import com.example.game.chicken.service.ChickenService;
import com.example.game.common.exception.ErrorCode;
import com.example.game.common.exception.GlobalException;
import com.example.game.item.entity.Item;
import com.example.game.item.entity.ItemType;
import com.example.game.item.entity.UserItem;
import com.example.game.item.repository.ItemRepository;
import com.example.game.item.repository.UserItemRepository;
import com.example.game.user.entity.User;
import com.example.game.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserItemServiceTest {

    @Autowired
    private UserChickenRepository userChickenRepository;
    @Autowired
    private ChickenRepository chickenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserItemRepository userItemRepository;
    @Autowired
    private ChickenService chickenService;
    @Autowired
    private UserItemService userItemService;
    @Autowired
    private ItemRepository itemRepository;

    @DisplayName("아이템을 정상적으로 판매한다.")
    @Test
    void itemSellTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Item testItem = itemRepository.save(new Item("testItem", ItemType.ITEM, "", 100, 100));
        UserItem userItem = userItemRepository.save(new UserItem(user,testItem, 10));

        // when
        userItemService.itemSell(user, userItem.getUserItemId(), 1);

        // then
        Assertions.assertThat(userItem.getQuantity()).isEqualTo(9);
        Assertions.assertThat(user.getUserGameInfo().getMoney()).isEqualTo(600);
    }

    @DisplayName("달걀 타입의 아이템을 판매한 경우 유저의 달걀 소유 개수 변경된다.")
    @Test
    void EggTypeItemSellTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Item testItem = itemRepository.save(new Item("testItem", ItemType.EGG, "", 100, 100));
        UserItem userItem = userItemRepository.save(new UserItem(user,testItem, 10));
        user.getUserGameInfo().addEggCount(10);

        // when
        userItemService.itemSell(user, userItem.getUserItemId(), 1);

        // then
        Assertions.assertThat(userItem.getQuantity()).isEqualTo(9);
        Assertions.assertThat(user.getUserGameInfo().getMoney()).isEqualTo(600);
        Assertions.assertThat(user.getUserGameInfo().getEggCount()).isEqualTo(9);
    }

    @DisplayName("자신의 아이템이 아닐 경우 예외가 발생한다.")
    @Test
    void NotMyItemSellExceptionTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        User user2 = userRepository.save(new User("user-email2", 1L, "username2", "password2"));
        Item testItem = itemRepository.save(new Item("testItem", ItemType.ITEM, "", 100, 100));
        UserItem userItem = userItemRepository.save(new UserItem(user,testItem, 10));

        // when then
        Assertions.assertThatThrownBy(() ->
                userItemService.itemSell(user2, userItem.getUserItemId(), 1))
                .isInstanceOf(GlobalException.class)
                .hasMessage(ErrorCode.VALIDATION_FAIL.getMessage());
    }

    @DisplayName("보유 아이템의 개수 이상 판매하려는 경우 예외 발생한다.")
    @Test
    void ItemSellMoreThenQuantityExceptionTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Item testItem = itemRepository.save(new Item("testItem", ItemType.ITEM, "", 100, 100));
        UserItem userItem = userItemRepository.save(new UserItem(user,testItem, 10));

        // when then
        Assertions.assertThatThrownBy(() ->
                        userItemService.itemSell(user, userItem.getUserItemId(), 11))
                .isInstanceOf(GlobalException.class)
                .hasMessage(ErrorCode.NOT_ENOUGH_ITEM.getMessage());
    }

    @DisplayName("보유 아이템의 개수 이상 판매하려는 경우 예외 발생한다.")
    @Test
    void ItemSellRequestNegativeNumberExceptionTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Item testItem = itemRepository.save(new Item("testItem", ItemType.ITEM, "", 100, 100));
        UserItem userItem = userItemRepository.save(new UserItem(user,testItem, 10));

        // when then
        Assertions.assertThatThrownBy(() ->
                        userItemService.itemSell(user, userItem.getUserItemId(), -1))
                .isInstanceOf(GlobalException.class)
                .hasMessage(ErrorCode.CAN_NOT_USE_NEGATIVE_NUMBER.getMessage());
    }
}
