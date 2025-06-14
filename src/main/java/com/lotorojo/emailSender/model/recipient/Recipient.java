package com.lotorojo.emailSender.model.recipient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Recipient {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String personalString;

    public Recipient(String email, String personalString) {
        this.email = email;
        this.personalString = personalString;
    }

    public Recipient(Long id, String email, String personalString) {
        this.id = id;
        this.email = email;
        this.personalString = personalString;
    }

    public Recipient() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalString() {
        return personalString;
    }

    public void setPersonalString(String personalString) {
        this.personalString = personalString;
    }
}
