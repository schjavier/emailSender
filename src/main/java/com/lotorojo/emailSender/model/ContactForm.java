package com.lotorojo.emailSender.model;

public class ContactForm {

    private String name;
    private String email;
    private String subject;
    private String text;

    public ContactForm(String name, String email, String subject, String text){
        this.name = name;
        this.email = email;
        this.subject= subject;
        this.text = text;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getSubject() {
        return subject;
    }
    public String getText() {
        return text;
    }




}
