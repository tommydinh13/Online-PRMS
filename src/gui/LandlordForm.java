package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;

// once a landlord logins,
// these are the actions they are able to perform
public class LandlordForm implements ActionListener {
  JFrame frame = new JFrame();
  private static JButton sofButton;
  private static JButton payFeeButton;
  private static JButton regisButton;
  private static JButton searchButton;
  private static JButton inboxButton;
  private static JButton logoutButton;

  private static int idLL;

  LandlordForm(int id) {
    idLL = id;

    // state of listing button
    sofButton = new JButton("Change State of Listing");
    sofButton.setBounds(80, 40, 200, 40);
    sofButton.setFocusable(false);
    sofButton.addActionListener(this);
    frame.add(sofButton);

    // register property button
    regisButton = new JButton("Register New Property");
    regisButton.setBounds(80, 120, 200, 40);
    regisButton.setFocusable(false);
    regisButton.addActionListener(this);
    frame.add(regisButton);

    // pay property fee button
    payFeeButton = new JButton("Pay Fee for Property");
    payFeeButton.setBounds(80, 200, 200, 40);
    payFeeButton.setFocusable(false);
    payFeeButton.addActionListener(this);
    frame.add(payFeeButton);

    // email inbox button
    inboxButton = new JButton("Email Inbox");
    inboxButton.setBounds(80, 280, 200, 40);
    inboxButton.setFocusable(false);
    inboxButton.addActionListener(this);
    frame.add(inboxButton);

    // logout button
    logoutButton = new JButton("Logout");
    logoutButton.setBounds(275, 10, 100, 20);
    logoutButton.setFocusable(false);
    logoutButton.addActionListener(this);
    frame.add(logoutButton);

    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE); // exiting window will close window
    frame.setSize(400, 400);   // setting size of window
    frame.setLayout(null);     // no layout
    frame.setTitle("Rental Property Management System");
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == sofButton) {
      // if state of listing button is pressed
      // create a new SOLForm object that takes in the landlord ID
      SOLForm myForm = new SOLForm(idLL);
    }

    else if (e.getSource() == regisButton) {
      // create RegisterPropertyForm object if regisButton is pressed
      RegisterPropertyForm newProperty = new RegisterPropertyForm(idLL);
    }

    else if (e.getSource() == payFeeButton) {
      // if pay fee button pressed, PayForm is created

      PayForm pay = new PayForm(idLL);
    } else if (e.getSource() == logoutButton) {
      // if logout button pressed, close the frame
      // open back up login form
      frame.dispose();
      LoginForm login = new LoginForm();
    }

    if (e.getSource() == inboxButton) {
      // if inbox button pressed,InboxForm is created
      InboxForm myInbox = new InboxForm(idLL);
    }
  }
}
