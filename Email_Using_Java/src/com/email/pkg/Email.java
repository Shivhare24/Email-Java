package com.email.pkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("property.prop");
		Properties p = new Properties();
		p.load(fis);
		String Mail_Receipent =p.getProperty("Mail_Receipent");
		String Mail_Sender =p.getProperty("Mail_Sender");
		String Mail_UserName =p.getProperty("Mail_UserName");
		String Mail_Password = p.getProperty("Mail_Password");
		String SMTP_Host = p.getProperty("SMTP_Host");
		String SMTP_TTLS =p.getProperty("SMTP_TTLS");
		String SMTP_Transport =p.getProperty("SMTP_Transport");
		String SMTP_Port =p.getProperty("SMTP_Port");
		String SMTP_Auth =p.getProperty("SMTP_Auth");
		String SMTP_Debug =p.getProperty("SMTP_Debug");
		
		String Mail_Subject =p.getProperty("Mail_Subject");
		String Mail_Text = p.getProperty("Mail_Text");
		
		
		
		
		try {		Properties prop = System.getProperties();
					prop.setProperty("mail.smtp.host",SMTP_Host);
					prop.setProperty("mail.smtp.starttls.enable",SMTP_TTLS);
					prop.setProperty("mail.smtp.starttls.required",SMTP_TTLS);
					prop.setProperty("mail.from",Mail_Sender);
					prop.setProperty("mail.transport.protocol",SMTP_Transport);
					prop.setProperty("mail.smtp.port",SMTP_Port);
					prop.setProperty("mail.smtp.auth",SMTP_Auth);
					prop.setProperty("mail.smtp.debug",SMTP_Debug);
					
					System.out.println(prop);
					Session session = Session.getDefaultInstance(prop,new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(Mail_UserName,Mail_Password);
						}
					});
					
					
					System.out.println(session);
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(Mail_Sender));
					message.addRecipient(Message.RecipientType.TO,new InternetAddress(Mail_Receipent));
					message.setSubject(Mail_Subject);
					message.setText(Mail_Text);
					
					
					Transport transport = session.getTransport(SMTP_Transport);
					System.out.println("about to connect");
					transport.connect();
					transport.sendMessage(message,message.getAllRecipients());
					transport.close();
					System.out.println("mail sent successfully");
		}
		catch (Exception e) {
			e.printStackTrace();
	        }
    }
}