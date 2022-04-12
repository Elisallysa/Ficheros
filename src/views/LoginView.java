package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import dao.UserDAO;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.DebugGraphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame {
	
	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField pwfPassword;
	private JLabel lblLogin;
	private JLabel lblNewLabel;
	private JLabel lblJoin;
	private JLabel lblNetflixImg;
	private UserDAO userDAO;
	
	public static void main(String[] args) {
		new LoginView();
	}
	
	public LoginView() {
		initialize();
		frame.setVisible(true);
		this.userDAO = new UserDAO();
	}
	
	public void initialize() {
		frame = new JFrame();
		configureUIComponents();
		configureUIListeners();
	}
	
	public void configureUIComponents() {
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 705, 460);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(244, 271, 207, 38);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		pwfPassword = new JPasswordField();
		pwfPassword.setBounds(244, 319, 207, 38);
		frame.getContentPane().add(pwfPassword);
		
		lblLogin = new JLabel("Log in");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Dubai", Font.BOLD, 25));
		lblLogin.setBounds(245, 223, 206, 38);
		frame.getContentPane().add(lblLogin);
		
		lblNewLabel = new JLabel("New to Netflix?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblNewLabel.setBounds(244, 369, 106, 27);
		frame.getContentPane().add(lblNewLabel);
		
		lblJoin = new JLabel("Sign up now.");
		lblJoin.setForeground(Color.WHITE);
		lblJoin.setFont(new Font("Dubai", Font.PLAIN, 14));
		lblJoin.setBounds(356, 369, 106, 27);
		frame.getContentPane().add(lblJoin);
		
		lblNetflixImg = new JLabel("");
		lblNetflixImg.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		lblNetflixImg.setDoubleBuffered(true);
		lblNetflixImg.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNetflixImg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNetflixImg.setIcon(new ImageIcon(LoginView.class.getResource("/assets/Netflix-Logo.jpg")));
		lblNetflixImg.setBounds(10, 10, 666, 218);
		frame.getContentPane().add(lblNetflixImg);
	}
	
	public void configureUIListeners() {
		lblJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
}
