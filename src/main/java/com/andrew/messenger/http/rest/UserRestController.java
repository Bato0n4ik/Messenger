package com.andrew.messenger.http.rest;

import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController("/api/v1/users")
@RequiredArgsConstructor
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

    @PutMapping("/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable Long id, @Validated @RequestBody UserCreateEditDto user) {
        return null;
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
