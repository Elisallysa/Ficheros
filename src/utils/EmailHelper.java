package utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * La clase EmailHelper contiene los métodos necesarios para enviar mails
 * automáticos.
 * 
 * @author elisa
 *
 */
public class EmailHelper {

	// MAIL (Y CONTRASEÑA) DESDE EL QUE SE ENVIARÁN LOS MAILS:
	static String username = CredentialsHelper.readUserDB();
	static String password = CredentialsHelper.readPasswordDB();

	/**
	 * Método que contiene el asunto y cuerpo de texto para enviar mails a usuarios
	 * que desean cambiar su contraseña.
	 * 
	 * @param to - Dirección de E-Mail del destinatario.
	 */
	public static void SendForgotPassword(String to) {
		String forgotSubject = "Confimation Code";
		String forgotTemplate = "Someone requested to change the password linked to his E-mail.\nIf it wasn't you, please ignore this E-mail.";
		SendEmail(forgotSubject, forgotTemplate, to);
	}

	/**
	 * Método que contiene el asunto y cuerpo de texto para enviar mails a usuarios
	 * que se acaban de registrar.
	 * 
	 * @param to - Dirección de E-Mail del destinatario.
	 */
	public static void SendWelcome(String to, String activationCode) {
		String welcomeSubject = "Welcome aboard!";
		String welcomeTemplate = "Thanks for joining!\nTo get access to our application, please activate your account with this code: "
				+ activationCode + "";
		SendEmail(welcomeSubject, welcomeTemplate, to);
	}

	/**
	 * Método que contiene el asunto y cuerpo de texto para enviar mails a usuarios
	 * que solicitan un nuevo código de activación.
	 * 
	 * @param to             - Dirección de E-Mail del destinatario.
	 * @param activationCode - Cadena de caracteres con el nuevo código de
	 *                       activación
	 */
	public static void SendNewActivationCode(String to, String activationCode) {
		String newCodeSubject = "Hi again!";
		String newCodeTemplate = "This is your new activation code: " + activationCode + "";
		SendEmail(newCodeSubject, newCodeTemplate, to);
	}

	/**
	 * Método con la configuración que permite el envío de mails automáticos.
	 * 
	 * @param subject  - Cadena de caracteres con el asunto del mail.
	 * @param template - Cadena de caracteres con el cuerpo de texto del mail.
	 * @param to       - Cadena de caracteres con la dirección de E-Mail del
	 *                 destinatario.
	 */
	private static void SendEmail(String subject, String template, String to) {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.trust", "*");
		prop.put("mail.smtp.starttls.required", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password); // Autentica que se inicia sesión correctamente
																		// con el mail y contraseña recibidos.
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("infonetflix@mail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(template);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
