package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;

import dao.UserDAO;
import main.MainApp;
import models.User;
import utils.ActivationCodeHelper;
import utils.EmailHelper;
import utils.PasswordHasher;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ForgotPassword extends JFrame {

	private JFrame frame;
	private JTextField tfEmail;
	private JPanel jpCentral;
	private JLabel lblForgotPassword;
	private JLabel lblEmail;
	private JButton btnReceiveCode;
	private JButton btnBackToLogin;
	private JLabel lblBackground;
	private UserDAO userDAO;
	private User user;
	private JPanel panel;
	private JLabel lbl_fondoSeries;
	private JLabel lblGoToChangePwd;
	private JButton btnChangePassword;
	private JLabel lblDidntGetCode;

	public static void main(String[] args) {
		new ForgotPassword();
	}

	/**
	 * Creaci�n de la aplicaci�n. Se inicializa, se hace visible el marco y se
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
	 * M�todo que configura los componentes de la interfaz gr�fica.
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

		btnReceiveCode = new JButton("Receive code");
		btnReceiveCode.setBounds(39, 118, 222, 32);
		btnReceiveCode.setBorder(null);
		btnReceiveCode.setBackground(Color.RED);
		btnReceiveCode.setFont(new Font("Dubai", Font.BOLD, 15));
		btnReceiveCode.setForeground(Color.WHITE);
		jpCentral.add(btnReceiveCode);

		btnChangePassword = new JButton("Change password");
		btnChangePassword.setForeground(Color.WHITE);
		btnChangePassword.setFont(new Font("Dialog", Font.BOLD, 15));
		btnChangePassword.setBorder(null);
		btnChangePassword.setBackground(Color.RED);
		btnChangePassword.setBounds(39, 243, 222, 32);
		jpCentral.add(btnChangePassword);

		lblDidntGetCode = new JLabel("I didn't get my code");
		lblDidntGetCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblDidntGetCode.setVerticalAlignment(SwingConstants.TOP);
		lblDidntGetCode.setForeground(Color.LIGHT_GRAY);
		lblDidntGetCode.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblDidntGetCode.setBounds(146, 156, 115, 13);
		lblDidntGetCode.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jpCentral.add(lblDidntGetCode);

		lblGoToChangePwd = new JLabel("If you got your code, click here:");
		lblGoToChangePwd.setForeground(Color.WHITE);
		lblGoToChangePwd.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblGoToChangePwd.setBounds(39, 228, 222, 13);
		jpCentral.add(lblGoToChangePwd);

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
		
		btnReceiveCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				user = new User(0, tfEmail.getText(), null);
				
				if (userDAO.isUser(user)) {
					if (userDAO.isActivated(user)) {
					EmailHelper.SendForgotPassword(tfEmail.getText());
					JOptionPane.showMessageDialog(btnReceiveCode, "You got your activation code in your mailbox");
					} else {
					JOptionPane.showMessageDialog(btnReceiveCode, "This user is not yet activated. Before reseting your password, please activate your account with the 6-digit code we sent you to your personal E-Mail at the moment of registering.");
					new UserActivationView();
					frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(btnReceiveCode, "That user is not registered in our database.");
				}
				
				
			}
		});
		
		lblDidntGetCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(lblDidntGetCode, "Close the application and try again later!");
			}
		});
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePasswordView();
				frame.dispose();
			}
		});

	}
}
