package com.andrew.messenger.http.rest;

import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable Long id) {
        return  userService.findById(id)
                .map(user -> ResponseEntity.ok()
                        .body(user))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<UserReadDto> create(@Validated @RequestBody UserCreateEditDto user) {
        return Optional.ofNullable(userService.create(user))
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable Long id) {

        return userService.findAvatar(id)
                .map(image -> {
                    log.info("Avatar is found, size: {}", image.length);
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_OCTET_STREAM_VALUE)
                            .contentLength(image.length)
                            .body(image);
                })
                .orElseGet(()->{
                    log.error("Avatar not found in DB!");
                    return ResponseEntity.notFound().build();
                });

    }


    @PostMapping("/{id}/update")
    public ResponseEntity<UserReadDto> update(@PathVariable Long id, @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .map(entity -> ResponseEntity.ok().body(entity))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return userService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }
}
