package by.max.unit;


import by.max.spring.database.entity.Company;
import by.max.spring.database.repository.CompanyRepository;
import by.max.spring.dto.CompanyReadDto;
import by.max.spring.listener.EntityEvent;
import by.max.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void findById(){
        Mockito.doReturn(Optional.of(new Company(COMPANY_ID, null, Collections.emptyMap())))
                .when(companyRepository).findById(COMPANY_ID);



        Mockito.verify(applicationEventPublisher).publishEvent(Mockito.any(EntityEvent.class));
    }
}
