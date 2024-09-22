package com.andrew.messenger.integration.service;


import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.integration.IntegrationTestBase;
import com.andrew.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private final UserService userService;
    private static final Long USER_ID = 1L;

    @Test
    void findById(){
        Optional<UserReadDto> mayBeUser = userService.findById(USER_ID);
        assertTrue(mayBeUser.isPresent());
        mayBeUser.ifPresent(user -> assertEquals(user.getUsername(), "petya_petuchkov.47@gmail.com"));
    }
}