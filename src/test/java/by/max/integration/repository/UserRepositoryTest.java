package by.max.integration.repository;


import by.max.annotation.IT;
import by.max.spring.database.entity.Role;
import by.max.spring.database.entity.User;
import by.max.spring.database.repository.UserRepository;
import by.max.spring.dto.IPersonalInfo;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@IT
@RequiredArgsConstructor
public class UserRepositoryTest {

    private final UserRepository userRepository;


    @Test
    void checkPageable(){
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Slice<User> slice = userRepository.findAllBy(pageable);
        slice.stream().forEach(System.out::println);
        assertFalse(slice.isEmpty());
        assertThat(slice).hasSize(2);
    }

    @Test
    void findAllByFirstnameContainingAndLastnameContaining(){
        List<User> maybeUsers = userRepository.findAllByFirstnameContainingAndLastnameContaining("e", "e");

        assertThat(maybeUsers).hasSize(3);
    }

    @Test
    void updateRole(){
        int result = userRepository.updateRole(Role.ADMIN, 2L, 4L);

        assertEquals(2, result);
    }

    @Test
    void checkProjections(){
        List<IPersonalInfo> users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(4);
    }

    @Test
    void findFirst3By(){
        List<User> maybeUsers = userRepository.findFirst3By(Sort.by("firstname")
                .and(Sort.by("lastname"))
                .descending());
        assertThat(maybeUsers).hasSize(3);
    }

}
