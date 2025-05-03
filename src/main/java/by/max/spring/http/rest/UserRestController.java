package by.max.spring.http.rest;


import by.max.spring.database.entity.Role;
import by.max.spring.dto.PageResponse;
import by.max.spring.dto.UserCreateEditDto;
import by.max.spring.dto.UserFilter;
import by.max.spring.dto.UserReadDto;
import by.max.spring.service.CompanyService;
import by.max.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final CompanyService companyService;


    @GetMapping
    public PageResponse<UserReadDto> findAll(UserFilter filter, @PageableDefault(size = 5) Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }


    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated @RequestBody UserCreateEditDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id,
                              @RequestBody @Validated UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
