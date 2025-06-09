package com.lotorojo.emailSender.service;


import com.lotorojo.emailSender.model.contactForm.ContactForm;
import com.lotorojo.emailSender.model.contactForm.RequestContactForm;
import com.lotorojo.emailSender.model.recipient.Recipient;
import org.springframework.stereotype.Service;


public interface MailService {

   void sendMail(ContactForm datos, String recipient);
   void sendFirstMail(Recipient recipient);
}
