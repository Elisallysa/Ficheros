package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import dao.ShowDAO;
import models.Show;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SearchView {

	private JFrame frame;
	private JList<Show> list;
	private JLabel lblSearchEngine;
	private JTextField tfSearchBox;
	private JComboBox<String> cbFilter;
	private JButton btnSearch;
	private ShowDAO showDAO;

	public static void main(String[] args) {
		new SearchView();
	}

	/**
	 * Create the application.
	 */
	public SearchView() {
		initialize();
		frame.setVisible(true);
		this.showDAO = new ShowDAO();
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
		
		list = null;
		list.setBackground(Color.WHITE);
		list.setBounds(29, 148, 556, 264);
		//list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		frame.getContentPane().add(list);
		//frame.getContentPane().add(new JScrollPane(list));
		
		lblSearchEngine = new JLabel("Netflix Search Engine");
		lblSearchEngine.setFont(new Font("Dubai", Font.BOLD, 53));
		lblSearchEngine.setForeground(Color.WHITE);
		lblSearchEngine.setBounds(29, 17, 550, 73);
		frame.getContentPane().add(lblSearchEngine);
		
		tfSearchBox = new JTextField();
		tfSearchBox.setFont(new Font("Dubai", Font.PLAIN, 14));
		tfSearchBox.setBounds(29, 95, 179, 29);
		frame.getContentPane().add(tfSearchBox);
		tfSearchBox.setColumns(10);
		
		cbFilter = new JComboBox<String>();
		cbFilter.addItem("Title");
		cbFilter.addItem("Country");
		cbFilter.addItem("Director");
		cbFilter.addItem("Release year");
		cbFilter.setFont(new Font("Dubai", Font.PLAIN, 14));
		cbFilter.setBounds(211, 95, 186, 29);
		frame.getContentPane().add(cbFilter);
		
		btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.RED);
		btnSearch.setBorder(null);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Dubai", Font.BOLD, 14));
		btnSearch.setBounds(400, 95, 179, 29);
		frame.getContentPane().add(btnSearch);
	}
	
	public void configureUIListeners() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JList<Show> showsList = showDAO.search(cbFilter.getSelectedIndex(), tfSearchBox.getText());
				list = showsList;
				
			
			}
		});
	}
}
