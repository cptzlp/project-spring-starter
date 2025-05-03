package by.max.spring.http.controller;


import by.max.spring.database.entity.Role;

import by.max.spring.dto.PageResponse;
import by.max.spring.dto.UserCreateEditDto;
import by.max.spring.dto.UserFilter;
import by.max.spring.dto.UserReadDto;
import by.max.spring.service.CompanyService;
import by.max.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;


    @GetMapping
    public String findAll(Model model, UserFilter filter,  @PageableDefault(size = 5) Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "user/users";
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    model.addAttribute("companies", companyService.findAll());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        UserReadDto dto = userService.create(user);
        return "redirect:/users/" + dto.getId();
    }

    //@PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated UserCreateEditDto user) {
        return userService.update(id, user)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //@DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/users";
    }


}
