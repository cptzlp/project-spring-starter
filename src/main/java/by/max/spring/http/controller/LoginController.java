package by.max.spring.http.controller;

import by.max.spring.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "user/login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("login") LoginDto loginDto){
        return "redirect:/login";
    }
}
