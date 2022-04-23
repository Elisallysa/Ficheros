package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;

import dao.UserDAO;
import models.User;
import utils.PasswordHasher;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Clase de la vista en la que el usuario puede cambiar su contraseña.
 * 
 * @author elisa
 *
 */
public class ChangePasswordView {

	// COMPONENTES DE LA VISTA
	private JFrame frame;
	private JPasswordField pwfConfirmPassword;
	private JPasswordField pwfPassword;
	private JTextField tfEmail;
	private JTextField tfCode;
	private JPanel jpCentral;
	private JPanel panel;
	private JLabel lblSignUp;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel lblConfirmPassword;
	private JLabel lbl_fondoSeries;
	private JLabel lblActivationCode;
	private JLabel lblBackground;
	private JButton btnChangePwd;
	private JButton btnBackToForgotMyPassword;
	private UserDAO userDAO;
	private User user;

	/**
	 * Creación de la vista. Se inicializa, se hace visible el marco y se reserva
	 * memoria para el usuario DAO.
	 */
	public ChangePasswordView() {
		initialize();
		frame.setVisible(true);
		this.userDAO = new UserDAO();
	}

	/**
	 * Método que inicializa los contenidos del marco. Y se llaman los métodos que
	 * configuran componentes y listeners.
	 */
	private void initialize() {
		frame = new JFrame();
		this.configureUIComponents();
		this.configureUIListeners();
	}

	/**
	 * Método que configura los componentes de la vista.
	 */
	public void configureUIComponents() {
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 649, 386);
		frame.getContentPane().setLayout(null);

		jpCentral = new JPanel();
		jpCentral.setBorder(null);
		jpCentral.setBackground(new Color(0, 0, 0, 180));
		jpCentral.setBounds(166, 10, 302, 333);
		frame.getContentPane().add(jpCentral);
		jpCentral.setLayout(null);

		lblSignUp = new JLabel("Change password");
		lblSignUp.setBounds(39, 21, 222, 44);
		lblSignUp.setFont(new Font("Dialog", Font.BOLD, 25));
		lblSignUp.setForeground(Color.WHITE);
		jpCentral.add(lblSignUp);

		pwfConfirmPassword = new JPasswordField();
		pwfConfirmPassword.setBounds(39, 182, 222, 32);
		jpCentral.add(pwfConfirmPassword);

		pwfPassword = new JPasswordField();
		pwfPassword.setBounds(39, 133, 222, 32);
		jpCentral.add(pwfPassword);

		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(39, 73, 78, 13);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setBounds(39, 85, 222, 32);
		tfEmail.setFont(new Font("Dubai", Font.PLAIN, 18));
		tfEmail.setColumns(10);
		jpCentral.add(tfEmail);

		lblPassword = new JLabel("New password");
		lblPassword.setBounds(39, 121, 78, 13);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblPassword);

		lblConfirmPassword = new JLabel("Repeat password");
		lblConfirmPassword.setBounds(39, 170, 120, 13);
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblConfirmPassword);

		btnChangePwd = new JButton("Change password");
		btnChangePwd.setBounds(39, 279, 222, 32);
		btnChangePwd.setBorder(null);
		btnChangePwd.setBackground(Color.RED);
		btnChangePwd.setFont(new Font("Dubai", Font.BOLD, 15));
		btnChangePwd.setForeground(Color.WHITE);
		jpCentral.add(btnChangePwd);

		lblActivationCode = new JLabel("Activation code");
		lblActivationCode.setForeground(Color.WHITE);
		lblActivationCode.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblActivationCode.setBounds(39, 225, 222, 13);
		jpCentral.add(lblActivationCode);

		tfCode = new JTextField();
		tfCode.setFont(new Font("Dubai", Font.PLAIN, 18));
		tfCode.setColumns(10);
		tfCode.setBounds(39, 237, 222, 32);
		jpCentral.add(tfCode);

		panel = new JPanel();
		panel.setBounds(0, 0, 633, 353);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnBackToForgotMyPassword = new JButton("Back to Forgot Password");
		btnBackToForgotMyPassword.setMargin(new Insets(0, 0, 0, 0));
		btnBackToForgotMyPassword.setBorder(null);
		btnBackToForgotMyPassword.setBackground(Color.RED);
		btnBackToForgotMyPassword.setForeground(Color.WHITE);
		btnBackToForgotMyPassword.setFont(new Font("Dubai", Font.BOLD, 10));
		btnBackToForgotMyPassword.setBounds(10, 10, 121, 21);
		panel.add(btnBackToForgotMyPassword);

		lbl_fondoSeries = new JLabel("Fondo de series");
		lbl_fondoSeries.setIcon(new ImageIcon(ChangePasswordView.class.getResource("/assets/netflix-series.jpg")));
		lbl_fondoSeries.setBounds(0, 0, 633, 353);
		panel.add(lbl_fondoSeries);

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(ChangePasswordView.class.getResource("/assets/netflix-series.jpg")));
		lblBackground.setBounds(0, 0, 640, 353);
		frame.getContentPane().add(lblBackground);

	}

	/**
	 * Método que configura los listeners de la vista.
	 */
	public void configureUIListeners() {

		// Botón para volver a la vista anterior.
		btnBackToForgotMyPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ForgotPassword();
				frame.dispose();
			}
		});

		// Botón para cambiar la contraseña con los datos introducidos.
		btnChangePwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail = tfEmail.getText();
				String password = new String(pwfPassword.getPassword());
				String confirmedPassword = new String(pwfConfirmPassword.getPassword());
				String code = tfCode.getText();

				// Todos los campos deben tener texto:
				if (!mail.isEmpty() && !password.isEmpty() && !confirmedPassword.isEmpty() && !code.isEmpty()) {

					user = new User(0, null, mail, PasswordHasher.hashIt(new String(password), "123456789"));

					if (userDAO.isUser(user)) { // Si existe el usuario
						if (password.equals(confirmedPassword)) { // y las contraseñas coinciden
							userDAO.changePassword(user, code); // se cambia la contraseña en la BD
							JOptionPane.showMessageDialog(btnChangePwd, "Password changed successfully.");
							new UserActivationView();
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(btnChangePwd, "Passwords do not match.");
						}
					} else {
						JOptionPane.showMessageDialog(btnChangePwd,
								"There is no user linked to that E-Mail address in our DB.");
					}

				} else {
					JOptionPane.showMessageDialog(btnChangePwd, "Please fill in all text fields.");
				}
			}
		});
	}
}
