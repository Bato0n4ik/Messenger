package com.andrew.messenger.mapper;

import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.dto.UserCreateEditDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User>{

    private PasswordEncoder passwordEncoder;


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


        Optional.of(fromObject.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));

        Optional.of(fromObject.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }

    @Override
    public User map(UserCreateEditDto newObject, User Object) {
        copy(newObject, Object);
        return Object;
    }
}
