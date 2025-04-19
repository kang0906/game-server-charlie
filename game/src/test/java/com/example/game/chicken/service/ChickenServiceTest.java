package com.example.game.chicken.service;

import com.example.game.chicken.entity.Chicken;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.chicken.repository.ChickenRepository;
import com.example.game.chicken.repository.UserChickenRepository;
import com.example.game.item.entity.UserItem;
import com.example.game.item.repository.UserItemRepository;
import com.example.game.user.entity.User;
import com.example.game.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChickenServiceTest {

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

    @DisplayName("기존 아이템이 없을 경우 달걀 생성 테스트")
    @Test
    void getEggFromChickenWhenNoItemTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Chicken chicken = chickenRepository.save(new Chicken("test-chicken", 5, 10));
        UserChicken userChicken = userChickenRepository
                .save(new UserChicken(user, chicken, LocalDateTime.now().minusSeconds(10), 0, 5));

        // when
        chickenService.getEggFromChicken(user, userChicken.getUserChickenId());

        // then
        UserItem userItem = userItemRepository.findByUserAndItem(user, chicken.getProduceItem()).get();

        Assertions.assertThat(userItem.getQuantity()).isEqualTo(1);
    }

    @DisplayName("기존 아이템이 없을 경우 달걀 생성 테스트")
    @ParameterizedTest()
    @CsvSource({"0,1", "1, 2", "5, 6"})
    void getEggFromChickenWithItemTest(int beforeItemQuantity, int afterItemQuantity) {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Chicken chicken = chickenRepository.save(new Chicken("test-chicken", 5, 10));
        UserChicken userChicken = userChickenRepository
                .save(new UserChicken(user, chicken, LocalDateTime.now().minusSeconds(10), 0, 5));
        userItemRepository.save(new UserItem(user, chicken.getProduceItem(), beforeItemQuantity));

        // when
        chickenService.getEggFromChicken(user, userChicken.getUserChickenId());

        // then
        UserItem userItem = userItemRepository.findByUserAndItem(user, chicken.getProduceItem()).get();

        Assertions.assertThat(userItem.getQuantity()).isEqualTo(afterItemQuantity);
    }
}
