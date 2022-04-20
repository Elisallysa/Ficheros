package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import dao.ShowDAO;
import models.Show;
import models.User;
import utils.FavShowsWriter;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.DebugGraphics;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class SearchView {

	private JFrame frame;
	private JList<String> jlShowList;
	private JLabel lblSearchEngine;
	private JTextField tfSearchBox;
	private JComboBox<String> cbFilter;
	private JButton btnSearch;
	private JButton btnAddToFavourites;
	private ShowDAO showDAO;
	private ArrayList<Show> showsList;
	private ListModel<String> defaultStringListModel;
	private User usuarioActivo;

	public static void main(String[] args) {
		new SearchView(new User(0, "eli", null, "123"));
	}

	/**
	 * Create the application.
	 */
	public SearchView(User usuario) {
		usuarioActivo = usuario;
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
		frame.setBounds(100, 100, 817, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		
		
		jlShowList = new JList();
		jlShowList.setVisibleRowCount(10);
		jlShowList.setSelectionBackground(new Color(0, 206, 209));
		jlShowList.setBounds(29, 134, 742, 275);
		jlShowList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jlShowList.setBackground(Color.WHITE);
		frame.getContentPane().add(jlShowList);
		
		lblSearchEngine = new JLabel("Netflix Search Engine");
		lblSearchEngine.setBounds(29, 17, 550, 73);
		lblSearchEngine.setFont(new Font("Dubai", Font.BOLD, 53));
		lblSearchEngine.setForeground(Color.WHITE);
		frame.getContentPane().add(lblSearchEngine);
		
		tfSearchBox = new JTextField();
		tfSearchBox.setBounds(29, 95, 179, 29);
		tfSearchBox.setFont(new Font("Dubai", Font.PLAIN, 14));
		frame.getContentPane().add(tfSearchBox);
		tfSearchBox.setColumns(10);
		
		cbFilter = new JComboBox<String>();
		cbFilter.setBounds(211, 95, 186, 29);
		cbFilter.setModel(new DefaultComboBoxModel<String>(new String[] {"Title", "Country", "Director", "Release year"}));
		cbFilter.setFont(new Font("Dubai", Font.PLAIN, 16));
		frame.getContentPane().add(cbFilter);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(400, 95, 179, 29);
		btnSearch.setBackground(Color.RED);
		btnSearch.setBorder(null);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Dubai", Font.BOLD, 14));
		frame.getContentPane().add(btnSearch);
		
		btnAddToFavourites = new JButton("Add to Favourites");
		btnAddToFavourites.setForeground(Color.WHITE);
		btnAddToFavourites.setFont(new Font("Dubai", Font.BOLD, 14));
		btnAddToFavourites.setBorder(null);
		btnAddToFavourites.setBackground(new Color(0, 206, 209));
		btnAddToFavourites.setBounds(592, 95, 179, 29);
		frame.getContentPane().add(btnAddToFavourites);
		btnAddToFavourites.setVisible(false);
	}
	
	public void configureUIListeners() {
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchText = tfSearchBox.getText();
				
				if (cbFilter.getSelectedIndex()!=-1 || !searchText.isEmpty()) {
					updateJList();
					btnAddToFavourites.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(btnSearch, "Please select a filter and introduce text in the search box.");
				}
			
			}
		});
		
		btnAddToFavourites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog("Type file's name:");
				
				String[] choices = { ",", ";", "Tabulator"};
				String selectedSeparator = (String) JOptionPane.showInputDialog(null, "Choose your data separator:",
			        "Separator selector", JOptionPane.QUESTION_MESSAGE, null, choices,choices[0]);
				addFavourites(selectedSeparator, fileName);
			}
		});
	}
	
	public void updateJList() {
		defaultStringListModel = new DefaultListModel<String>();
		for (Show s : getSearchList()) {
		((DefaultListModel<String>) defaultStringListModel).add(0, s.toString());
		}
		jlShowList.setModel(defaultStringListModel);
		jlShowList.setSelectedIndex(-1);
	}
	
	public ArrayList<Show> getSearchList() {
		this.showsList = showDAO.search(cbFilter.getSelectedIndex(), tfSearchBox.getText());
		return this.showsList;
	}
	
	public void addFavourites(String separador, String fileName) {
	
		FavShowsWriter favsWriter = new FavShowsWriter();
		
		for (int i = 0; i < jlShowList.getSelectedIndices().length; i++) {
			
			favsWriter.addFavShow(this.showsList.get(jlShowList.getSelectedIndices()[i]), usuarioActivo.getUsername(), fileName, separador);
			
		}
		
	
	}
}
