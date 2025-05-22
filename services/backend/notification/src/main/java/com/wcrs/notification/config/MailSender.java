package com.wcrs.notification.config;

public interface MailSender {

    void sendEmail(String to, String subject, String body);
}
