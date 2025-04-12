package com.example.game.chicken.service;

import com.example.game.chicken.dto.UserChickenListDto;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.chicken.repository.UserChickenRepository;
import com.example.game.common.exception.GlobalException;
import com.example.game.item.entity.Item;
import com.example.game.item.entity.UserItem;
import com.example.game.item.repository.UserItemRepository;
import com.example.game.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.game.common.exception.ErrorCode.DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChickenService {

    private final UserChickenRepository userChickenRepository;
    private final UserItemRepository userItemRepository;

    public UserChickenListDto getChickenList(User user) {
        List<UserChicken> userChickenList = userChickenRepository.findAllByUser(user);
        return new UserChickenListDto(userChickenList);
    }

    @Transactional
    public boolean getEggFromChicken(User user, Long chickenId) {
        UserChicken userChicken = userChickenRepository.findById(chickenId)
                .orElseThrow(() -> new GlobalException(DATA_NOT_FOUND));

        // 달걀 생성 확인 및 생성
        userChicken.addCurrentEgg();

        // 달걀 생성 (item)
        if (userChicken.decreaseCurrentEgg()) {
            Item produceItem = userChicken.getChicken().getProduceItem();
            Optional<UserItem> userItem = userItemRepository.findByUserAndItem(user, produceItem);

            if(userItem.isPresent()) {
                userItem.get().addQuantity(1);
            } else {
                userItemRepository.save(new UserItem(user, produceItem, 1));
            }
            return true;
        }

        return false;
    }
}
