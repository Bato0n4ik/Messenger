package com.andrew.messenger.http.controllers;

import com.andrew.messenger.database.entity.Role;
import com.andrew.messenger.dto.UserCreateEditDto;
import com.andrew.messenger.dto.UserReadDto;
import com.andrew.messenger.service.UserService;
import com.andrew.messenger.validation.groups.OnCreate;
import com.andrew.messenger.validation.groups.OnUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/users")
@RequiredArgsConstructor
@Controller
@Slf4j
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
    public String registration( Model model, @ModelAttribute  UserCreateEditDto user) {
        log.info("Registration endpoint: entering registration");
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        log.info("Registration endpoint : redirecting to registration form");
        return "user/registration";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated(OnCreate.class) UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        log.info("Create endpoint: entering in create endpoint!");
        if(bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors();
            log.error("Create endpoint: bindingResult has errors - {}", errors);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", errors);
            log.error("Create endpoint: redirecting to registration form again due to the fact that bindingResult has errors");
            return "redirect:/users/registration";
        }


        UserReadDto userReadDto = userService.create(user);
        System.out.println("User: " + userReadDto);
        log.info("Create endpoint: bindingResult hasn't errors, redirecting to /login endpoint");
        return "redirect:/login";
    }

    @GetMapping("/{id}/edit")
    public String redirectToUpdate(@PathVariable Long id, Model model) {

        if(!model.containsAttribute("user")){
            UserReadDto userReadDto = userService.findById(id).map(user -> {
                model.addAttribute("user", user);
                return user;
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        return "user/update";

    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute @Validated(OnUpdate.class) UserCreateEditDto user,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.error("Update endpoint: bindingResult has errors - {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);

            //return "redirect:/users/" + id + "/edit";
            return UriComponentsBuilder.fromPath("redirect:/user/{id}/edit")
                    .buildAndExpand(id)
                    .toUriString();
        }

        return userService.update(id, user)
                .map(entity -> "redirect:/users/" + id)
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
