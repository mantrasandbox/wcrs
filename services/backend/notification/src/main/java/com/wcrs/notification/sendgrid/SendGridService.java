package com.wcrs.notification.sendgrid;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.wcrs.notification.config.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridService implements MailSender {

    @Value("${SENDGRID_API_KEY}")
    private String SENDGRID_API_KEY ;

    @Override
    public void sendEmail(String recipient, String subject, String body) {
        Email from =  new Email("akibuuka50@gmail.com");
        Email to = new Email(recipient);
        Content content = new Content("text/plain", body);

        Mail mail = new Mail(from,subject,to,content);

        SendGrid sendGrid = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();

        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            System.out.println("Status Code: "+response.getStatusCode());
            System.out.println("Response Body: "+response.getBody());
            System.out.println("Response Headers: "+response.getHeaders());

        }
        catch (IOException e){
            throw new RuntimeException("Failed to send Email: " + e.getMessage(), e);
        }
    }
}

