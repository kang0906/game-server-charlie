package com.example.game.chicken.service;

import com.example.game.chicken.dto.UserChickenListDto;
import com.example.game.chicken.entity.UserChicken;
import com.example.game.chicken.repository.UserChickenRepository;
import com.example.game.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ChickenService {

    private final UserChickenRepository userChickenRepository;

    public UserChickenListDto getChickenList(User user) {
        List<UserChicken> userChickenList = userChickenRepository.findAllByUser(user);
        return new UserChickenListDto(userChickenList);
    }
}
