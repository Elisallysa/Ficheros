package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import dao.ShowDAO;
import models.Show;

public class FavShowsWriter {
	
	public void addFavShow(Show show, String username, String fileName, String separator) {
		try {
			
			File f = new File ("src/assets/userFiles/"+username+"_"+fileName+".csv");
			
			FileWriter fw = new FileWriter("src/assets/userFiles/"+username+"_"+fileName+".csv", true);
			
		    var lineas = Files.readAllLines(new File("src/assets/userFiles/"+username+"_"+fileName+".csv").toPath(), StandardCharsets.UTF_8);
			
		    if (!lineas.toString().contains(show.getShow_id())) {

		    	fw.write(show.toCSVString(separator)+"\n");
		    	
		    }
			
			fw.flush();
			fw.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	

