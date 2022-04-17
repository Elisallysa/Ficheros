package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;

import dao.UserDAO;
import models.User;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class RegisterView {
	
	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField pwfConfirmPassword;
	private JPasswordField pwfPassword;
	private JTextField tfEmail;
	private JPanel jpCentral;
	private JLabel lblSignUp;
	private JLabel lblUsername;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JLabel lbl_fondoSeries;
	private JButton btnCreateAccount;
	private JButton btnBackToLogin;
	private UserDAO userDAO;
	private User user;
	
	public static void main(String[] args) {
		new RegisterView();
	}
	
	/**
	 * Creación de la aplicación. Se inicializa, se hace visible el marco y se
	 * reserva memoria para el usuario DAO.
	 */
	public RegisterView() {
		initialize();
		frame.setVisible(true);
		this.userDAO = new UserDAO();
	}

	/**
	 * Se inicializan los contenidos del marco.
	 */
	private void initialize() {
		frame = new JFrame();
		this.configureUIComponents();
		this.configureUIListeners();
	}

	/**
	 * Método que configura los componentes de la interfaz gráfica.
	 */
	public void configureUIComponents() {
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 644, 393);
		frame.getContentPane().setLayout(null);
		
		btnBackToLogin = new JButton("Back to Login");
		btnBackToLogin.setMargin(new Insets(0, 0, 0, 0));
		btnBackToLogin.setBorder(null);
		btnBackToLogin.setBackground(Color.RED);
		btnBackToLogin.setForeground(Color.WHITE);
		btnBackToLogin.setFont(new Font("Dubai", Font.BOLD, 10));
		btnBackToLogin.setBounds(10, 10, 68, 21);
		frame.getContentPane().add(btnBackToLogin);
		
		jpCentral = new JPanel();
		jpCentral.setBorder(null);
		jpCentral.setBackground(new Color(0,0,0));
		jpCentral.setBounds(166, 10, 302, 333);
		frame.getContentPane().add(jpCentral);
		jpCentral.setLayout(null);
		
		lbl_fondoSeries = new JLabel("Fondo de series");
		lbl_fondoSeries.setIcon(new ImageIcon(RegisterView.class.getResource("/assets/netflix-series.jpg")));
		lbl_fondoSeries.setBounds(0, 0, 633, 353);
		frame.getContentPane().add(lbl_fondoSeries);
		
		lblSignUp = new JLabel("Sign up");
		lblSignUp.setBounds(39, 21, 165, 44);
		lblSignUp.setFont(new Font("Dubai", Font.BOLD, 36));
		lblSignUp.setForeground(Color.WHITE);
		jpCentral.add(lblSignUp);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(39, 82, 222, 32);
		tfUsername.setFont(new Font("Dubai", Font.PLAIN, 18));
		jpCentral.add(tfUsername);
		tfUsername.setColumns(10);
		
		pwfConfirmPassword = new JPasswordField();
		pwfConfirmPassword.setBounds(39, 229, 222, 32);
		jpCentral.add(pwfConfirmPassword);
		
		pwfPassword = new JPasswordField();
		pwfPassword.setBounds(39, 180, 222, 32);
		jpCentral.add(pwfPassword);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(39, 70, 78, 13);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblUsername);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(39, 120, 78, 13);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(39, 132, 222, 32);
		tfEmail.setFont(new Font("Dubai", Font.PLAIN, 18));
		tfEmail.setColumns(10);
		jpCentral.add(tfEmail);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 168, 78, 13);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblPassword);
		
		lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setBounds(39, 217, 120, 13);
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblConfirmPassword);
		
		btnCreateAccount = new JButton("Create account");
		btnCreateAccount.setBounds(39, 278, 222, 32);
		btnCreateAccount.setBorder(null);
		btnCreateAccount.setBackground(Color.RED);
		btnCreateAccount.setFont(new Font("Dubai", Font.BOLD, 15));
		btnCreateAccount.setForeground(Color.WHITE);
		jpCentral.add(btnCreateAccount);
		
		
		
		
		
	}
	
	public void configureUIListeners() {
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText();
				String mail = tfEmail.getText();
				String password = new String(pwfPassword.getPassword());
				String confirmedPassword = new String(pwfConfirmPassword.getPassword());

				if (!username.isEmpty() && !mail.isEmpty() && !password.isEmpty() && !confirmedPassword.isEmpty()) { // Todos los campos
					user = new User(0, username, mail, password);																							// deben tener texto
					if (!userDAO.isUser(user)) {
						if (password.equals(confirmedPassword)) { // Si la contraseña es igual a la contraseña confirmada se
																// registra el usuario
						
						userDAO.register(user);
						JOptionPane.showMessageDialog(btnCreateAccount, "Thanks for joining!");
						new LoginView();
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(btnCreateAccount, "Passwords do not match.");
					}
					} else {
						JOptionPane.showMessageDialog(btnCreateAccount, "That E-mail already exists in our DB.");
					}
					
				} else {
					JOptionPane.showMessageDialog(btnCreateAccount, "Completion of all information is required to register.");
				}
			}
		});
		
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView();
			}
		});
	}
}
	
