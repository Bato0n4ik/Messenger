package com.andrew.messenger.mapper;

import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User>{


    @Override
    public User map(UserCreateEditDto fromObject) {
        User toObject = new User();
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateEditDto fromObject, User user) {
        user.setUsername(fromObject.getUsername());
        user.setFirstname(fromObject.getFirstname());
        user.setLastname(fromObject.getLastname());
        user.setBirthDate(fromObject.getBirthDate());
        user.setRole(fromObject.getRole());
    }

    @Override
    public User map(UserCreateEditDto newObject, User Object) {
        copy(newObject, Object);
        return Object;
    }
}
