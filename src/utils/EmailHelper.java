package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.UserDAO;
import models.User;

public class EmailHelper {

	private UserDAO userDAO;
	static String username;
	static String password;
	
	public static void SendForgotPassword(String to) {
		String forgotSubject = "Confimation Code";
		String forgotTemplate = "Someone requested to change the password linked to his E-mail.\nIf it wasn't you, please ignore this E-mail.";
		SendEmail(forgotSubject, forgotTemplate, to);
	}
	
	public static void SendWelcome(String to) {
		String welcomeSubject = "Welcome aboard!";
		String welcomeTemplate = "Thanks for joining!\nGo and search your favourite shows now!";
		SendEmail(welcomeSubject, welcomeTemplate, to);
	}
	
	private static void SendEmail(String subject, String template, String to) {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(template);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	}
