package com.lotorojo.emailSender.controller;


import com.lotorojo.emailSender.model.contactForm.RequestContactForm;
import com.lotorojo.emailSender.model.contactForm.ResponseContactForm;
import com.lotorojo.emailSender.service.ContactFormServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactFormControllerTests {

private RequestContactForm requestContactForm;
private String recipient;
private ResponseContactForm responseContactForm;

    @BeforeEach
    void setUp(){

        requestContactForm = new RequestContactForm(
                "Alguien",
                "yo@alguien.com",
                "Contacto",
                "bla bla bla, bla, bla");

        recipient = "tu@alguien.com";

        responseContactForm = new ResponseContactForm(
                1L,
                "Alguien",
                "yo@alguien.com",
                "Contacto",
                "bla bla bla, bla, bla",
                "123123"
        );

    }


    @Mock
    private ContactFormServiceImpl contactFormService;

    @InjectMocks
    private ContactFormController formController;

    @Test
    void sendMail_mustReturnCreated(){

        when(contactFormService.processForm(requestContactForm, recipient)).thenReturn(responseContactForm);

        ResponseEntity<ResponseContactForm> response = formController.sendMail(requestContactForm, recipient);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseContactForm, response.getBody());


    }



}
