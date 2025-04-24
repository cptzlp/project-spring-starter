package by.max.spring.service;



import by.max.spring.database.repository.CompanyRepository;
import by.max.spring.dto.CompanyReadDto;

import by.max.spring.listener.AccessType;
import by.max.spring.listener.EntityEvent;
import by.max.spring.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final CompanyReadMapper companyReadMapper;

    public List<CompanyReadDto> findAll() {
        return companyRepository.findAll().stream()
                .map(companyReadMapper::map)
                .toList();
    }

    public Optional<CompanyReadDto> findById(Integer id) {
        return companyRepository.findById(id).map(entity -> {
            applicationEventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
            return new CompanyReadDto(entity.getId(), null);
        });
    }
}
