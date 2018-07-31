package com.admin;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoyerMail {
	public final String FROM = "vianneyba@gmail.com";
	public final String PASSWORD = "R8wsLRY5";
	
	public EnvoyerMail(String uSujet, String uMessage, String uDestinataire) throws AddressException, MessagingException {
		
	
		 Properties properties = new Properties();
		 properties.put("mail.smtp.host", "smtp.gmail.com");
		 properties.put("mail.smtp.port", "587");
		 properties.put("mail.smtp.auth", "true");
		 properties.put("mail.smtp.starttls.enable", "true");

		 Session session = Session.getInstance(properties, new Authenticator() {
		     @Override
		     protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(FROM, PASSWORD);
		     }
		 });

		 MimeMessage message = new MimeMessage(session);
		 message.setFrom(new InternetAddress(FROM));
		 message.addRecipient(Message.RecipientType.TO, new InternetAddress(uDestinataire));
		 message.setSubject( uSujet );
		 message.setText( uMessage );

		 Transport.send(message);
		 System.out.println("Done");

	}
}
