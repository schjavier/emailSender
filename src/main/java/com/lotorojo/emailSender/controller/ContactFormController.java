package com.lotorojo.emailSender.controller;

import com.lotorojo.emailSender.model.contactForm.ResponseContactForm;
import com.lotorojo.emailSender.service.ContactFormService;
import com.lotorojo.emailSender.model.contactForm.RequestContactForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send")
public class ContactFormController {


    ContactFormService contactFormService;

    public ContactFormController(ContactFormService contactFormService){
        this.contactFormService = contactFormService;
    }


    @PostMapping("/{recipient}")
    public ResponseEntity<ResponseContactForm> sendMail (@RequestBody RequestContactForm contactForm,
                                            @PathVariable String recipient){

      ResponseContactForm response = contactFormService.processForm(contactForm, recipient);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
