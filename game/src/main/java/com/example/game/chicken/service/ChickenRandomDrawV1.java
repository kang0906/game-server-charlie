package com.example.game.chicken.service;

import com.example.game.chicken.entity.Chicken;
import com.example.game.chicken.repository.ChickenRepository;
import com.example.game.util.RandomDraw;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChickenRandomDrawV1 implements ChickenRandomDraw {

    private final ChickenRepository chickenRepository;
    private RandomDraw<Chicken> randomDraw = new RandomDraw<>();

    @PostConstruct
    public void init() {
        randomDraw.reset();
        List<Chicken> all = chickenRepository.findAll();

        for (Chicken chicken : all) {
            randomDraw.putItemToList(chicken, chicken.getRandomDrawProbability());
        }
    }

    @Override
    public Chicken getRandomChicken() {
        return randomDraw.getItem();
    }
}
