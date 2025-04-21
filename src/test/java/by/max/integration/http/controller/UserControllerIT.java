package by.max.integration.http.controller;


import by.max.annotation.IT;
import by.max.spring.dto.UserCreateEditDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static by.max.spring.dto.UserCreateEditDto.*;
import static by.max.spring.dto.UserCreateEditDto.Fields.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class UserControllerIT {

    @Autowired
    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", IsCollectionWithSize.hasSize(10)));
    }


    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "test@gmail.com")
                        .param(birthDate, "2025-04-21")
                        .param(firstname, "test")
                        .param(lastname, "testov")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }
}
