package com.andrew.messenger.service;


import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.database.repository.UserRepository;
import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.mapper.UserCreateEditMapper;
import com.andrew.messenger.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;
    private final ImageService imageService;

    public Optional<UserReadDto> findById(Long id){
        return userRepository.findById(id).map(userReadMapper::map);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.of(userRepository.findByUsername(username));
    }

    @SneakyThrows
    private void uploadImage(MultipartFile image) {
        if(!image.isEmpty()){
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    public Optional<byte[]> findAvatar(Long id){
        return userRepository.findById(id)
                .flatMap(user -> imageService.getImage(user.getImage()))
                ;
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto){
        return Optional.of(userCreateEditDto)
                .map(createDto -> {
                    uploadImage(createDto.getImage());
                    return userCreateEditMapper.map(createDto);
                })
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
