package com.example.game.chicken.repository;

import com.example.game.chicken.entity.Chicken;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.user.entity.User;
import com.example.game.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserChickenRepositoryTest {

    @Autowired
    private UserChickenRepository userChickenRepository;
    @Autowired
    private ChickenRepository chickenRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("User로 UserChicken 조회")
    @Test
    void findByUserTest() {
        // given
        User user = userRepository.save(new User("user-email", 0L, "username", "password"));
        Chicken chicken = chickenRepository.save(new Chicken("test-chicken", 5));
        UserChicken userChicken = userChickenRepository.save(new UserChicken(user, chicken));

        // when
        UserChicken byUser = userChickenRepository.findTopByUser(user);

        // then
        Assertions.assertThat(byUser.getUserChickenId()).isEqualTo(userChicken.getUserChickenId());
        Assertions.assertThat(byUser.getUser().getUserId()).isEqualTo(userChicken.getUser().getUserId());
        Assertions.assertThat(byUser.getChicken().getChickenId()).isEqualTo(userChicken.getChicken().getChickenId());

    }

}
