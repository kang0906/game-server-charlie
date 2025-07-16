package com.example.game.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomDraw<T> {
    private Map<Integer, T> itemList = new HashMap<>();
    private T defaultItem;
    private Random random = new Random();

    public void putItemToList(T item, int probability) {
        itemList.put(probability, item);
    }

    public T getItem() {
        int rand = random.nextInt(1000);
        int cumulativeProbability = 0;

        for (Integer integer : itemList.keySet()) {
            cumulativeProbability += integer;
            if(rand <= cumulativeProbability) {
                return itemList.get(integer);
            }
        }

        return defaultItem;
    }
}
