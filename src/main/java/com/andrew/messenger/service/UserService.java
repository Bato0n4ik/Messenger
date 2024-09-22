package com.andrew.messenger.service;


import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.database.repository.UserRepository;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;

    public Optional<UserReadDto> findById(Long id){
        return userRepository.findById(id).map(userReadMapper::map);
    }
}
