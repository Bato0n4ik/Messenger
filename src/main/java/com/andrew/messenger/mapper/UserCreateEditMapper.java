package com.andrew.messenger.mapper;

import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;
import java.util.function.Predicate;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User>{

    private final PasswordEncoder passwordEncoder;


    @Override
    public User map(UserCreateEditDto fromObject) {
        User toObject = new User();
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(UserCreateEditDto fromObject, User user) {

        if(fromObject.getUsername() != null) {
            user.setUsername(fromObject.getUsername());
        }

        if(fromObject.getFirstname() != null) {
            user.setFirstname(fromObject.getFirstname());
        }

        if(fromObject.getLastname() != null){
            user.setLastname(fromObject.getLastname());
        }

        if(fromObject.getBirthDate() != null){
            user.setBirthDate(fromObject.getBirthDate());
        }

        if(fromObject.getRole() != null){
            user.setRole(fromObject.getRole());
        }


        Optional.ofNullable(fromObject.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));

        //log.info("password before encoding: {}", fromObject.getRawPassword());

        Optional.ofNullable(fromObject.getRawPassword())
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
