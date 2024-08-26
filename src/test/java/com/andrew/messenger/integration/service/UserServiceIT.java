package com.andrew.messenger.integration.service;

import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.integration.IntegrationTestBase;
import com.andrew.messenger.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class UserServiceIT extends IntegrationTestBase {

    private UserService userService;
    private final Long USER_ID = 1L;

    @Test
    public void checkFindById(){
        Optional<User> mayBeUser = userService.findById(USER_ID);
        assertTrue(mayBeUser.isPresent());
        mayBeUser.ifPresent(user -> assertEquals(user.getUsername(), "Boba47"));
    }
}