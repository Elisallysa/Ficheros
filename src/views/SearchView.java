package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import models.Show;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class SearchView {

	private JFrame frame;
	private JList<Show> list;
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		new SearchView();
	}

	/**
	 * Create the application.
	 */
	public SearchView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		configureUIComponents();
		configureUIListeners();
	}
	
	public void configureUIComponents() {
		frame.setBounds(100, 100, 632, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		
		list = new JList<Show>();
		list.setBounds(29, 84, 550, 328);
		frame.getContentPane().add(list);
		frame.add(new JScrollPane(list));
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		lblNewLabel = new JLabel("Netflix Search Engine");
		lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 53));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(29, 17, 550, 73);
		frame.getContentPane().add(lblNewLabel);
	}
	
	public void configureUIListeners() {
		
	}
	

}
