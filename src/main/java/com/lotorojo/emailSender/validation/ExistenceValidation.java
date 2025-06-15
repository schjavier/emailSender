package com.lotorojo.emailSender.validation;

import com.lotorojo.emailSender.exceptions.RecipientValidationException;
import com.lotorojo.emailSender.model.recipient.Recipient;
import com.lotorojo.emailSender.model.recipient.RecipientRepository;
import org.springframework.stereotype.Component;

@Component
public class ExistenceValidation implements Validations<String>{

    private final RecipientRepository recipientRepository;

    public ExistenceValidation(RecipientRepository recipientRepository){
        this.recipientRepository = recipientRepository;
    }

    @Override
    public void validate(String recipient) {

       boolean existRecipient = recipientRepository.existsByEmail(recipient);

       if (existRecipient){
           throw new RecipientValidationException("Duplicate Recipient, please use your personal String");
       }

    }
}
