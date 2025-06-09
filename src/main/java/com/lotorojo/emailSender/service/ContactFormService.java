package com.lotorojo.emailSender.service;

import com.lotorojo.emailSender.model.contactForm.RequestContactForm;
import com.lotorojo.emailSender.model.contactForm.ResponseContactForm;

public interface ContactFormService {

    ResponseContactForm processForm(RequestContactForm data, String recipient);
    String hashRecipient(String recipient);

}
