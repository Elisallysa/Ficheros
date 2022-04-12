package models;

public class User {
	// ATRIBUTOS
	// Número entero identificativo único del usuario
	private int iduser;
	// Cadena de caracteres con el nombre de usuario
	private String username;
	// Cadena de caracteres con la contraseña del usuario
	private String password;

	// CONSTRUCTOR
	/**
	 * Constructor de User
	 * 
	 * @param iduser   - número identificativo único
	 * @param username - nombre de usuario
	 * @param password - contraseña
	 */
	public User(int iduser, String username, String password) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.password = password;
	}

	// GETTERS & SETTERS
	public int getId() {
		return iduser;
	}

	public void setId(int id) {
		this.iduser = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// TO STRING
	@Override
	public String toString() {
		return "Usuario [iduser=" + iduser + ", username=" + username + ", password=" + password + "]";
	}
}
