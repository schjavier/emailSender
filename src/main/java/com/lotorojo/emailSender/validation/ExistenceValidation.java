package com.lotorojo.emailSender.validation;

import com.lotorojo.emailSender.exceptions.RecipientValidationException;
import com.lotorojo.emailSender.model.recipient.Recipient;
import com.lotorojo.emailSender.model.recipient.RecipientRepository;

public class ExistenceValidation implements Validations<Recipient>{

    private final RecipientRepository recipientRepository;

    public ExistenceValidation(RecipientRepository recipientRepository){
        this.recipientRepository = recipientRepository;
    }

    @Override
    public void validate(Recipient recipient) {

       boolean existRecipient = recipientRepository.existsByEmail(recipient.getEmail());

       if (!existRecipient){
           throw new RecipientValidationException("Recipient unknown");
       }

    }
}
