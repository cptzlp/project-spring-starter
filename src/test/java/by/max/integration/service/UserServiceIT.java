package by.max.integration.service;


import by.max.annotation.IT;
import by.max.spring.database.entity.Role;
import by.max.spring.database.repository.pool.ConnectionPool;
import by.max.spring.dto.UserCreateEditDto;
import by.max.spring.dto.UserReadDto;
import by.max.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;
    private final UserService userService;

    @Test
    public void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(10);

    }

    @Test
    public void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("max@gmail.com", user.getUsername()));
    }

    @Test
    public void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userDto.getRole(), actualResult.getRole());

    }

    @Test
    public void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );

        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);
        assertTrue(actualResult.isPresent());

        actualResult.ifPresent(user ->{
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });


    }

    @Test
    public void delete() {
        assertFalse(userService.delete(-126L));
        assertTrue(userService.delete(USER_1));
    }
}
