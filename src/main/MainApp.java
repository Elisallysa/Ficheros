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

import models.Show;

public class MainApp {

	public static void main(String[] args) {

		File f = new File("netflix_titles.csv");
		Scanner sc = null;
		String[] show = new String[12];
		
		Comparator<Show> ComparadorYear = (Show show1, Show show2) -> show1.getRelease_year().compareTo(show2.getRelease_year());

		
		List<Show> shows = new LinkedList<Show>();

		try {

			sc = new Scanner(f, "UTF-8");

			while (sc.hasNext()) {

				String s = sc.nextLine();

				// Imprimimos línea por línea para identificar en qué línea falla:
				// System.out.println(s);

				show = s.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);

				String id = show[0];
				String type = show[1];
				String title = show[2];
				String director = show[3];
				String cast = show[4];
				String country = show[5];
				String date = show[6];
				String year = show[7];
				String rating = show[8];
				String duration = show[9];
				String list = show[10];
				String description = show[11];

				Show infoShow = new Show(id, type, title, director, cast, country, date, year, rating, duration, list,
						description);

				shows.add(infoShow);
				
			}

			shows.sort(ComparadorYear);
			System.out.println(
					shows.get(0).toString() + "\n" + shows.get(200).toString() + "\n" + shows.get(7786).toString());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.close();

	}

}
