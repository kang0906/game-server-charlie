package com.example.game.chicken.service;

import com.example.game.chicken.entity.Chicken;
import com.example.game.chicken.repository.ChickenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ChickenRandomDrawV1Test {

    @Autowired
    private ChickenRepository chickenRepository;
    @Autowired
    private ChickenRandomDrawV1 chickenRandomDrawV1;

    @DisplayName("랜덤 뽑기 테스트")
    @Test
    void randomDrawTest() {
        chickenRepository.save(new Chicken("White Chicken", 0, 0, 500));
        chickenRepository.save(new Chicken("Brown Chicken", 0, 0, 450));
        chickenRepository.save(new Chicken("Golden Chicken", 0, 0, 50));
        chickenRandomDrawV1.init();

        int whiteChickenCount = 0;
        int brownChickenCount = 0;
        int goldenChickenCount = 0;

        for (int i = 0; i < 1000; i++) {
            Chicken chicken = chickenRandomDrawV1.getRandomChicken();

            if (chicken.getChickenName().equals("White Chicken")) {
                whiteChickenCount++;
            } else if (chicken.getChickenName().equals("Brown Chicken")) {
                brownChickenCount++;
            } else if (chicken.getChickenName().equals("Golden Chicken")) {
                goldenChickenCount++;
            }
        }

        System.out.println("whiteChickenCount = " + whiteChickenCount);
        System.out.println("brownChickenCount = " + brownChickenCount);
        System.out.println("goldenChickenCount = " + goldenChickenCount);
    }

}
