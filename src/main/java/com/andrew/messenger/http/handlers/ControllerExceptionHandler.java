package com.andrew.messenger.http.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.andrew.messenger.http.controllers")
public class ControllerExceptionHandler{
    //@ExceptionHandler(Exception.class)
   // public String handleException(Exception ex,  Model model){
   //     log.error(ex.getMessage());
   //     model.addAttribute("exception", ex);
   //     return "errors/error500";
   // }
}
