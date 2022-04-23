package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * La clase CredentialsHelper contiene los métodos que reciben la información
 * almacenada en un archivo .json del usuario que envía mails automáticos.
 * 
 * @author elisa
 *
 */
public class CredentialsHelper {

	// Archivo del que se quiere obtener la información
	private final static String appSettingsFile = "appsettings.json";

	/**
	 * Método con el que se obtiene el mail del usuario.
	 * 
	 * @return - Cadena de caracteres con el mail del usuario.
	 */
	public static String readUserDB() {
		return readFromSettingsFile("username");
	}

	/**
	 * Método con el que se obtiene la contraseña del mail del usuario.
	 * 
	 * @return - Cadena de caracteres con la contraseña del mail del usuario.
	 */
	public static String readPasswordDB() {
		return readFromSettingsFile("password");
	}

	/**
	 * Método que recorre la información de un archivo para buscar una información
	 * específica.
	 * 
	 * @param keyword - palabra clave de la información que se busca.
	 * @return - Cadena de caracteres con la información que se busca.
	 */
	private static String readFromSettingsFile(String keyword) {
		List<String> list;

		try {
			list = Files.readAllLines(new File(appSettingsFile).toPath());
			String appsettingsContent = "";
			for (var l : list) {
				appsettingsContent += l;
			}

			JsonObject jsonObject = JsonParser.parseString(appsettingsContent).getAsJsonObject();

			return jsonObject.get(keyword).getAsString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
