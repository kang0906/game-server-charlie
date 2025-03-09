package com.example.game.chicken.dto;

import com.example.game.chicken.entity.UserChicken;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserChickenListDto {
    private List<UserChickenDto> userChickenList;

    public UserChickenListDto(List<UserChicken> userChicken) {
        this.userChickenList = userChicken.stream().map(UserChickenDto::new).toList();
    }
}
