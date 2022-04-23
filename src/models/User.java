package models;

/**
 * La clase User representa un usuario que contiene información relevante del
 * mismo.
 * 
 * @author elisa
 *
 */
public class User {
	// ATRIBUTOS
	private int iduser; // Número entero identificativo único del usuario
	private String username; // Cadena de caracteres con el nombre de usuario
	private String mail; // Dirección de mail del usuario
	private String password;// Cadena de caracteres con la contraseña del usuario
	private String activationCode; // Cadena de caracteres con un código de activación
	private boolean activated; // Booleano que indica si el usuario ha activado su cuenta o no

	// CONSTRUCTORES

	/**
	 * Constructor de User con todos los atributos.
	 * 
	 * @param iduser         - Número entero identificativo del usuario
	 * @param username       - Cadena de caracteres con el nombre de usuario
	 * @param mail           - Cadena de caracteres con la dirección de E-Mail del
	 *                       usuario
	 * @param password       - Cadena de caracteres con la contraseña del usuario
	 * @param activationCode - Cadena de caracteres con el código de activación del
	 *                       usuario
	 * @param activated      - Booleano que indica si la cuenta se encuentra activa
	 *                       o no
	 */
	public User(int iduser, String username, String mail, String password, String activationCode, boolean activated) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.mail = mail;
		this.password = password;
		this.activationCode = activationCode;
		this.setActivated(false);
	}

	/**
	 * Constructor de User con id, mail y contraseña.
	 * 
	 * @param iduser   - Número entero identificativo del usuario
	 * @param mail     - Cadena de caracteres con la dirección de E-Mail del usuario
	 * @param password - Cadena de caracteres con la contraseña del usuario
	 */
	public User(int iduser, String mail, String password) {
		super();
		this.iduser = iduser;
		this.mail = mail;
		this.password = password;
	}

	/**
	 * Constructor de User con id, nombre de usuario, mail y contraseña.
	 * 
	 * @param iduser   - Número entero identificativo del usuario
	 * @param username - Cadena de caracteres con el nombre de usuario
	 * @param mail     - Cadena de caracteres con la dirección de E-Mail del usuario
	 * @param password - Cadena de caracteres con la contraseña del usuario
	 */
	public User(int iduser, String username, String mail, String password) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.mail = mail;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	// TO STRING
	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", username=" + username + ", mail=" + mail + ", password=" + password
				+ ", activationCode=" + activationCode + ", activated=" + activated + "]";
	}
}
