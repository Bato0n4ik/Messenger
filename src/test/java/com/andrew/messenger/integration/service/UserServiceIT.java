package com.andrew.messenger.integration.service;


import com.andrew.messenger.database.entity.Role;
import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.integration.IntegrationTestBase;
import com.andrew.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void create() {

        UserCreateEditDto user = new UserCreateEditDto(
                "test.47@gmail.com",
                "test",
                LocalDate.now(),
                "Vladimir",
                "Testes",
                Role.USER,
                new MockMultipartFile("image", new byte[0]));

        var actualResult = userService.create(user);

        assertTrue(actualResult.getId() > 0);
        assertEquals(actualResult.getUsername(), user.getUsername());
        assertEquals(actualResult.getBirthDate(), user.getBirthDate());
        assertEquals(actualResult.getFirstname(), user.getFirstname());
        assertEquals(actualResult.getLastname(), user.getLastname());
        assertSame(actualResult.getRole(), user.getRole());

    }

    @Test
    void update(){

        UserCreateEditDto user = new UserCreateEditDto(
                "test.47@gmail.com",
                "test123",
                LocalDate.of(1990, 1, 1),
                "Vladimir",
                "Testes",
                Role.ADMIN,
                new MockMultipartFile("image", new byte[0]));

        userService.update(USER_ID, user)
                 .ifPresent( actualUser -> {
                     assertEquals(actualUser.getUsername(), user.getUsername());
                     assertEquals(actualUser.getBirthDate(), user.getBirthDate());
                     assertEquals(actualUser.getFirstname(), user.getFirstname());
                     assertEquals(actualUser.getLastname(), user.getLastname());
                     assertSame(actualUser.getRole(), user.getRole());
                 });
    }

    @Test
    void delete(){
        assertTrue(userService.delete(USER_ID));
    }


}