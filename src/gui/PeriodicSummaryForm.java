package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Database.Manager;
import Database.Property;

public class PeriodicSummaryForm {

	JFrame frame = new JFrame();
	private static JPanel housePanel;
	private static JLabel totalHouseLabel;
	private static JLabel numTotalLabel;
	private static JLabel rentedHouseLabel;
	private static JLabel numRentedLabel;
	private static JLabel activeHouseLabel;
	private static JLabel numActiveLabel;

	private static JPanel rentedHousePanel;
	private static JTextArea display;
	private static JScrollPane scroll;

	private ArrayList<Property> rentedProperties;

	PeriodicSummaryForm() throws SQLException {

		housePanel = new JPanel();
//		housePanel.setBackground(Color.red);
		housePanel.setBounds(0, 0, 300, 100);

		totalHouseLabel = new JLabel("Total Number of Houses Listed:");
		housePanel.add(totalHouseLabel);

		numTotalLabel = new JLabel("");
		housePanel.add(numTotalLabel);

		rentedHouseLabel = new JLabel("Total Number of Houses Listed:");
		housePanel.add(rentedHouseLabel);

		numRentedLabel = new JLabel("");
		housePanel.add(numRentedLabel);

		activeHouseLabel = new JLabel("Total Number of Houses Listed:");
		housePanel.add(activeHouseLabel);

		numActiveLabel = new JLabel("");
		housePanel.add(numActiveLabel);

		Manager myManager = new Manager();
//
		String total = Integer.toString(myManager.totalProperties());
		String rented = Integer.toString(myManager.totalProperties("Rented"));
		String active = Integer.toString(myManager.totalProperties("Active"));

		numTotalLabel.setText(total);
		numRentedLabel.setText(rented);
		numActiveLabel.setText(active);

		rentedHousePanel = new JPanel();
		rentedHousePanel.setBorder(new TitledBorder(new EtchedBorder(), "List of Houses Rented This Period"));
		rentedHousePanel.setBounds(50, 100, 700, 400);

		display = new JTextArea(22, 52);
		display.setEditable(false); // set textArea non-editable
		String landlordColumn = "Landlord Name \t";
		String idColumn = "Property ID \t";
		String addressColumn = "Address \n \n ";

		display.append(landlordColumn);
		display.append(idColumn);
		display.append(addressColumn);

		
		// display.append(myText);
		// display.append(text2);
		// display.append(myText);
		// display.append(myText);
		// display.append(myText);display.append(myText);display.append(myText);display.append(myText);display.append(myText);display.append(myText);display.append(myText);display.append(myText);display.append(myText);display.append(myText);
		
		// will have while loop that pulls from db and displays on gui

		// call the function to get rented properties from database and store into rentedProperties
		rentedProperties = myManager.searchProperties("Rented");

		for (int i = 0; i<rentedProperties.size(); i++ ){
			String currLandlord = rentedProperties.get(i).getLandlord().getName() + " \t \t";
			String currID = Integer.toString(rentedProperties.get(i).getID())+ "\t";
			String currAddress = rentedProperties.get(i).getAddress() + "\n \n ";

		display.append(currLandlord);
		display.append(currID);
		display.append(currAddress);

		}
		
		


		scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Add Textarea in to middle panel
		rentedHousePanel.add(scroll);

		frame.add(housePanel);
		frame.add(rentedHousePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(800, 600); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		frame.setVisible(true);

	}

}
