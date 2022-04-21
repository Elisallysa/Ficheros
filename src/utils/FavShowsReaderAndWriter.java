package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ShowDAO;
import models.Show;

public class FavShowsReaderAndWriter {
	
	public void addFavShow(ArrayList<Show> favList, String username, String fileName, String separator) {
		try {
			
			Boolean newFile = new File ("src/assets/userFiles/"+username+"_"+fileName+".csv").exists();
			
			FileWriter fw = new FileWriter("src/assets/userFiles/"+username+"_"+fileName+".csv", true);
			
			if (!newFile) {
				if (!separator.isBlank()) {
					fw.write("SEPARATOR:"+separator);
				} else {
					fw.write("SEPARADOR:TABULATOR");
				}
				
			}
			
		    var lineas = Files.readAllLines(new File("src/assets/userFiles/"+username+"_"+fileName+".csv").toPath(), StandardCharsets.UTF_8);
		    
		    for (Show s : favList) {
		    	if (!lineas.toString().contains(s.getShow_id())) {
		    			fw.write(s.toCSVString(separator)+"\n");
		    		}
		    }
		    
//		    if (separator.isBlank()) {
//		    	lineas.toString().replace("/t", "\t");
//		    }
			
			fw.flush();
			fw.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	
	public static void main(String[] args) {
		
			try {
				
				File fi = new File ("src/assets/userFiles/eli.csv");
				System.out.println(new File ("src/assets/userFiles/caca.csv").exists());
				
				
				FileReader fileR = new FileReader(fi);
				
			    BufferedReader bufR = new BufferedReader(fileR);
			    
			    FileWriter fiwr = new FileWriter("src/assets/userFiles/eli.csv", true);
			    
			    var lines = Files.readAllLines(new File("src/assets/userFiles/eli.csv").toPath(), StandardCharsets.UTF_8);
	//			System.out.println(lines);
				
				fiwr.write("hola\thola");
				
			    
//				var trozos = bufR.readLine().split("([,;\t])", -1);
//				trozos = bufR.readLine().split("([,;\t])", -1);
//				var trozos2 = bufR.readLine().split("([,;\t])", -1);
//				
//				System.out.println(trozos[3]);
//				System.out.println(trozos2[1]);
				
				fiwr.flush();
				fiwr.close();
				
				System.out.println(bufR.readLine());
				
				}catch (Exception e){
					e.printStackTrace();
				}
		
			}
	
	public String getSeparator(String username, String fileName) {
		try {
			File f = new File ("src/assets/userFiles/"+username+"_"+fileName+".csv");
			FileReader fr = new FileReader(f);
			
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			var trozos = br.readLine().split("([:])", -1);
			
			if (trozos[1].equals("TABULATOR")) {
				trozos[1]="\t";
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
			
			Scanner sc = new Scanner(new File("src/assets/userFiles/"+username+"_"+fileName+".csv"), "UTF-8");
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
	
	
	
	

