package com.lotorojo.emailSender.model.recipient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

        boolean existsByEmail(String email);
        Optional<Recipient> findByEmail(String email);
        Optional<Recipient> findByPersonalString(String personalString);
        Optional<Recipient> findByPersonalStringOrEmail(String personalString, String email);
}
