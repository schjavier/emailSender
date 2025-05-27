package com.lotorojo.emailSender.model.contactForm;

public record ResponseContactForm(
        Long id,
        String name,
        String email,
        String subject,
        String text,
        String encryptedMail ) {


}
