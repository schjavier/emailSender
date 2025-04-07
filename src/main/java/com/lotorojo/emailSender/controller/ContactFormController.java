package com.lotorojo.emailSender.controller;

import com.lotorojo.emailSender.model.ContactForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send")
public class ContactFormController {

    JavaMailSender mailSender;

    public ContactFormController(JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }


    @PostMapping("/{addressee}")
    public ResponseEntity<String> sendMail (@RequestBody ContactForm contactForm,
                                                @PathVariable String addressee){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(addressee);
        message.setFrom("emailsender@lotorojo.com.ar");
        message.setSubject(contactForm.getSubject());
        message.setReplyTo(contactForm.getEmail());
        message.setText(contactForm.getName() +
                " Paso por tu web y te dejó este mensaje: " +
                contactForm.getText());

        mailSender.send(message);

        return ResponseEntity.status(HttpStatus.CREATED).body("Correo enviado");
    }


}
