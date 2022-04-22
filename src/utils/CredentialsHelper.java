package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CredentialsHelper {

	private final static String appSettingsFile = "appsettings.json";
	
	public static String readUserDB() {
		return readFromSettingsFile("username");
	}
	
	public static String readPasswordDB() {
		return readFromSettingsFile("password");
	}
	
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
