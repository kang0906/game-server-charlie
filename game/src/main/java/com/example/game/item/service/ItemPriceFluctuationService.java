package com.example.game.item.service;

import com.example.game.admin.alert.entity.AlertLevel;
import com.example.game.admin.alert.service.AlertService;
import com.example.game.item.entity.Item;
import com.example.game.item.entity.ItemPriceHistory;
import com.example.game.item.repository.ItemPriceHistoryRepository;
import com.example.game.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
@RequiredArgsConstructor
public class ItemPriceFluctuationService {

    private final ItemRepository itemRepository;
    private final ItemPriceHistoryRepository itemPriceHistoryRepository;
    private final AlertService alertService;

    private Random random = new Random();

    @Scheduled(cron = "0 */30 * * * *")
    @Transactional
    public void changePrice() {
        log.info("[ItemPriceFluctuationService] start changePrice()");
        LocalDateTime startTime = LocalDateTime.now();

        List<Item> all = itemRepository.findAll();

        for (Item item : all) {
            itemPriceHistoryRepository.save(
                    new ItemPriceHistory(item, makeNowPrice(item.getPrice(), item.getBasePrice()) ,item.getPrice()));
        }

        long elapse = Duration.between(startTime, LocalDateTime.now()).getSeconds();
        log.info("[ItemPriceFluctuationService] changePrice() done (elapse = {})", elapse);
        if (elapse > 60) {
            alertService.sendAlert(AlertLevel.WARN, "[ItemPriceFluctuationService] changePrice() takes too long {elapse : " + elapse + "}");
        }
    }

    private int makeNowPrice(int oldPrice, int basePrice) {
        int newPrice = random.nextInt(basePrice * 2);

        if (newPrice < 10) {
            newPrice = 10;
        }

        return newPrice;
    }
}
