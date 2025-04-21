package by.max.spring.service;


import by.max.spring.database.entity.Company;
import by.max.spring.database.repository.CompanyRepository;
import by.max.spring.dto.CompanyReadDto;
import by.max.spring.listener.AccessType;
import by.max.spring.listener.EntityEvent;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CompanyService(CompanyRepository companyRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.companyRepository = companyRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id).map(entity -> {
            applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.getId(), null);
        });
    }
}
