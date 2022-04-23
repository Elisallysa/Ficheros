package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

/**
 * Clase que hereda de la clase abstracta AbstractDAO en la que se implementan
 * los métodos para interactuar con la tabla users de la BD Netflix.
 * 
 * @author elisa
 *
 */
public class UserDAO extends AbstractDAO {

	/**
	 * Método que comprueba si hay un registro en la BD con un nombre de usuario y
	 * una contraseña concretos.
	 * 
	 * @param usuario - Objeto de la clase User con un nombre y contraseña
	 * @return - true: el usuario se encuentra en la BD; false: no se encuentra en
	 *         la BD
	 */
	public boolean login(User usuario) {
		final String QUERY = "SELECT * FROM users " + "WHERE mail = '" + usuario.getMail() + "' AND " + "password = '"
				+ usuario.getPassword() + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método que introduce un nuevo registro en la tabla users de la BD con un
	 * nombre de usuario, mail y una contraseña
	 * 
	 * @param usuario - Objeto de la clase User que se quiere introducir en la BD.
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
	 * Método que comprueba que existe un usuario registrado con el mail
	 * introducido.
	 * 
	 * @param usuario - Objeto de la clase User del que se quiere comprobar el mail
	 *                de registro.
	 * @return true: un usuario con el mismo mail existe en la BD; false: no existe
	 *         ningún usuario registrado con ese mail.
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

	/**
	 * Método que comprueba que un usuario ha activado su cuenta mediante el código
	 * de activación.
	 * 
	 * @param usuario - Objeto de la clase User asociado a la cuenta que se quiere
	 *                comprobar si está activada.
	 * @return - true: la cuenta del usuario está activada; false: no está activada.
	 */
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

	/**
	 * Método que actualiza la contraseña de un usuario registrado en la BD.
	 * 
	 * @param usuario   - Objeto de la clase User que corresponde al usuario que
	 *                  desea cambiar su contraseña.
	 * @param inputCode - Código de activación que permite la ejecución del UPDATE.
	 */
	public void changePassword(User usuario, String inputCode) {
		final String QUERY = "SELECT * FROM users " + "WHERE mail = '" + usuario.getMail() + "' AND activation_code = '"
				+ inputCode + "'";
		final String UPDATE = "UPDATE users SET password = '" + usuario.getPassword() + "' WHERE mail = '"
				+ usuario.getMail() + "' AND activation_code = '" + inputCode + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			if (rs.next()) { // Si existe un registro con el mail y el código se ejecuta el UPDATE
				stmt.executeUpdate(UPDATE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que establece un código de activación para un usuario.
	 * 
	 * @param usuario - Objeto de la clase User que corresponde a un usuario
	 *                registrado en la BD.
	 * @param code    - Cadena de caracteres de máximo 6 cifras que se almacenará
	 *                como código de activación.
	 */
	public void setActivationCode(User usuario, String code) {
		final String UPDATE = "UPDATE users SET activation_code = " + code + " WHERE mail = '" + usuario.getMail()
				+ "'";
		try {
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que comprueba si el código de activación asociado a un usuario es el
	 * mismo que el introducido por parámetro.
	 * 
	 * @param usuario   - Objeto de la clase usuario del que se comprobará el código
	 *                  de activación.
	 * @param inputCode - Cadena de caracteres que se comparará con el código de
	 *                  activación almacenado en la BD.
	 * @return - true: si el código de activación almacenado en la BD es el mismo al
	 *         introducido por parámetro, false: los códigos son diferentes.
	 */
	public boolean checkActivationCode(User usuario, String inputCode) {
		final String QUERY = "SELECT activated FROM users " + "WHERE mail = '" + usuario.getMail()
				+ "'  AND activation_code = '" + inputCode + "'";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Método con el que se obtiene el nombre de usuario asociado a un usuario
	 * almacenado en la BD.
	 * 
	 * @param usuario - Objeto de la clase User.
	 * @return - Una cadena de caracteres con el nombre de usuario.
	 */
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

	/**
	 * Método que activa la cuenta de un usuario almacenado en la BD.
	 * 
	 * @param usuario   - Objeto de la clase User que contiene la información de un
	 *                  usuario registrado en la BD.
	 * @param inputCode - Cadena de caracteres con un código de activación que se
	 *                  comparará con el almacenado en el registro del usuario.
	 */
	public void activateUser(User usuario, String inputCode) {
		final String UPDATE = "UPDATE users SET activated = 1 WHERE mail = '" + usuario.getMail()
				+ "' AND activation_code = '" + inputCode + "'";
		try {
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
