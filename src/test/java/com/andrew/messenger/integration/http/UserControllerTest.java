package com.andrew.messenger.integration.http;

import com.andrew.messenger.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
@WithMockUser(username="test.47@gmail.com", password = "test12345", authorities = {"ADMIN","USER"})
public class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final Long USERID = 1L;

    //@Test
    public void findById() throws Exception {
        mockMvc.perform(get("/users/" + USERID))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/user"))
                .andExpect(model().attributeExists("user"));
    }

    //@Test
    public void create () throws Exception {

    }

    //@Test
    public void update () throws Exception {

    }
}
