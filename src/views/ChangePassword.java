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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ChangePassword extends JFrame {
	
	private JFrame frame;
	private JTextField tfEmail;
	private JPanel jpCentral;
	private JLabel lblUserActivation;
	private JLabel lblEmail;
	private JButton btnActivate;
	private JLabel lblBackground;
	private UserDAO userDAO;
	private JPanel panel;
	private JLabel lbl_fondoSeries;
	private JLabel lblActivationCode;
	private JTextField tfActivationCode;
	private JLabel lblDontHaveCode;
	private JLabel lblSendAgain;
	
	public static void main(String[] args) {
		new ChangePassword();
	}
	
	/**
	 * Creación de la aplicación. Se inicializa, se hace visible el marco y se
	 * reserva memoria para el usuario DAO.
	 */
	public ChangePassword() {
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
		lblBackground.setIcon(new ImageIcon(ChangePassword.class.getResource("/assets/netflix-series.jpg")));
		lblBackground.setBounds(0, 0, 640, 353);
		frame.getContentPane().add(lblBackground);
		
		jpCentral = new JPanel();
		jpCentral.setBorder(null);
		jpCentral.setBackground(new Color(0,0,0,180));
		jpCentral.setBounds(166, 10, 302, 333);
		getContentPane().add(jpCentral);
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
		btnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		jpCentral.add(lblSendAgain);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 633, 353);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lbl_fondoSeries = new JLabel("Fondo de series");
		lbl_fondoSeries.setIcon(new ImageIcon(ChangePassword.class.getResource("/assets/netflix-series.jpg")));
		lbl_fondoSeries.setBounds(0, 0, 633, 353);
		panel.add(lbl_fondoSeries);
		
	}
	
	public void configureUIListeners() {
		}
}
	
