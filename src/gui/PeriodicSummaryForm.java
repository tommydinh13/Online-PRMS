/**
 * @author Kundai Dziwa <a href="mailto:kundai.dziwa@ucalgary.ca">
 *         kundai.dziwa@ucalgary.ca</a>
 *
*@author Tommy Dinh <a href="mailto:tommy.dinh@ucalgary.ca">
 *         tommy.dinh@ucalgary.ca</a>
 * 
*@author Tien Dat Johny Do <a href ="tiendat.do@ucalgary.ca">
 *        tiendat.do@ucalgary.ca</a>
 * 
 *@author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 * 
 * @version 1.1
 * @since 1.0
 */ 
package gui;

import Domain.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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

  PeriodicSummaryForm() {

    // panel for house information
    housePanel = new JPanel();
    housePanel.setBounds(0, 0, 300, 100);

    totalHouseLabel = new JLabel("Total Number of Properties Listed:");
    housePanel.add(totalHouseLabel);

    numTotalLabel = new JLabel(""); // total houses set to nothing
    housePanel.add(numTotalLabel);

    rentedHouseLabel = new JLabel("Total Number of Rented Properties Listed:");
    housePanel.add(rentedHouseLabel);

    numRentedLabel = new JLabel(""); // rented houses set to nothing
    housePanel.add(numRentedLabel);

    activeHouseLabel = new JLabel("Total Number of Active Properties Listed:");
    housePanel.add(activeHouseLabel);

    numActiveLabel = new JLabel(""); // active houses set to nothing
    housePanel.add(numActiveLabel);

    Manager myManager = new Manager();

    String total =
        Integer.toString(myManager.totalProperties()); // get total properties
    String rented = Integer.toString(
        myManager.totalProperties("Rented")); // get total rented properties
    String active = Integer.toString(
        myManager.totalProperties("Active")); // get total active properties

    numTotalLabel.setText(total);   // display total properties
    numRentedLabel.setText(rented); // display total rented properties
    numActiveLabel.setText(active); // display total active properties

    // panel that is going to show the rented properties
    // with some details
    rentedHousePanel = new JPanel();
    rentedHousePanel.setBorder(new TitledBorder(
        new EtchedBorder(), "List of Houses Rented This Period"));
    rentedHousePanel.setBounds(50, 100, 700, 400);

    // an area to display the rented properties information
    display = new JTextArea(22, 52);
    display.setEditable(false); // set textArea non-editable
    String landlordColumn = "Landlord Name \t";
    String idColumn = "Property ID \t";
    String addressColumn = "Address \n \n ";

    display.append(landlordColumn);
    display.append(idColumn);
    display.append(addressColumn);

    // ArrayList of properties that are rented this period
    rentedProperties = myManager.searchProperties("Rented");

    // loop that runs through the rented properties to get components
    for (int i = 0; i < rentedProperties.size(); i++) {
      // get landlord, property id, and address of the rented properties
      String currLandlord = rentedProperties.get(i).getLandlord().getName() +
                            " \t \t"; // get landlord
      String currID = Integer.toString(rentedProperties.get(i).getID()) +
                      "\t"; // get property ID
      String currAddress =
          rentedProperties.get(i).getAddress() + "\n \n "; // get address

      display.append(currLandlord); // display the landlord
      display.append(currID);       // display the ID
      display.append(currAddress);  // display the address
    }

    // scroll bar for display
    scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

    // Add Textarea to rented house panel
    rentedHousePanel.add(scroll);

    // frame for periodic summary form
    frame.add(housePanel);
    frame.add(rentedHousePanel);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(800, 600);      // setting size of window
    frame.setLayout(null);        // no layout
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }
}
