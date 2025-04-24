package com.example.game.item.service;

import com.example.game.common.exception.GlobalException;
import com.example.game.item.dto.UserItemListResponseDto;
import com.example.game.item.entity.ItemType;
import com.example.game.item.entity.UserItem;
import com.example.game.item.repository.UserItemRepository;
import com.example.game.user.entity.User;
import com.example.game.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.game.common.exception.ErrorCode.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserItemService {

    private final UserItemRepository userItemRepository;
    private final UserRepository userRepository;

    @Transactional
    public String itemSell(User user, Long itemId, int amount) {
        UserItem userItem = userItemRepository.findById(itemId)
                .orElseThrow(() -> new GlobalException(DATA_NOT_FOUND));
        user = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new GlobalException(DATA_NOT_FOUND));

        if(userItem.getUser().getUserId() != user.getUserId()) {
            throw new GlobalException(VALIDATION_FAIL);
        }

        if (userItem.getQuantity() < amount) {
            throw new GlobalException(NOT_ENOUGH_ITEM);
        }

        userItem.reduceQuantity(amount);
        if (userItem.getItem().getItemType() == ItemType.EGG) {
            user.getUserGameInfo().reduceEggCount(amount);
        }

        user.getUserGameInfo().addMoney(userItem.getItem().getPrice() * amount);

        return "success";
    }

    public UserItemListResponseDto itemList(User user) {
        List<UserItem> userItemList = userItemRepository.findAllByUser(user);
        return new UserItemListResponseDto(userItemList);
    }
}
