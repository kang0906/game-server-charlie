package com.example.game.chicken.service;

import com.example.game.chicken.entity.Chicken;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.chicken.repository.ChickenRepository;
import com.example.game.chicken.repository.UserChickenRepository;
import com.example.game.common.exception.ErrorCode;
import com.example.game.common.exception.GlobalException;
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
    @Autowired
    private ChickenRandomDrawV1 chickenRandomDrawV1;

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

    @DisplayName("유저 닭 최대치 확장 비용 증가 식 검증")
    @Test
    void increaseChickenLimitPriceTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        user.getUserGameInfo().useMoney(100);   // user.getMoney() == 400
        // when then
        for (int i = 2; i < 20; i++) {
            chickenService.increaseChickenLimit(user);

            Assertions.assertThat(user.getUserGameInfo().getMoney()).isEqualTo(0);
            Assertions.assertThat(user.getUserGameInfo().getMaxChicken()).isEqualTo(i+1);

            user.getUserGameInfo().addMoney((int)Math.pow(2, i+1) * 100);
        }

        user.getUserGameInfo().useMoney((int)Math.pow(2, 20) * 100);
        user.getUserGameInfo().addMoney(100000000);
        chickenService.increaseChickenLimit(user);
        Assertions.assertThat(user.getUserGameInfo().getMoney()).isEqualTo(0);
        Assertions.assertThat(user.getUserGameInfo().getMaxChicken()).isEqualTo(21);
    }

    @DisplayName("유저 닭 최대치 확장 비용 부족 시 예외발생")
    @Test
    void increaseChickenLimitNotEnoughMoneyTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        user.getUserGameInfo().useMoney(101);   // user.getMoney() == 399

        // when then
        Assertions.assertThatThrownBy(() ->
                        chickenService.increaseChickenLimit(user))
                .isInstanceOf(GlobalException.class)
                .hasMessage(ErrorCode.NOT_ENOUGH_MONEY.getMessage());
    }

    @DisplayName("닭 랜덤뽑기 구매 테스트 ")
    @Test
    void chickenBuyTest() {
        // given
        chickenRepository.save(new Chicken("White Chicken", 0, 0, 500));
        chickenRepository.save(new Chicken("Brown Chicken", 0, 0, 450));
        chickenRepository.save(new Chicken("Golden Chicken", 0, 0, 50));
        chickenRandomDrawV1.init();
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        user.getUserGameInfo().addMoney(10000);

        // when
        chickenService.buyChicken(user);

        // then
        Assertions.assertThat(user.getUserGameInfo().getMoney())
                .isEqualTo(500);
        Assertions.assertThat(userChickenRepository.findAllByUser(user).size())
                .isEqualTo(1);

    }

}
