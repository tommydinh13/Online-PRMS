package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RegisteredRenterForm implements ActionListener {
	JFrame frame = new JFrame();
	private static JButton searchButton;
	private static JButton unsubButton;

	private static int idRenter;

	RegisteredRenterForm(int id) {
		idRenter = id;

		searchButton = new JButton("Search Properties");
		searchButton.setBounds(80, 150, 200, 40);
		searchButton.setFocusable(false);
		searchButton.addActionListener(this);
		frame.add(searchButton);

		unsubButton = new JButton("Unsubscribe");
		unsubButton.setBounds(250, 315, 125, 20);
		unsubButton.setFocusable(false);
		unsubButton.addActionListener(this);
		frame.add(unsubButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(400, 400); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == searchButton) {
			SearchCriteriaForm search = new SearchCriteriaForm();

		}

		if (e.getSource() == unsubButton) {
			// unsub the user
		}

	}

}
