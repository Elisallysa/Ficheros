package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

public class UserDAO extends AbstractDAO {

	/**
	 * Comprueba que hay un registro en la BD con un nombre de usuario y una
	 * contrase�a
	 * 
	 * @param usuario - Objeto usuario con un nombre y contrase�a
	 * @return - true: si el usuario se encuentra en la BD; false: si no se
	 *         encuentra en la BD
	 */
	public boolean login(User usuario) {
		final String QUERY = "SELECT * FROM users " + "WHERE mail = '" + usuario.getMail() + "' AND "
				+ "password = '" + usuario.getPassword() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Introduce un nuevo registro en la tabla Users de la BD con un nombre de
	 * usuario y una contrase�a
	 * 
	 * @param usuario - Objeto user que se quiere introducir en la BD.
	 */
	public void register(User usuario) {
		final String INSERT = "INSERT INTO users (username, mail, password) VALUES ('" + usuario.getUsername() + "','"
				+ usuario.getMail() + "', '" + usuario.getPassword() + "');";
		try {
			stmt.executeUpdate(INSERT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Comprueba que existe un usuario registrado con el mail introducido.
	 * 
	 * @param usuario - Objeto de User del que se quiere comprobar el mail de
	 *                registro.
	 * @return true: un usuario con el mismo mail existe en la BD; false: no existe
	 *         ning�n usuario registrado con ese mail.
	 */
	public boolean isUser(User usuario) {
		final String QUERY = "SELECT * FROM users " + "WHERE mail = '" + usuario.getMail() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isActivated(User usuario) {
		final String QUERY = "SELECT * FROM users " + "WHERE mail = '" + usuario.getMail() + "' AND activated = 1";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void changePassword(User usuario, String inputCode) {
		final String QUERY = "SELECT * FROM users " + "WHERE mail = '" + usuario.getMail() + "' AND activation_code = '" + inputCode + "'";
		final String UPDATE = "UPDATE users SET password = '"+usuario.getPassword()+"' WHERE mail = '"
				+ usuario.getMail() + "' AND activation_code = '" + inputCode + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			if (rs.next()) {
			stmt.executeUpdate(UPDATE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setActivationCode(User usuario, String code) {
		final String UPDATE = "UPDATE users SET activation_code = "+code+" WHERE mail = '"
				+ usuario.getMail() +"'";
		try {
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkActivationCode(User usuario, String inputCode) {
		final String QUERY = "SELECT activated FROM users " + "WHERE mail = '" + usuario.getMail() + "'  AND activation_code = '" + inputCode+ "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String getUsername(User usuario) {
		final String QUERY = "SELECT username FROM users " + "WHERE mail = '" + usuario.getMail() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void activateUser(User usuario, String inputCode) {
		final String UPDATE = "UPDATE users SET activated = 1 WHERE mail = '"
				+ usuario.getMail() + "' AND activation_code = '" + inputCode + "'";
		try {
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
}
