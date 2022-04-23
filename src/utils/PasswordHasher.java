package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * La clase PasswordHasher contiene un método con el que se cifran contraseñas.
 * 
 * @author elisa
 *
 */
public class PasswordHasher {

	/**
	 * Método que cifra una cadena de caracteres que corresponde a una contraseña.
	 * 
	 * @param passwordToHash - Cadena de caracteres que corresponde a la contraseña
	 *                       que se desea cifrar
	 * @param salt           - Cadena de caracteres aleatoria que se añade a las
	 *                       contraseñas para cifrarlas
	 * @return Cadena de caracteres de la contraseña cifrada.
	 */
	public static String hashIt(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

}
