package com.lotorojo.emailSender.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/awake")
public class NapController {

@GetMapping
    public String wakeUp(){
    return "[ Bosteza! ]";
}

}
