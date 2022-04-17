package utils;

import dao.UserDAO;
import models.User;

public class EmailHelper {

	private UserDAO userDAO;
	
	public static void SendForgotPassword(String to) {
		//Buscar plantilla del forgot
		String forgotTemplate = "";
		SendEmail(forgotTemplate, to);
		//Enviar E-mail
	}
	
	public static void SendWelcome(String to ) {
		SendEmail();
		//Enviar E-mail
	}
	
	public void Login(User usuario) {
		userDAO.login(usuario);
	}
	
	private static void SendEmail(String template, String to) {
		
	}
	
}
