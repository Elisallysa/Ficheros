package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import dao.UserDAO;
import main.MainApp;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ForgotPassword extends JFrame {

	private JFrame frame;
	private JTextField tfEmail;
	private JPanel jpCentral;
	private JLabel lblForgotPassword;
	private JLabel lblEmail;
	private JLabel lblInsertCode;
	private JButton btnReceiveCode;
	private JButton btnBackToLogin;
	private JLabel lblBackground;
	private UserDAO userDAO;
	private JPanel panel;
	private JLabel lbl_fondoSeries;
	private JTextField tfCode;
	private JLabel lblEmail_1;
	private JTextField textField;
	private JButton btnChangePassword;
	private JLabel lblDidntGetCode;

	public static void main(String[] args) {
		new ForgotPassword();
	}

	/**
	 * Creación de la aplicación. Se inicializa, se hace visible el marco y se
	 * reserva memoria para el usuario DAO.
	 */
	public ForgotPassword() {
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
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 653, 388);
		frame.getContentPane().setLayout(null);
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
		jpCentral.setBounds(166, 10, 302, 333);
		jpCentral.setBorder(null);
		jpCentral.setBackground(new Color(0, 0, 0, 180));
		frame.getContentPane().add(jpCentral);
		jpCentral.setLayout(null);

		lblForgotPassword = new JLabel("Forgot password");
		lblForgotPassword.setBounds(39, 10, 222, 44);
		lblForgotPassword.setFont(new Font("Dialog", Font.BOLD, 25));
		lblForgotPassword.setForeground(Color.WHITE);
		jpCentral.add(lblForgotPassword);

		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(39, 64, 78, 13);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setBounds(39, 76, 222, 32);
		tfEmail.setFont(new Font("Dubai", Font.PLAIN, 18));
		tfEmail.setColumns(10);
		jpCentral.add(tfEmail);

		lblInsertCode = new JLabel("Insert code here");
		lblInsertCode.setBounds(39, 222, 78, 13);
		lblInsertCode.setForeground(Color.WHITE);
		lblInsertCode.setFont(new Font("Dialog", Font.PLAIN, 10));
		jpCentral.add(lblInsertCode);

		btnReceiveCode = new JButton("Receive code");
		btnReceiveCode.setBounds(39, 118, 222, 32);
		btnReceiveCode.setBorder(null);
		btnReceiveCode.setBackground(Color.RED);
		btnReceiveCode.setFont(new Font("Dubai", Font.BOLD, 15));
		btnReceiveCode.setForeground(Color.WHITE);
		jpCentral.add(btnReceiveCode);

		tfCode = new JTextField();
		tfCode.setFont(new Font("Dialog", Font.PLAIN, 18));
		tfCode.setColumns(10);
		tfCode.setBounds(39, 236, 222, 32);
		jpCentral.add(tfCode);

		btnChangePassword = new JButton("Change password");
		btnChangePassword.setForeground(Color.WHITE);
		btnChangePassword.setFont(new Font("Dialog", Font.BOLD, 15));
		btnChangePassword.setBorder(null);
		btnChangePassword.setBackground(Color.RED);
		btnChangePassword.setBounds(39, 276, 222, 32);
		jpCentral.add(btnChangePassword);

		lblDidntGetCode = new JLabel("I didn't get my code");
		lblDidntGetCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDidntGetCode.setVerticalAlignment(SwingConstants.TOP);
		lblDidntGetCode.setForeground(Color.LIGHT_GRAY);
		lblDidntGetCode.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblDidntGetCode.setBounds(146, 156, 115, 13);
		jpCentral.add(lblDidntGetCode);

		lblEmail_1 = new JLabel("E-mail");
		lblEmail_1.setForeground(Color.WHITE);
		lblEmail_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblEmail_1.setBounds(39, 179, 78, 13);
		jpCentral.add(lblEmail_1);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(39, 191, 222, 32);
		jpCentral.add(textField);

		lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 640, 353);
		lblBackground.setIcon(new ImageIcon(ForgotPassword.class.getResource("/assets/netflix-series.jpg")));
		frame.getContentPane().add(lblBackground);

		panel = new JPanel();
		panel.setBounds(0, 0, 633, 353);
		getContentPane().add(panel);
		panel.setLayout(null);

		lbl_fondoSeries = new JLabel("Fondo de series");
		lbl_fondoSeries.setIcon(new ImageIcon(ForgotPassword.class.getResource("/assets/netflix-series.jpg")));
		lbl_fondoSeries.setBounds(0, 0, 633, 353);
		panel.add(lbl_fondoSeries);

	}

	public void configureUIListeners() {
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new LoginView();
			}
		});

	}
}
