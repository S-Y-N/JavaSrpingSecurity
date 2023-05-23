package com.example.demo_2305.Controllers;

import com.example.demo_2305.domain.UserDTO;
import com.example.demo_2305.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class RegistrationController {


    private final UserService _userService;

    @GetMapping("/registration")
    public ModelAndView getRegistration(){
        return new ModelAndView("user/registration","user",new UserDTO());
    }
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/registration";
        }
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            model.addAttribute("passwordError", "Invalid password");
            return "user/registration";
        }
        if(!_userService.registerUser(userDTO)){
            model.addAttribute("usernameForm","Username already exists");
            return "user/registration";
        }
        return "home/index";
    }

}
