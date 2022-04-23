package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Show;

/**
 * Clase que hereda de la clase abstracta AbstractDAO en la que se implementan
 * los métodos para interactuar con la tabla shows de la BD Netflix.
 * 
 * @author elisa
 *
 */
public class ShowDAO extends AbstractDAO {

	/**
	 * Método que inserta un show nuevo en la BD.
	 * 
	 * @param show - Película o serie que se quiere almacenar en la BD.
	 */
	public void insert(Show show) {
		final String INSERT = "INSERT INTO shows VALUES ('" + show.getShow_id() + "', '" + show.getType() + "', '"
				+ show.getTitle() + "', '" + show.getDirector() + "', '" + show.getCast() + "', '" + show.getCountry()
				+ "', '" + show.getDate_added() + "', '" + show.getRelease_year() + "', '" + show.getRating() + "', '"
				+ show.getDuration() + "', '" + show.getListed_in() + "', '" + show.getDescription() + "')";
		try {
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que almacena objetos de la clase Show obtenidos de la BD en un
	 * ArrayList.
	 * 
	 * @return - ArrayList de Show que se encuentran en la BD.
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
				var sinopsis = rs.getString("description");
				Show s = new Show(id, tipo, titulo, direc, reparto, pais, fecha, estreno, categoria, duracion, generos,
						sinopsis);
				shows.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}

	/**
	 * Método que almacena objetos de la clase Show que contienen la información
	 * introducida con un filtro de búsqueda en un ArrayList.
	 * 
	 * @param searchFilter - Tipo de dato del filtro de búsqueda.
	 * @param busqueda     - texto que debe contener el tipo de información
	 *                     seleccionado.
	 * @return - ArrayList de Show con las películas y series que contienen el texto
	 *         introducido en el tipo de dato seleccionado.
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

		final String QUERY = "SELECT * FROM shows WHERE " + campo + " like '%" + busqueda + "%'";
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
				var sinopsis = rs.getString("description");
				Show s = new Show(id, tipo, titulo, direc, reparto, pais, fecha, estreno, categoria, duracion, generos,
						sinopsis);
				shows.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}

	/**
	 * Método que comprueba si una película o serie está almacenada en la BD.
	 * 
	 * @param show - Objeto de la clase Show que corresponde a la película o serie
	 *             que se quiere comprobar.
	 * @return - true: la película o serie se encuentra en la BD; false: no se
	 *         encuentra en la BD.
	 */
	public boolean isStored(Show show) {
		final String QUERY = "SELECT * FROM shows " + "WHERE show_id = '" + show.getShow_id() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
