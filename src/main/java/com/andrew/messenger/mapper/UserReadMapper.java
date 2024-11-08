package com.andrew.messenger.mapper;

import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.dto.UserReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User user) {
        return new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getBirthDate(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole(),
                user.getImage()
        );
    }
}
