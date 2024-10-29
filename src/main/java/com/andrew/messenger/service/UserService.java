package com.andrew.messenger.service;


import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.database.repository.UserRepository;
import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.mapper.UserCreateEditMapper;
import com.andrew.messenger.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Optional<UserReadDto> findById(Long id){
        return userRepository.findById(id).map(userReadMapper::map);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.of(userRepository.findByUsername(username));
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto){
        return Optional.of(userCreateEditDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map)
                .orElseThrow();
    }


    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto user) {
        return Optional.of(userRepository.findById(id)
                .map(u -> userCreateEditMapper.map(user, u))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map)
                .orElseThrow());
    }

    @Transactional
    public boolean delete(Long id) {
           return  userRepository.findById(id).map(user -> {
               userRepository.delete(user);
               userRepository.flush();
               return true;
           }).orElse(false);
    }


}
