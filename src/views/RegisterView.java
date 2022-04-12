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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class RegisterView extends JFrame {
	
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
	private JButton btnCreateAccount;
	private JLabel lblBackground;
	private UserDAO userDAO;
	private JPanel panel;
	private JLabel lbl_fondoSeries;
	
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
		frame.setBounds(100, 100, 705, 460);
		getContentPane().setLayout(null);
		
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(RegisterView.class.getResource("/assets/netflix-series.jpg")));
		lblBackground.setBounds(0, 0, 640, 353);
		frame.getContentPane().add(lblBackground);
		
		jpCentral = new JPanel();
		jpCentral.setBorder(null);
		jpCentral.setBackground(new Color(0,0,0,180));
		jpCentral.setBounds(166, 10, 302, 333);
		getContentPane().add(jpCentral);
		jpCentral.setLayout(null);
		
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
		
		panel = new JPanel();
		panel.setBounds(0, 0, 633, 353);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lbl_fondoSeries = new JLabel("Fondo de series");
		lbl_fondoSeries.setIcon(new ImageIcon(RegisterView.class.getResource("/assets/netflix-series.jpg")));
		lbl_fondoSeries.setBounds(0, 0, 633, 353);
		panel.add(lbl_fondoSeries);
		
	}
	
	public void configureUIListeners() {
		}
}
	
