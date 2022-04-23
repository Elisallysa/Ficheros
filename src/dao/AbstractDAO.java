package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase abstracta de DAO con configuración básica que heredarán las clases
 * hijas para conectar con la BD.
 * 
 * @author elisa
 *
 */
public abstract class AbstractDAO {
	// Atributos a los que solo accede la clase abstracta
	private final String DB_URL = "jdbc:mysql://localhost/netflix";
	private final String USER = "Rawdevil";
	private final String PASS = "Put0user22";
	// Atributos a los que accederán las clases hijas y que están inicializados en
	// el constructor
	protected Connection conn;
	protected Statement stmt;

	/**
	 * Constructor de la clase abstracta.
	 */
	public AbstractDAO() {
		try {
			this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
			this.stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
