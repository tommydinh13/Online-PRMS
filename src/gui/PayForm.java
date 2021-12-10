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
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class PayForm implements ActionListener {

  JFrame frame = new JFrame();

  // title panel
  private static JPanel titlePanel;
  private static JLabel titleLabel;

  // button panel
  private static JPanel buttonPanel;
  private static JLabel idLabel;
  private static JTextField idText;
  private static JButton payButton;

  // display panel
  private static JPanel displayPanel;
  private static JTextArea display;
  private static JScrollPane scroll;

  ArrayList<Property> cancelledProperty;
  ArrayList<Property> suspendedProperty;
  private int landlordID;

  PayForm(int id) {
    landlordID = id;
    Landlord myLandlord;
    myLandlord = new Landlord(id);
    // search for all cancelled properties
    cancelledProperty = myLandlord.searchProperties("Cancelled");
    // serach for all suspended
    suspendedProperty = myLandlord.searchProperties("Suspended");
    landlordID = id;

    // ********* TITLE ************
    titlePanel = new JPanel();
    titlePanel.setBounds(0, 0, 800, 50);

    // set title
    titleLabel = new JLabel("Pay Property Fee");
    titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
    titlePanel.add(titleLabel);

    // ********* BUTTON PANEL ************
    buttonPanel = new JPanel();
    buttonPanel.setBounds(0, 50, 800, 75);

    idLabel = new JLabel("Property ID: ");
    idLabel.setFont(new Font("Courier", Font.PLAIN, 17));
    buttonPanel.add(idLabel);

    // textbox for user to input id
    idText = new JTextField(10);
    buttonPanel.add(idText);
    buttonPanel.add(Box.createHorizontalStrut(60));

    // button for pay
    payButton = new JButton("Pay");
    payButton.setBounds(new Rectangle(300, 500));
    payButton.addActionListener(this);
    buttonPanel.add(payButton);

    // ******** ACTIVE LISTINGS *******
    // panel used to display all cancelled and suspended
    // listings to be paid for
    displayPanel = new JPanel();
    displayPanel.setBorder(
        new TitledBorder(new EtchedBorder(), "Unpaid Listings"));
    displayPanel.setBounds(50, 150, 700, 400);

    // textbox that will show the listings
    display = new JTextArea(22, 52);
    display.setEditable(false); // set textArea non-editable
    scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    displayPanel.add(scroll);

    // columns for the listings
    display.append("Property ID\t\t");
    display.append("State of Listing\t\t");
    display.append("Address\n\n");

    // loop that runs through cancelled properties and recieves
    // certain components of the property to display
    for (int i = 0; i < cancelledProperty.size(); i++) {
      // property Id, state of listing, and address are all
      // passed to a string
      String propID = Integer.toString(cancelledProperty.get(i).getID());
      String state = cancelledProperty.get(i).getStateofListing();
      String address = cancelledProperty.get(i).getAddress();

      // display the components
      display.append(propID + "\t\t");
      display.append(state + "\t\t");
      display.append(address + "\n\n");
    }

    // loop that runs through suspended properties and recieves
    // certain components of the property to display
    for (int i = 0; i < suspendedProperty.size(); i++) {
      // property Id, state of listing, and address are all
      // passed to a string
      String propID = Integer.toString(suspendedProperty.get(i).getID());
      String state = suspendedProperty.get(i).getStateofListing();
      String address = suspendedProperty.get(i).getAddress();

      // display the components
      display.append(propID + "\t\t");
      display.append(state + "\t\t");
      display.append(address + "\n\n");
    }

    // ******** FRAME ********
    frame.add(titlePanel);
    frame.add(buttonPanel);
    frame.add(displayPanel);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(800, 600);      // setting size of window
    frame.setLayout(null);        // no layout
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String stringID = idText.getText();
    

    if (e.getSource() == payButton) {
      // if landlord clicks pay button
      // the property ID entered will be changed to active
      // error check
      if(!isInteger(stringID)){
                      JOptionPane.showMessageDialog(
            null, "Property ID must be an Integer!",
            "ID Error", JOptionPane.ERROR_MESSAGE);
      }else{
      int propID = Integer.parseInt(idText.getText());
          Landlord payLandlord;
        payLandlord = new Landlord(landlordID);
        payLandlord.changeSOL(propID, "Active");

        // display success
        JOptionPane.showMessageDialog(
          null, " Payment was Successful! Property is now Active.",
          "Payment Success", JOptionPane.INFORMATION_MESSAGE);


      }

    }
  }

   /*https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java*/
  public static boolean isInteger(String str) {
    if (str == null) {
        return false;
    }
    int length = str.length();
    if (length == 0) {
        return false;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
        if (length == 1) {
            return false;
        }
        i = 1;
    }
    for (; i < length; i++) {
        char c = str.charAt(i);
        if (c < '0' || c > '9') {
            return false;
        }
    }
    return true;
}
}
