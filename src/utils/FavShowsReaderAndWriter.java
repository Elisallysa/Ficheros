package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Show;

/**
 * La clase FavShowsReaderAndWriter contiene los métodos con los que se escriben
 * y leen archivos .csv que contienen información de películas y series.
 * 
 * @author elisa
 *
 */
public class FavShowsReaderAndWriter {

	/**
	 * Añade uno o más shows a un archivo .csv.
	 * 
	 * @param favList   - ArrayList de objetos Show.
	 * @param username  - Cadena de caracteres con un nombre de usuario.
	 * @param fileName  - Cadena de caracteres con el nombre del archivo donde se
	 *                  quieren almacenar los shows.
	 * @param separator - Cadena de caracteres con el separador que se usará para
	 *                  separar la información de los shows en el archivo .csv.
	 */
	public void addFavShow(ArrayList<Show> favList, String username, String fileName, String separator) {
		try {

			Boolean fileExists = new File("src/assets/userFiles/" + username + "_" + fileName + ".csv").exists(); // Comprueba
																													// si
																													// existe
																													// un
																													// archivo
																													// de
																													// un
																													// usuario
																													// y
																													// con
																													// un
																													// nombre
																													// concreto.

			FileWriter fw = new FileWriter("src/assets/userFiles/" + username + "_" + fileName + ".csv", true); // Construye
																												// un
																												// FileWriter
																												// para
																												// el
																												// archivo
																												// con
																												// la
																												// codificación
																												// de
																												// caracteres
																												// por
																												// defecto.

			if (!fileExists) { // Si el archivo no existe
				if (!separator.isBlank()) {
					fw.write("SEPARATOR:" + separator + "\n"); // En la primera línea escribimos qué separador se ha
																// seleccionado
				} else { // Si el separador no tiene contenido (isBlank) significa que se seleccionó el
							// tabulador
					fw.write("SEPARADOR:TABULATOR\n"); // Lo escribimos con texto para poder leerlo más tarde
				}

			}

			var lineas = Files.readAllLines(
					new File("src/assets/userFiles/" + username + "_" + fileName + ".csv").toPath(),
					StandardCharsets.UTF_8);

			for (Show s : favList) {
				if (!lineas.toString().contains(s.getShow_id())) { // Comprueba que el archivo no contenga el show que
																	// se quiere añadir.
					fw.write(s.toCSVString(separator) + "\n");
				}
			}

			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que obtiene el separador de un archivo de favoritos .csv que ya
	 * existe.
	 * 
	 * @param username - Cadena de caracteres con el nombre de usuario activo.
	 * @param fileName - Nombre del archivo del que se quiere obtener el separador
	 *                 de información.
	 * @return Cadena de caracteres que corresponde al separador usado en el
	 *         archivo.
	 */
	public String getSeparator(String username, String fileName) {
		try {
			File f = new File("src/assets/userFiles/" + username + "_" + fileName + ".csv");
			FileReader fr = new FileReader(f);

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			var trozos = br.readLine().split("([:])", -1);

			if (trozos[1].equals("TABULATOR")) {
				trozos[1] = "\t";
			}

			return trozos[1];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Método con el que se lee un archivo .csv de favoritos para almacenarlos en un
	 * array de String.
	 * 
	 * @param username - Cadena de caracteres del nombre de usuario activo.
	 * @param fileName - Nombre del archivo del que se quiere obtener los shows.
	 * @return - Array de String que contendrá los shows almacenados en el archivo
	 *         .csv
	 */
	public String[] getFavList(String username, String fileName) {
		try {
			List<String> favList = new ArrayList<String>();

			Scanner sc = new Scanner(new File("src/assets/userFiles/" + username + "_" + fileName + ".csv"), "UTF-8");
			sc.nextLine();// File header
			while (sc.hasNextLine()) {
				favList.add(sc.nextLine());
			}
			String[] favArray = new String[favList.size()];

			return favList.toArray(favArray);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
