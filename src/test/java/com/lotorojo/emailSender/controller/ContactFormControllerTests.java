package com.lotorojo.emailSender.controller;


import com.lotorojo.emailSender.model.contactForm.RequestContactForm;
import com.lotorojo.emailSender.model.contactForm.ResponseContactForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ContactFormControllerTests {

private RequestContactForm requestContactForm;
private String addressee;

    @BeforeEach
    void setUp(){

        requestContactForm = new RequestContactForm(
                "Alguien",
                "yo@alguien.com",
                "Contacto",
                "bla bla bla, bla, bla");

    addressee = "tu@alguien.com";
    }


    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private ContactFormController formController;

    @Test
    void sendMail_mustReturnCreated(){

        ResponseEntity<ResponseContactForm> response = formController.sendMail(this.requestContactForm, this.addressee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Correo enviado", response.getBody());


    }


    @Test
    void sendMail_mustConfigureMailCorrectly(){

    formController.sendMail(requestContactForm, addressee);

        ArgumentCaptor<SimpleMailMessage> msgCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        verify(mailSender).send(msgCaptor.capture());

        SimpleMailMessage sentMessage = msgCaptor.getValue();

        String[] expectedAddressee = sentMessage.getTo();
        assertEquals("tu@alguien.com", expectedAddressee[0]);
        assertEquals("emailsender@lotorojo.com.ar", sentMessage.getFrom());
        assertEquals("Contacto", sentMessage.getSubject());
        assertEquals("yo@alguien.com", sentMessage.getReplyTo());

        assertTrue(sentMessage.getText().contains("Alguien Paso por tu web"));
        assertTrue(sentMessage.getText().contains("bla bla bla, bla, bla"));



    }



}
