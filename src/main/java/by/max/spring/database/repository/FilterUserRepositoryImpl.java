package by.max.spring.database.repository;

import by.max.spring.database.entity.User;
import by.max.spring.dto.QPredicates;
import by.max.spring.dto.UserFilter;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import lombok.RequiredArgsConstructor;



import java.util.List;

import static by.max.spring.database.entity.QUser.user;


@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository{

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();

    }
}
