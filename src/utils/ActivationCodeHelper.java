package utils;

import dao.UserDAO;
import models.User;

/**
 * La clase ActivationCodeHelper contiene la lógica para generar y establecer un
 * código de activación a un usuario.
 * 
 * @author elisa
 *
 */
public class ActivationCodeHelper {

	// Objeto de la clase UserDAO inicializado:
	static UserDAO userDAO = new UserDAO();

	/**
	 * Método que genera una cadena de caracteres con un código de activación de
	 * máximo 6 cifras.
	 * 
	 * @return - Cadena de caracteres con el código de activación generado.
	 */
	public static String generateActivationCode() {

		try {
			int num = 0;
			for (int i = 0; i < 6; i++) {
				while (num == 0) { // Para evitar la posibilidad remota de que el número sea 0.
					num = (int) (Math.random() * 1000000);
				}
			}
			return String.valueOf(num);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método que llama el método de la clase UserDAO para establecer un código de
	 * activación a un usuario.
	 * 
	 * @param user - Objeto de la clase User que corresponde a un usuario almacenado
	 *             en la BD.
	 * @param code - Cadena de caracteres que corresponde al código que se quiere
	 *             almacenar en la BD.
	 */
	public static void setActivationCode(User user, String code) {
		userDAO.setActivationCode(user, code);
	}

}
