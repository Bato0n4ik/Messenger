package com.andrew.messenger.service;


import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.database.repository.UserRepository;
import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.mapper.UserCreateEditMapper;
import com.andrew.messenger.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.Tika;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

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
            Tika tika = new Tika();

            String detectedType = tika.detect(image.getInputStream());
            if (!detectedType.startsWith("image/")) {
                throw new IllegalArgumentException("File is not an image!");
            }

            BufferedImage originalImage = ImageIO.read(image.getInputStream());
            if (originalImage == null) {
                throw new IllegalArgumentException("Invalid format or corrupted file");
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String format = detectedType.split("/")[1];
            ImageIO.write(originalImage, format, outputStream);


            imageService.upload(image.getOriginalFilename(), outputStream.toByteArray());
        }
    }

    public Optional<byte[]> findAvatar(Long id){
        return userRepository.findById(id)
                .flatMap(user -> imageService.getImage(user.getImage()));
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userCreateEditDto){
        return Optional.of(userCreateEditDto)
                .map(createDto -> {
                    uploadImage(createDto.getImage());
                    log.info("image uploaded");
                    return userCreateEditMapper.map(createDto);
                })
                .map(userRepository::save)
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var repositoryUser = Optional.ofNullable(userRepository.findByUsername(username));
        return repositoryUser.map(user -> new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(user.getRole())))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
