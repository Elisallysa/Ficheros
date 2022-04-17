package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import dao.ShowDAO;
import models.Show;
import utils.CredentialsHelper;
import views.LoginView;

public class MainApp {

	public static void main(String[] args) {

		new LoginView();

	}
	
	/**
	 * M�todo para leer el archivo .csv, crear Objetos de la clase Show y almacenarlos en la BD.
	 * Solo se ejecutar� una vez, pero se mantiene aqu� para futuras consultas.
	 */
	public void insertAllShows() {
		File f = new File("netflix_titles.csv");
		Scanner sc = null;
		String[] show = new String[12];
		
		// Comparador para ordenar los shows por a�o por si se necesitara:
		// Comparator<Show> ComparadorYear = (Show show1, Show show2) -> show1.getRelease_year().compareTo(show2.getRelease_year());
		
		List<Show> shows = new LinkedList<Show>();

		try {

			sc = new Scanner(f, "UTF-8");

			while (sc.hasNext()) {

				String s = sc.nextLine();

				show = s.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);

				String id = show[0];
				String type = show[1].replace('"', '_'); // Reemplazamos las comillas porque producen conflicto en la inserci�n en la BD.
				String title = show[2].replace('"', '_');
				String director = show[3].replace('"', '_');
				String cast = show[4].replace('"', '_');
				String country = show[5].replace('"', '_');
				String date = show[6].replace('"', '_');
				String year = show[7].replace('"', '_');
				String rating = show[8].replace('"', '_');
				String duration = show[9].replace('"', '_');
				String list = show[10].replace('"', '_');
				String description = show[11].replace('"', '_');

				Show infoShow = new Show(id, type, title, director, cast, country, date, year, rating, duration, list,
						description);

				
				ShowDAO showDAO = new ShowDAO();
				if (!showDAO.isStored(infoShow)) {
					System.out.println(infoShow);
				showDAO.insert(infoShow);
				}
				// Para a�adir los shows a la lista:
				// shows.add(infoShow);
				
			}

			// Si queremos ordenar la lista de shows:
			// shows.sort(ComparadorYear);
			
			// De esta forma compruebo que se han almacenado correctamente en la lista de shows:
			// System.out.println(
			//		shows.get(0).toString() + "\n" + shows.get(200).toString() + "\n" + shows.get(7786).toString());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();
	}

}
