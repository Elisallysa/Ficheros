package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Show;

public class ShowDAO extends AbstractDAO {

	/**
	 * 
	 * @param show
	 */
	public void insert(Show show) {
		final String INSERT = "INSERT INTO shows VALUES ('" + show.getShow_id() + "', '" + show.getType() + "', '"
				+ show.getTitle() + "', '" + show.getDirector() + "', '" + show.getCast() + "', '"
				+ show.getCountry() + "', '" + show.getDate_added() + "', '" + show.getRelease_year() + "', '"
				+ show.getRating() + "', '" + show.getDuration() + "', '" + show.getListed_in() + "', '"
				+ show.getDescription() + "')";
		try {
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Show> getAll() {
		final String QUERY = "SELECT * FROM shows";
		var shows = new ArrayList<Show>();
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				var id = rs.getString("show_id");
				var tipo = rs.getString("show_type");
				var titulo = rs.getString("title");
				var direc = rs.getString("director");
				var reparto = rs.getString("cast");
				var pais = rs.getString("country");
				var fecha = rs.getString("date_added");
				var estreno = rs.getString("release_year");
				var categoria = rs.getString("rating");
				var duracion = rs.getString("duration");
				var generos = rs.getString("listed_in");
				var sinopsis = rs.getString("descripcion");
				Show s = new Show(id, tipo, titulo, direc, reparto, pais, fecha, estreno, categoria, duracion, generos, sinopsis);
				shows.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param busqueda
	 * @return
	 */
	public ArrayList<Show> search(int searchFilter, String busqueda) {
	
		String campo = "";
		
		switch (searchFilter) {
		case 0:
			campo = "title";
			break;
		case 1:
			campo = "country";
			break;
		case 2:
			campo = "director";
			break;
		case 3:
			campo = "release_year";
			break;
		default:
			break;
		}
		
		final String QUERY = "SELECT * FROM shows WHERE "+campo+" like '%"+busqueda+"%'";
		var shows = new ArrayList<Show>();
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				var id = rs.getString("show_id");
				var tipo = rs.getString("show_type");
				var titulo = rs.getString("title");
				var direc = rs.getString("director");
				var reparto = rs.getString("cast");
				var pais = rs.getString("country");
				var fecha = rs.getString("date_added");
				var estreno = rs.getString("release_year");
				var categoria = rs.getString("rating");
				var duracion = rs.getString("duration");
				var generos = rs.getString("listed_in");
				var sinopsis = rs.getString("descripcion");
				Show s = new Show(id, tipo, titulo, direc, reparto, pais, fecha, estreno, categoria, duracion, generos, sinopsis);
				shows.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}
	
}
