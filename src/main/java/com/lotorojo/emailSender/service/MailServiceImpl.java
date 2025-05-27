package com.lotorojo.emailSender.service;

import com.lotorojo.emailSender.model.contactForm.ContactForm;
import com.lotorojo.emailSender.model.recipient.Recipient;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

    private final String SENDER = "emailsender@lotorojo.com.ar";
    private final String DEFAULT_TEXT = " paso por tu web y te dejó este mensaje: ";
    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(ContactForm datos, String recipient) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(recipient);
        message.setFrom(SENDER);
        message.setSubject(datos.getSubject());
        message.setReplyTo(datos.getEmail());
        message.setText(datos.getName() + DEFAULT_TEXT + datos.getText());

        javaMailSender.send(message);


    }

    @Override
    public void sendFirstMail(Recipient recipient) {

        String subject = "Bienvenido!";
        String text = "Gracias por utilizar nuestros servicios!\n" +
                "Aca abajo esta tu código personal:\n" +
                 recipient.getPersonalString() + " \nUsalo en tu formulario para" +
                "no exponer tu email. :) ";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(recipient.getEmail());
        message.setFrom(SENDER);
        message.setSubject(subject);
        message.setReplyTo(SENDER);
        message.setText(text);

        javaMailSender.send(message);

    }
}
