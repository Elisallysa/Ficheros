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

public class FavShowsReaderAndWriter {

	public void addFavShow(ArrayList<Show> favList, String username, String fileName, String separator) {
		try {

			Boolean newFile = new File("src/assets/userFiles/" + username + "_" + fileName + ".csv").exists();

			FileWriter fw = new FileWriter("src/assets/userFiles/" + username + "_" + fileName + ".csv", true);

			if (!newFile) {
				if (!separator.isBlank()) {
					fw.write("SEPARATOR:" + separator + "\n");
				} else {
					fw.write("SEPARADOR:TABULATOR\n");
				}

			}

			var lineas = Files.readAllLines(
					new File("src/assets/userFiles/" + username + "_" + fileName + ".csv").toPath(),
					StandardCharsets.UTF_8);

			for (Show s : favList) {
				if (!lineas.toString().contains(s.getShow_id())) {
					fw.write(s.toCSVString(separator) + "\n");
				}
			}

//		    if (separator.isBlank()) {
//		    	lineas.toString().replace("/t", "\t");
//		    }

			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
