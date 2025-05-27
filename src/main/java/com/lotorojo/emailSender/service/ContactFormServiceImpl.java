package com.lotorojo.emailSender.service;

import com.lotorojo.emailSender.model.contactForm.ContactForm;
import com.lotorojo.emailSender.model.contactForm.RequestContactForm;
import com.lotorojo.emailSender.model.contactForm.ResponseContactForm;
import com.lotorojo.emailSender.model.recipient.Recipient;
import com.lotorojo.emailSender.model.recipient.RecipientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class ContactFormServiceImpl implements ContactFormService{

    private final RecipientRepository recipientRepository;
    private final MailService mailService;

    public ContactFormServiceImpl(RecipientRepository repository, MailService mailService){
        this.recipientRepository = repository;
        this.mailService = mailService;
    }

    @Override
    @Transactional
    public ResponseContactForm processForm(RequestContactForm data, String recipient) {

        Optional<Recipient> optionalRecipient = recipientRepository.findByPersonalString(recipient);

        if (optionalRecipient.isPresent()){

            mailService.sendMail(
                    new ContactForm(
                            data.name(),
                            data.email(),
                            data.subject(),
                            data.text()
                    ), optionalRecipient.get().getEmail());

                   return new ResponseContactForm(optionalRecipient.get().getId(), data.name(), data.email(), data.subject(), data.text(), recipient);
        } else {

            String hashedRecipient = hashRecipient(recipient);
            Recipient newRecipient = recipientRepository.save(new Recipient(recipient, hashedRecipient));
            mailService.sendFirstMail(newRecipient);

            return new ResponseContactForm(newRecipient.getId(), data.name(), data.email(), data.subject(), data.text(), hashedRecipient);

        }
    }

    @Override
    public String hashRecipient(String recipient) {
        StringBuilder hashedRecipient = new StringBuilder("MS");

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    recipient.getBytes(StandardCharsets.UTF_8)
            );

            for (byte b : encodedHash){
                hashedRecipient.append(b);
            }

        } catch (NoSuchAlgorithmException ex){

            System.out.println(ex.getMessage());
        }

        return hashedRecipient.subSequence(0,13).toString();
    }
}
