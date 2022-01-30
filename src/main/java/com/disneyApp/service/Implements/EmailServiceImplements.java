package com.disneyApp.service.Implements;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailServiceImplements {

    @Autowired
    private Environment env;

    @Value("${disney.disney.email.sender}")
    private String emailSender;

    @Value("${disney.disney.email.enabled}")
    private boolean enabled;


    public void sendWelcomeEmail(String to) {

        if(!enabled) {
            return;
        }

        String apiKey = env.getProperty("EMAIL_API_KEY");

        // Email Data
        Email fromEmail = new Email(emailSender);
        Email toEmail = new Email(to);
        Content content = new Content(
                                "text/plain",
                                "Pensaste que no podrias hacer el desfío");
        String subject = "Alkemy";
        // Create
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Error Trying To Send EMAIL");
        }

    }

}
