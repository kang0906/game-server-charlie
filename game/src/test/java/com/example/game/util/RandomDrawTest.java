package com.example.game.util;

import com.example.game.chicken.entity.Chicken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomDrawTest {

    @DisplayName("랜덤 뽑기 테스트")
    @Test
    void randomDrawTest() {
        RandomDraw<Chicken> randomDraw = new RandomDraw<>();

        randomDraw.putItemToList(new Chicken("White Chicken", 0, 0), 700);
        randomDraw.putItemToList(new Chicken("Brown Chicken", 0, 0), 250);
        randomDraw.putItemToList(new Chicken("Golden Chicken", 0, 0), 50);

        int whiteChickenCount = 0;
        int brownChickenCount = 0;
        int goldenChickenCount = 0;

        for (int i = 0; i < 1000; i++) {
            Chicken chicken = randomDraw.getItem();

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
