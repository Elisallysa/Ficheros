package models;

public class User {
	// ATRIBUTOS
	// N�mero entero identificativo �nico del usuario
	private int iduser;
	// Cadena de caracteres con el nombre de usuario
	private String username;
	private String mail;
	// Cadena de caracteres con la contrase�a del usuario
	private String password;
	private String activationCode;
	private boolean activated;

	// CONSTRUCTOR
	/**
	 * Constructor de User
	 * 
	 * @param iduser   - n�mero identificativo �nico
	 * @param username - nombre de usuario
	 * @param password - contrase�a
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
	
	public User(int iduser, String mail, String password) {
		super();
		this.iduser = iduser;
		this.mail = mail;
		this.password = password;
	}
	
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
