package by.max.spring.database.repository;

import by.max.spring.database.entity.User;
import by.max.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {
    List<User> findAllByFilter(UserFilter filter);
}
