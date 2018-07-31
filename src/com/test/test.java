package com.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.admin.EnvoyerMail;

public class test {

	public static void sendEmail(String to)
    {
        final String username = "vianneyba@gmail.com";
        final String password = "R8wsLRY5";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vianneyba@free.fr"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
            message.setSubject("A testing mail header !!!");
            message.setText("Dear Mail Crawler,"
                + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } 

        catch (MessagingException e) 
        {
           System.out.println(e.getMessage());
            System.out.println("Username or Password are incorrect ... exiting !");
        }
    }


    public static void main(String[] args) 
    {
        String to = "vianneyba@free.fr";
        sendEmail(to);
    }

}
