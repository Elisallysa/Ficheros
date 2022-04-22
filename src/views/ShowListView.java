package views;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ShowListView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		JPanel panel = new JPanel(new BorderLayout());
		List<String> myList = new ArrayList<>(10);

		final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		panel.add(scrollPane);
		JFrame frame = new JFrame("Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(500, 250);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public ShowListView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
