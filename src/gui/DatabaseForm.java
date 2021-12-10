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

public class DatabaseForm {
  JFrame frame = new JFrame();
  private static JPanel titlePanel;
  private static JLabel titleLabel;
  private static JPanel bodyPanel;

  private static JTextArea display;
  private static JScrollPane scroll;

  private String titleRole;
  private String listRole;

  DatabaseForm(int num) {

    Manager myManager = new Manager();

    // depending on user input, we choose to either search for
    // properties, rentesr, or landlords
    if (num == 2) {
      titleRole = "Property";
      listRole = "Properties";
    } else if (num == 1) {
      titleRole = "Renter";
      listRole = "Renters";
    } else {
      titleRole = "Landlord";
      listRole = "Landlords";
    }

    // components for title panel
    titlePanel = new JPanel();
    titlePanel.setBounds(0, 0, 300, 100);
    titleLabel = new JLabel(titleRole + " Database");
    titlePanel.add(titleLabel);

    // components for body panel
    bodyPanel = new JPanel();
    bodyPanel.setBorder(
        new TitledBorder(new EtchedBorder(), "List of " + listRole));
    bodyPanel.setBounds(50, 100, 700, 400);

    // creating space that will display
    // the information pulled from the database
    display = new JTextArea(22, 52);
    display.setEditable(false); // set textArea non-editable
    scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    bodyPanel.add(scroll);

    // if user input was property,
    // create titles for columns that fit property databse
    if (num == 2) {
      display.append("ID\t\t");
      display.append("Address\t\t");
      display.append("House Type\t\t");
      display.append("Bath\t\t");
      display.append("Bed\t\t");
      display.append("Furnish\t\t");
      display.append("Quadrant\t\t");
      display.append("Price\t\t");
      display.append("State of Listing\t\t");
      display.append("Landlord \n\n");

      // algorithim for getting elements in property from
      // an arraylist of properties
      ArrayList<Property> myProperties = myManager.searchProperties();
      // loop that runs through all properties in the database
      for (int i = 0; i < myProperties.size(); i++) {
        // get the element and set as a string
        String id = Integer.toString(myProperties.get(i).getID()) + " \t \t";
        String address = myProperties.get(i).getAddress() + " \t \t";
        String ht = myProperties.get(i).getHouseType() + " \t \t";
        String bath =
            Integer.toString(myProperties.get(i).getBathrooms()) + " \t \t";
        String bed =
            Integer.toString(myProperties.get(i).getBedrooms()) + " \t \t";
        String furnish = myProperties.get(i).getFurnishedStatus() + " \t \t";
        String quadrant = myProperties.get(i).getCityQuadrant() + " \t \t";
        String price =
            Double.toString(myProperties.get(i).getPrice()) + " \t \t";
        String sol = myProperties.get(i).getStateofListing() + " \t \t";
        String landlord = myProperties.get(i).getLandlord().getName() + " \n\n";

        // add the elements to the display space
        display.append(id);
        display.append(address);
        display.append(ht);
        display.append(bath);
        display.append(bed);
        display.append(furnish);
        display.append(quadrant);
        display.append(price);
        display.append(sol);
        display.append(landlord);
      }

      // if user selection was registered renter,
    } else if (num == 1) {
      ArrayList<RegisteredRenter> myRenters = myManager.searchRenters();
      // create titles for columns that fit renter databse
      display.append("ID\t\t");
      display.append("Name\t\t");
      display.append("Email\t\t");
      display.append("Password\n\n");
      // using same algorithim for getting elements in renter from
      // an arraylist of RegisteredRenters
      // loop that runs through all registered renters
      for (int i = 0; i < myRenters.size(); i++) {

        // setting the elements to a string
        String id = Integer.toString(myRenters.get(i).getID()) + " \t \t";
        String name = myRenters.get(i).getName() + " \t \t";
        String email = myRenters.get(i).getEmail() + " \t \t";
        String password = myRenters.get(i).getPassword() + " \n\n";

        // adding values to the display space
        display.append(id);
        display.append(name);
        display.append(email);
        display.append(password);
      }

      // if user selects landlord
    } else if (num == 0) {
      // search for all landlords
      ArrayList<Landlord> myLandlords = myManager.searchLandlords();

      // creating columns for components of landlord
      display.append("ID\t\t");
      display.append("Name\t\t");
      display.append("Email\t\t");
      display.append("Password\n\n");

      // loop that runs through all landlords in db
      for (int i = 0; i < myLandlords.size(); i++) {
        // setting landlord elements to a string
        String id = Integer.toString(myLandlords.get(i).getID());
        String name = myLandlords.get(i).getName();
        String email = myLandlords.get(i).getEmail();
        String password = myLandlords.get(i).getPassword() + " \n\n";

        // adding the elements to the display space for amanger to see
        display.append(checkLength(id));
        display.append(checkLength(name));
        display.append(checkLength(email));
        display.append(password);
      }
    }

    // the construction of the frame for DatabaseForm
    frame.add(titlePanel);
    frame.add(bodyPanel);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(800, 600);      // setting size of window
    frame.setLayout(null);        // no layout
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }

  // check method to determine how many tabs is needed for a string
  // so that the format in display matches properly
  public String checkLength(String s) {
    if (s.length() < 14) {
      return s + "\t\t";
    } else if (s.length() == 14) {
      return s + "\t\t";
    }

    return s + "\t";
  }
}
