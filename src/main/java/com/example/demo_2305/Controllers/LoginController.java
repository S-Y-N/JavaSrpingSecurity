package com.example.demo_2305.Controllers;

import com.example.demo_2305.domain.UserDTO;
import com.example.demo_2305.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin(){return "user/login";}

}
