package com.example.game.item.repository;

import com.example.game.item.entity.Item;
import com.example.game.item.entity.ItemType;
import com.example.game.item.entity.UserItem;
import com.example.game.user.entity.User;
import com.example.game.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserItemRepositoryTest {

    @Autowired
    private UserItemRepository userItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("User로 UserItem 모두 조회")
    @Test
    void findAllByUserTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        User user2 = userRepository.save(new User("user-email2", 1L, "username2", "password"));
        Item item1 = itemRepository.save(new Item("testItem1", ItemType.ITEM, "testItem", 11, 10));
        Item item2 = itemRepository.save(new Item("testItem2", ItemType.EGG, "testItem", 9, 10));
        UserItem userItem1 = userItemRepository.save(new UserItem(user, item1, 10));
        UserItem userItem2 = userItemRepository.save(new UserItem(user, item2, 10));
        UserItem userItem3 = userItemRepository.save(new UserItem(user2, item2, 10));

        // when
        List<UserItem> allByUser = userItemRepository.findAllByUser(user);

        // then
        Assertions.assertThat(allByUser).hasSize(2)
                .extracting("user", "item", "quantity")
                .containsExactlyInAnyOrder(
                        tuple(user, item1, 10),
                        tuple(user, item2, 10)
                );
    }

}
