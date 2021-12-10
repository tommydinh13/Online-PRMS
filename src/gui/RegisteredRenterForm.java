package gui;

import Database.Property;
import Database.PropertyList;
import Database.RegisteredRenter;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RegisteredRenterForm implements ActionListener {
  JFrame frame = new JFrame();
  private static JButton searchButton;
  private static JButton unsubButton;
  private static JButton logoutButton;
  private static JButton notifButton;

private static JButton thisButton;
  private static RegisteredRenter renter;
  private static int renterID;

  RegisteredRenterForm(int id) {
    renter = new RegisteredRenter(id);
    renterID = id;

    // create button for search properties
    searchButton =
        new JButton("Search Properties"); // create button and give label
    searchButton.setBounds(80, 150, 200, 40);
    searchButton.setFocusable(false);
    searchButton.addActionListener(this);
    frame.add(searchButton);

    // unsubscribe button
    unsubButton = new JButton("Unsubscribe"); // create button and give label
    unsubButton.setBounds(250, 315, 125, 20);
    unsubButton.setFocusable(false);
    unsubButton.addActionListener(this);
    frame.add(unsubButton);

    // logout button
    logoutButton = new JButton("Logout"); // create button and give label
    logoutButton.setBounds(10, 10, 125, 30);
    logoutButton.setFocusable(false);
    logoutButton.addActionListener(this);
    frame.add(logoutButton);

	// JUST FOR TESTING
	thisButton = new JButton("test"); // create button and give label
    thisButton.setBounds(10, 300, 125, 30);
    thisButton.setFocusable(false);
    thisButton.addActionListener(this);
    frame.add(thisButton);

    // image icon used to upload image to button
    ImageIcon icon =
        new ImageIcon("src/gui/Images/bell.png"); // create imageicon that takes
                                                  // in the file of the image
    Image img = icon.getImage();                  // create image
    Image newimg = img.getScaledInstance(
        30, 30, java.awt.Image.SCALE_SMOOTH); // scale image
    icon = new ImageIcon(newimg);             // replace with new scaled image

    // notification button
    notifButton = new JButton();            // create button
    notifButton.setBounds(300, 10, 50, 50); // set bounds
    notifButton.setFocusable(false);
    notifButton.setIcon(icon); // set icon
    notifButton.addActionListener(
        this); // actionlistener gives button functionality
    frame.add(notifButton);

    // frame for RegisteredRenterForm
    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE); // exiting window will close window
    frame.setSize(400, 400);   // setting size of window
    frame.setLayout(null);     // no layout
    frame.setTitle("Rental Property Management System");
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == searchButton) {
      // if this button is pressed
      // create a SearchCriteriaForm with the renterID
      SearchCriteriaForm search = new SearchCriteriaForm(renterID);

    }

    else if (e.getSource() == unsubButton) {
		// if this button is pressed
      renter.unsubscribe();	//unsubscribe the renter
      frame.dispose();	// close frame
      JOptionPane.showMessageDialog(
          null, "You have been successfully Unsubscribed", "Unsubscribe",
          JOptionPane.INFORMATION_MESSAGE);	// display message
      LoginForm newLogin = new LoginForm();	// goto login form
    } else if (e.getSource() == logoutButton) {
		
      frame.dispose();
      LoginForm login = new LoginForm();
    } 
	else if (e.getSource() == notifButton) {
      renter = new RegisteredRenter(renterID);
      if (renter.getNotify().equals("YES")) {
        JOptionPane.showMessageDialog(
            null, "There are new listing(s) matching your Search Criteria!",
            "Notification", JOptionPane.INFORMATION_MESSAGE);
        renter.setNotify();
      } else {
        JOptionPane.showMessageDialog(
            null, "There are NO new listing(s) matching your Search Criteria",
            "Notification", JOptionPane.INFORMATION_MESSAGE);
      }
    }
	else if(e.getSource()  == thisButton){
    Property newProp = new Property("134", "afd", 2, 4, "adsf", "sad", 92);
    newProp.setID(37);
    PropertyList myList = new PropertyList();
		myList.addProperty(newProp);
	}
  }
}
