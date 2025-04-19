package com.example.game.chicken.entity;

import com.example.game.chicken.repository.ChickenRepository;
import com.example.game.chicken.repository.UserChickenRepository;
import com.example.game.user.entity.User;
import com.example.game.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserChickenTest {

    @Autowired
    private UserChickenRepository userChickenRepository;
    @Autowired
    private ChickenRepository chickenRepository;
    @Autowired
    private UserRepository userRepository;


    @DisplayName("UserChicken 객체의 달걀 1개 생성 테스트")
    @ParameterizedTest()
    @CsvSource({    // 이 테스트에서는 닭의 최대 달걀 수용량을 5로 고정하였음(Chicken.maxEggCapacity == 5)
            "10, 1, 2", // 1개 생성
            "9, 0, 0",  // 0개 생성
            "50, 0, 5", // 최대 개수 만큼 생성
            "49, 0, 4", // 최대개수 -1 만큼 생성 (경계값)
            "9, 1, 1",  // 기존에 생성가능 1개가 있는 상태에서 0개 생성
            "40, 1, 5", // 기존에 생성가능 1개가 있는 상태에서 최대 개수 만큼 생성
            "39, 1, 4", // 기존에 생성가능 1개가 있는 상태에서 최대 개수 - 1 만큼 생성 (경계값)
            "60, 0, 5" // 최대개수 이상 생성할 수 있는 시간의 경우 최대 개수만큼 생성
    })
    void addCurrentEggTest(int elapsedTime, int beforeEggCount, int afterEggCount) {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Chicken chicken = chickenRepository.save(new Chicken("test-chicken", 5, 10));
        UserChicken userChicken = userChickenRepository
                .save(new UserChicken(user, chicken, LocalDateTime.now().minusSeconds(elapsedTime), beforeEggCount, 5));

        LocalDateTime lastProduceTime = userChicken.getLastProduceTime();

        // when
        userChicken.addCurrentEgg();

        // then
        Assertions.assertThat(userChicken.getLastProduceTime()).isAfter(lastProduceTime);
        Assertions.assertThat(userChicken.getCurrentEgg()).isEqualTo(afterEggCount);
    }
}
