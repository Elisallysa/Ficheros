package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;

import dao.UserDAO;
import models.User;
import utils.PasswordHasher;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.DebugGraphics;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase de la vista de Login de usuario.
 * 
 * @author elisa
 *
 */
public class LoginView {

	// COMPONENTES DE LA VISTA
	private JFrame frame;
	private JTextField tfMail;
	private JPasswordField pwfPassword;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblLogin;
	private JLabel lblNewToNetflix;
	private JLabel lblJoin;
	private JLabel lblNetflixImg;
	private JLabel lblForgotPwd;
	private JButton btnLogin;
	private UserDAO userDAO;
	private User usuario;

	/**
	 * Creación de la vista. Se inicializa, se hace visible el marco y se reserva
	 * memoria para el usuario DAO.
	 */
	public LoginView() {
		initialize();
		frame.setVisible(true);
		this.userDAO = new UserDAO();
	}

	/**
	 * Método que inicializa los contenidos del marco. Y se llaman los métodos que
	 * configuran componentes y listeners.
	 */
	public void initialize() {
		frame = new JFrame();
		configureUIComponents();
		configureUIListeners();
	}

	/**
	 * Método que configura los componentes de la vista.
	 */
	public void configureUIComponents() {
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 704, 500);

		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(244, 236, 78, 13);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 10));
		frame.getContentPane().add(lblEmail);

		lblPassword = new JLabel("Password");
		lblPassword.setBounds(244, 294, 78, 13);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 10));
		frame.getContentPane().add(lblPassword);

		tfMail = new JTextField();
		tfMail.setBounds(244, 252, 207, 38);
		frame.getContentPane().add(tfMail);
		tfMail.setColumns(10);

		pwfPassword = new JPasswordField();
		pwfPassword.setBounds(244, 309, 207, 38);
		frame.getContentPane().add(pwfPassword);

		lblLogin = new JLabel("Log in");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Dubai", Font.BOLD, 25));
		lblLogin.setBounds(244, 198, 207, 38);
		frame.getContentPane().add(lblLogin);

		lblNewToNetflix = new JLabel("New to Netflix?");
		lblNewToNetflix.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewToNetflix.setForeground(Color.WHITE);
		lblNewToNetflix.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblNewToNetflix.setBounds(244, 403, 106, 27);
		frame.getContentPane().add(lblNewToNetflix);

		lblJoin = new JLabel("Sign up now.");
		lblJoin.setForeground(new Color(30, 144, 255));
		lblJoin.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblJoin.setBounds(356, 403, 95, 27);
		lblJoin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(lblJoin);

		lblNetflixImg = new JLabel("");
		lblNetflixImg.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		lblNetflixImg.setDoubleBuffered(true);
		lblNetflixImg.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNetflixImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNetflixImg.setIcon(new ImageIcon(LoginView.class.getResource("/assets/Netflix-Logo.jpg")));
		lblNetflixImg.setBounds(10, 10, 666, 218);
		frame.getContentPane().add(lblNetflixImg);

		lblForgotPwd = new JLabel("Forgot your password?");
		lblForgotPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgotPwd.setForeground(Color.LIGHT_GRAY);
		lblForgotPwd.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblForgotPwd.setBounds(244, 426, 207, 27);
		lblForgotPwd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(lblForgotPwd);

		btnLogin = new JButton("Log in");
		btnLogin.setBounds(242, 368, 209, 32);
		btnLogin.setBorder(null);
		btnLogin.setBackground(Color.RED);
		btnLogin.setFont(new Font("Dubai", Font.BOLD, 15));
		btnLogin.setForeground(Color.WHITE);
		frame.getContentPane().add(btnLogin);
	}

	/**
	 * Método que configura los listeners de la vista.
	 */
	public void configureUIListeners() {
		// Botón de inicio de sesión
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		// Con el listener de este Label se regresa a la vista anterior
		lblJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new RegisterView();
			}
		});

		// Con el listener de este Label se abre una vista de solicitud de código para
		// cambiar contraseña
		lblForgotPwd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				new ForgotPassword();
			}
		});
	}

	/**
	 * Método que obtiene el texto del campo de texto del mail de usuario y la
	 * contraseña y comprueba que se encuentra en la BD para permitir el acceso a la
	 * aplicación.
	 */
	public void login() {
		String mail = tfMail.getText();
		String password = new String(pwfPassword.getPassword());
		usuario = new User(0, mail, PasswordHasher.hashIt(password, "123456789"));
		boolean loggedIn = userDAO.login(usuario); // Llamamos al método login de la clase userDAO para comprobar que el
													// mail y contraseña corresponden a un usuario de la BD

		/*
		 * Si en UsuarioDAO obtenemos un registro con los datos del usuario, loggedIn es
		 * true y por tanto, desaparece la ventana de login y se crea una nueva del
		 * buscador de shows de Netflix. Si no, lanza un mensaje que informa que el
		 * nombre de usuario o contrase�a son inv�lidos.
		 */
		if (loggedIn && userDAO.isActivated(usuario)) { // Si el usuario se encuentra en la BD y está activado se
														// permite el inicio de sesión
			JOptionPane.showMessageDialog(btnLogin, "Login successful!");
			new SearchView(userDAO.getUsername(usuario));
			frame.dispose();
		} else if (loggedIn && !userDAO.isActivated(usuario)) {
			JOptionPane.showMessageDialog(btnLogin, "User activation pending. Please activate your account.");
			new UserActivationView(); // Si el usuario no está activo se le redirige a la vista de activación de
										// usuario
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(btnLogin, "Invalid E-mail or password");
		}
	}
}
