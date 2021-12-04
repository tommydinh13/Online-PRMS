package gui;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Database.Manager;

public class PeriodicSummaryForm {

	JFrame frame = new JFrame();
	private static JPanel housePanel;
	private static JLabel totalHouseLabel;
	private static JLabel numTotalLabel;
	private static JLabel rentedHouseLabel;
	private static JLabel numRentedLabel;
	private static JLabel activeHouseLabel;
	private static JLabel numActiveLabel;

	PeriodicSummaryForm() throws SQLException {

		housePanel = new JPanel();
//		housePanel.setBackground(Color.red);
		housePanel.setBounds(0, 0, 250, 250);

		totalHouseLabel = new JLabel("Total Number of Houses Listed:");
		housePanel.add(totalHouseLabel);

		numTotalLabel = new JLabel("");
		housePanel.add(numTotalLabel);

		rentedHouseLabel = new JLabel("Total Number of Houses Rented:");
		housePanel.add(rentedHouseLabel);

		numRentedLabel = new JLabel("");
		housePanel.add(numRentedLabel);

		activeHouseLabel = new JLabel("Total Number of Houses Active:");
		housePanel.add(activeHouseLabel);

		numActiveLabel = new JLabel("");
		housePanel.add(numActiveLabel);
		
		Manager myManager = new Manager();

		String total = Integer.toString(myManager.totalProperties());
		String rented =  Integer.toString(myManager.totalProperties("Rented"));
		String active =  Integer.toString(myManager.totalProperties("Active"));

		numTotalLabel.setText(total);
		numRentedLabel.setText(rented);
		numActiveLabel.setText(active);
		
		

		frame.add(housePanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(400, 400); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		frame.setVisible(true);

	}

}
