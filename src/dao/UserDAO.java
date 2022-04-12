package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

public class UserDAO extends AbstractDAO {

	/**
	 * Comprueba que hay un registro en la BD con un nombre de
	 * usuario y una contraseña
	 * 
	 * @param usuario - Objeto usuario con un nombre y contraseña
	 * @return - true: si el usuario se encuentra en la BD; false: si no se
	 *         encuentra en la BD
	 */
	public boolean login(User usuario) {
		final String QUERY = "SELECT username, password FROM users " + "WHERE username = '" + usuario.getUsername()
				+ "' and " + "password = '" + usuario.getPassword() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Introduce un nuevo registro en la tabla Users de la BD con un
	 * nombre de usuario y una contraseña
	 * 
	 * @param usuario - Objeto user que se quiere introducir en la BD.
	 */
	public void register(User usuario) {
		final String INSERT = "INSERT INTO users (username,password) VALUES ('" + usuario.getUsername() + "','"
				+ usuario.getPassword() + "');";
		try {
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
