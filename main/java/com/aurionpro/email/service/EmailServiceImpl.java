package com.aurionpro.email.service;

import com.aurionpro.email.entity.Email;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    private final String from = "dhruvrpandey01@gmail.com"; //  sender email
    private final String password = "lkfr shuz rrvo rhpu"; //  app password

    @Override
    public boolean sendEmail(Email email) {
        try {
            // Setup SMTP properties
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.auth", "true");

            // Auth session
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            session.setDebug(true); // Optional: logs for debug

            // Create MIME message
            MimeMessage message = new MimeMessage(session);

            // Set sender
            message.setFrom(new InternetAddress(from));

            // Set TO recipients
            addRecipients(message, Message.RecipientType.TO, email.getReceiverEmails());

            // Set CC recipients
            addRecipients(message, Message.RecipientType.CC, email.getCcEmails());

            // Set BCC recipients
            addRecipients(message, Message.RecipientType.BCC, email.getBccEmails());

            // Subject
            message.setSubject(email.getSubject());
            
            // Content (body)
            message.setContent(email.getContent(), "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully.");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to add recipients
    private void addRecipients(MimeMessage message, Message.RecipientType type, List<String> recipients) throws MessagingException {
        if (recipients != null && !recipients.isEmpty()) {
            for (String recipient : recipients) {
                message.addRecipient(type, new InternetAddress(recipient));
            }
        }
    }
}
