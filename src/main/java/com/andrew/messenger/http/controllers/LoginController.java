package com.andrew.messenger.http.controllers;

import com.andrew.messenger.database.entity.User;
import com.andrew.messenger.dto.LoginDto;
import com.andrew.messenger.service.UserService;
import com.andrew.messenger.validators.LoginValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    public final UserService userService;
    public final LoginValidator loginValidator;

    @GetMapping
    public String login(LoginDto loginDto, Model model){
        model.addAttribute("loginDto", loginDto);
        return "user/login";
    }

    @PostMapping
    public String loginPost(@ModelAttribute @Validated LoginDto loginDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult);
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            return "redirect:/login";
        }
        else if(!loginValidator.validate(loginDto)){
            return "redirect:/login";
        }

        return userService.findByUsername(loginDto.getUsername())
                .map(User::getId)
                .map(id -> "redirect:/users/"+ id)
                .orElseThrow(() -> new UsernameNotFoundException(loginDto.getUsername()));
    }
}
