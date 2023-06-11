package com.mycompany.eldcare;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.*;

class EldCare {
    public static void sendMail(String recp) throws Exception {
        System.out.println("Preparing to send email");
        Properties prop = new Properties();
        
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        
        String myEmailID =  "eldcarehelp@gmail.com";
        String password = "ELDcareHELP123@";
        
        Session sesh = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmailID, password);
            }
        });
        Message msg = prepareMessage(sesh, myEmailID, recp);
        
        Transport.send(msg);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session sesh, String myEmailID, String recp) {
        try {
            Message msg = new MimeMessage(sesh);
            msg.setFrom(new InternetAddress(myEmailID));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recp));
            msg.setSubject("Hello World");
            msg.setText("Hello World!");
            return msg;
        } catch (MessagingException ex) {
            Logger.getLogger(EldCare.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String args[]) throws Exception {
        EldCare.sendMail("kartikeyasureka@gmail.com");
    }
}
