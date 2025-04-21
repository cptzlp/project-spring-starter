package by.max.integration.service;


import by.max.annotation.IT;
import by.max.spring.ApplicationRunner;
import by.max.spring.config.DatabaseProperties;
import by.max.spring.dto.CompanyReadDto;
import by.max.spring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@IT
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class CompanyServiceIT {
    private static final Integer COMPANY_ID = 1;


    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;


    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID, "Google");

        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

}
