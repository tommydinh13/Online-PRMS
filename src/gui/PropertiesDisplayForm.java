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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class PropertiesDisplayForm implements ActionListener {
  JFrame frame = new JFrame();
  private static JPanel titlePanel;
  private static JLabel titleLabel;
  private static JPanel bodyPanel;

  private static JButton detailButton;
  private static JButton contactButton;
  private static JTextArea display;
  private static JScrollPane scroll;

  private ArrayList<Property> myList;
  PropertiesDisplayForm(ArrayList<Property> p) {
    myList = p;

    bodyPanel = new JPanel();
    bodyPanel.setBorder(
        new TitledBorder(new EtchedBorder(), "List of Properties"));
    bodyPanel.setBounds(50, 100, 700, 400);

    display = new JTextArea(22, 52);
    display.setEditable(false); // set textArea non-editable
    scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    bodyPanel.add(scroll);

    // columns for displaying a property
    display.append("ID\t\t");         // id column
    display.append("Address\t\t");    // address column
    display.append("House Type\t\t"); // housetype column
    display.append("Bath\t\t");       // bath column
    display.append("Bed\t\t");        // bed column
    display.append("Furnish\t\t");    // furnish column
    display.append("Quadrant\t\t");   // quadrant column
    display.append("Price\n\n");      // price column

    // loop that will run through a list of properties and display
    // its components
    for (int i = 0; i < myList.size(); i++) {

      String id = Integer.toString(myList.get(i).getID()) + " \t \t"; // get id
      String address = myList.get(i).getAddress()+ "\t"; // get address
      String ht = myList.get(i).getHouseType() + " \t \t"; // get housetype
      String bath = Integer.toString(myList.get(i).getBathrooms()) +
                    " \t \t"; // get bath num
      String bed = Integer.toString(myList.get(i).getBedrooms()) +
                   " \t \t"; // get bed num
      String furnish =
          myList.get(i).getFurnishedStatus() + " \t \t"; // get furnish state
      String quadrant =
          myList.get(i).getCityQuadrant() + " \t \t"; // get quadrant
      String price = "$" + Double.toString(myList.get(i).getPrice()) +
                     " \n \n"; // get price

      display.append(id);       // display id
      display.append(address);  // display address
      display.append(ht);       // display housetype
      display.append(bath);     // display bath num
      display.append(bed);      // display bed num
      display.append(furnish);  // display furnish state
      display.append(quadrant); // display quadrant
      display.append(price);    // display price
    }

    // more detail button
    detailButton =
        new JButton("Get More Details");       // create button and give label
    detailButton.setBounds(600, 520, 150, 60); // set bounds
    detailButton.addActionListener(
        this); // actionlistener gives button functionality

    // contact landlord about property
    contactButton =
        new JButton("Contact Property");        // create button and give label
    contactButton.setBounds(400, 520, 150, 60); // set bounds
    contactButton.addActionListener(
        this); // actionlistener gives button functionality

    // frame for display properties form
    frame.add(bodyPanel);
    frame.add(detailButton);
    frame.add(contactButton);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(850, 700);      // setting size of window
    frame.setLayout(null);        // no layout
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {

    if ((e.getSource() == detailButton) || (e.getSource() == contactButton)) {

      
      // if one of these buttons are clicked
      // create a new email that takes in the property id that the user inputs
      String insert = JOptionPane.showInputDialog("Please Enter Property ID:");
      // System.out.println(insert);
      EmailForm myEmail = new EmailForm(insert);
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
