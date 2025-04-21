package by.max.integration.repository;


import by.max.annotation.IT;
import by.max.spring.database.entity.Company;
import by.max.spring.database.repository.CompanyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
public class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 4;

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void findById(){
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);

    }


    @Test
    void save(){
        Company company = Company.builder()
                .name("Apple1")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }

    @Test
    void delete(){
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void checkFindByQueries(){
        companyRepository.findByName("Apple");
        List<Company> maybeCompanies = companyRepository.findAllByNameContainingIgnoreCase("a");


        assertThat(maybeCompanies).hasSize(3);
    }

}
