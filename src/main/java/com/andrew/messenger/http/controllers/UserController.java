package com.andrew.messenger.http.controllers;

import com.andrew.messenger.database.entity.Role;
import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;



    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return  userService.findById(id)
                .map(user -> {
                    model.addAttribute("user", user);
                    model.addAttribute("roles", Role.values());
                    return "user/user";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute  UserCreateEditDto user , Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user/registration";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/users/registration";
        }

        userService.create(user);
        return "redirect:/login";
    }

    @GetMapping("/{id}/update")
    public String redirectToUpdate(@PathVariable Long id, UserReadDto user , Model model) {
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute UserCreateEditDto user, Model model) {
        return userService.update(id, user)
                .map(entity -> { return "redirect:/users/" + id;})
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        if(userService.delete(id)){
            return "redirect:/login";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
