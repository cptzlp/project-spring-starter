package by.max.spring.database.repository;


import by.max.spring.database.entity.Role;
import by.max.spring.database.entity.User;

import by.max.spring.dto.IPersonalInfo;
import by.max.spring.dto.PersonalInfo;
import by.max.spring.dto.UserFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository, QuerydslPredicateExecutor<User> {

    List<User> findFirst3By(Sort sort);

    Slice<User> findAllBy(Pageable pageable);

    @Query(
            value = "select u.firstname, u.lastname, u.birth_date from users u where company_id = :companyId",
            nativeQuery = true
    )
    List<IPersonalInfo> findAllByCompanyId(Integer companyId);

    //HQL
    @Query("select u from User u where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllByFirstnameContainingAndLastnameContaining(String firstname, String lastname);

    @Query(name = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.role = :role where u.id in (:ids)")
    int updateRole(Role role, Long... ids);


}

