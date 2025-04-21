package by.max.spring.http.controller;


import by.max.spring.database.entity.Role;
import by.max.spring.dto.UserReadDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@SessionAttributes({"user"})
@RequestMapping("/api/v1")
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> getRoles(){
        return Arrays.asList(Role.values());
    }

    @GetMapping("hello")
    public String hello(Model model, UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @GetMapping("bye")
    public String bye(@SessionAttribute("user") UserReadDto user) {
        return "greeting/bye";
    }
}
