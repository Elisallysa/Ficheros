package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import dao.UserDAO;
import models.User;
import utils.ActivationCodeHelper;
import utils.EmailHelper;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase de la vista de activación de usuario.
 * 
 * @author elisa
 *
 */
public class UserActivationView {

	// COMPONENTES DE LA VISTA
	private JFrame frame;
	private JTextField tfEmail;
	private JPanel jpCentral;
	private JPanel panel;
	private JLabel lblUserActivation;
	private JLabel lblEmail;
	private JLabel lbl_fondoSeries;
	private JLabel lblActivationCode;
	private JLabel lblDontHaveCode;
	private JLabel lblSendAgain;
	private JLabel lblBackground;
	private JButton btnActivate;
	private JButton btnBackToLogin;
	private UserDAO userDAO;
	private JTextField tfActivationCode;
	private User user;

	/**
	 * Creación de la aplicación. Se inicializa, se hace visible el marco y se
	 * reserva memoria para el usuario DAO.
	 */
	public UserActivationView() {
		initialize();
		frame.setVisible(true);
		this.userDAO = new UserDAO();
	}

	/**
	 * Se inicializan los contenidos del marco y se llama a los métodos que
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
		frame.setBounds(100, 100, 649, 384);
		frame.getContentPane().setLayout(null);

		jpCentral = new JPanel();
		jpCentral.setBorder(null);
		jpCentral.setBackground(new Color(0, 0, 0, 180));
		jpCentral.setBounds(166, 10, 302, 333);
		frame.getContentPane().add(jpCentral);
		jpCentral.setLayout(null);

		lblUserActivation = new JLabel("User activation");
		lblUserActivation.setBounds(39, 30, 222, 44);
		lblUserActivation.setFont(new Font("Dialog", Font.BOLD, 25));
		lblUserActivation.setForeground(Color.WHITE);
		jpCentral.add(lblUserActivation);

		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(39, 101, 78, 13);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setBounds(39, 113, 222, 32);
		tfEmail.setFont(new Font("Dubai", Font.PLAIN, 18));
		tfEmail.setColumns(10);
		jpCentral.add(tfEmail);

		btnActivate = new JButton("Activate");
		btnActivate.setBounds(39, 210, 222, 32);
		btnActivate.setBorder(null);
		btnActivate.setBackground(Color.RED);
		btnActivate.setFont(new Font("Dubai", Font.BOLD, 15));
		btnActivate.setForeground(Color.WHITE);
		jpCentral.add(btnActivate);

		lblActivationCode = new JLabel("Activation code");
		lblActivationCode.setForeground(Color.WHITE);
		lblActivationCode.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblActivationCode.setBounds(39, 147, 78, 13);
		jpCentral.add(lblActivationCode);

		tfActivationCode = new JTextField();
		tfActivationCode.setFont(new Font("Dialog", Font.PLAIN, 18));
		tfActivationCode.setColumns(10);
		tfActivationCode.setBounds(39, 159, 222, 32);
		jpCentral.add(tfActivationCode);

		lblDontHaveCode = new JLabel("I don't have any code.");
		lblDontHaveCode.setForeground(Color.WHITE);
		lblDontHaveCode.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblDontHaveCode.setBounds(85, 249, 108, 13);
		jpCentral.add(lblDontHaveCode);

		lblSendAgain = new JLabel("Send again.");

		lblSendAgain.setHorizontalAlignment(SwingConstants.CENTER);
		lblSendAgain.setForeground(Color.LIGHT_GRAY);
		lblSendAgain.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblSendAgain.setBounds(189, 249, 72, 13);
		lblSendAgain.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jpCentral.add(lblSendAgain);

		panel = new JPanel();
		panel.setBounds(0, 0, 633, 353);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnBackToLogin = new JButton("Back to Login");
		btnBackToLogin.setMargin(new Insets(0, 0, 0, 0));
		btnBackToLogin.setBorder(null);
		btnBackToLogin.setBackground(Color.RED);
		btnBackToLogin.setForeground(Color.WHITE);
		btnBackToLogin.setFont(new Font("Dubai", Font.BOLD, 10));
		btnBackToLogin.setBounds(10, 10, 68, 21);
		panel.add(btnBackToLogin);

		lbl_fondoSeries = new JLabel("Fondo de series");
		lbl_fondoSeries.setIcon(new ImageIcon(UserActivationView.class.getResource("/assets/netflix-series.jpg")));
		lbl_fondoSeries.setBounds(0, 0, 633, 353);
		panel.add(lbl_fondoSeries);

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(UserActivationView.class.getResource("/assets/netflix-series.jpg")));
		lblBackground.setBounds(0, 0, 640, 353);
		frame.getContentPane().add(lblBackground);

	}

	/**
	 * Método que configura los listeners de la vista.
	 */
	public void configureUIListeners() {
		// Botón que activa la cuenta de usuario
		btnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Ningún campo debe estar vacío:
				if (!tfEmail.getText().isEmpty() || !tfActivationCode.getText().isEmpty()) {
					User user = new User(0, tfEmail.getText(), null);
					if (userDAO.checkActivationCode(user, tfActivationCode.getText())) { // Se activa la cuenta si el
																							// código de activación
																							// coincide con el
																							// almacenado en la BD
						userDAO.activateUser(user, tfActivationCode.getText());
						JOptionPane.showMessageDialog(btnActivate, "User activated successfully");
					} else {
						JOptionPane.showMessageDialog(btnActivate,
								"Invalid mail or activation code. Please try again.");
					}
				} else {
					JOptionPane.showMessageDialog(btnActivate, "Please introduce info in both text fields.");
				}

			}
		});

		// Botón con el que se confirma si el usuario necesita recibir otro código de
		// activación
		lblSendAgain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int option = JOptionPane.showConfirmDialog(frame, "Would you like to get another code?", "Resend code",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					if (!tfEmail.getText().isEmpty()) { // Se envía un nuevo código si se ha introducido un mail...
						user = new User(0, tfEmail.getText(), null);

						if (userDAO.isUser(user)) { // ...y el usuario está registrado
							String newCode = ActivationCodeHelper.generateActivationCode();
							ActivationCodeHelper.setActivationCode(user, newCode);
							EmailHelper.SendNewActivationCode(user.getMail(), newCode);
							JOptionPane.showMessageDialog(lblSendAgain, "New code sent!");
						} else {
							JOptionPane.showMessageDialog(lblSendAgain,
									"This user is not registered or the E-Mail address is not valid.");
						}

					} else {
						JOptionPane.showMessageDialog(lblSendAgain,
								"Please introduce an E-Mail address in the text field.");
					}

				}
			}
		});

		// Botón que regresa a la vista anterior de la GUI
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginView();
				frame.dispose();
			}
		});

	}
}
