package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.Manager;

public class ManagerForm implements ActionListener {

  JFrame frame = new JFrame();
  private static JButton sofButton;
  private static JButton perSumButton;
  private static JButton propFeeButton;
  private static JButton searchButton;
  private static JButton logoutButton;

  // once a manager logins,
  // these are the actions they are able to perform
  ManagerForm() {
    // change state of listings of active properties button
    sofButton = new JButton("Change State of Listing");
    sofButton.setBounds(80, 40, 200, 40);
    sofButton.setFocusable(false);
    sofButton.addActionListener(this);
    frame.add(sofButton);

    // change property fee and period button
    propFeeButton = new JButton("Change Property Fee");
    propFeeButton.setBounds(80, 120, 200, 40);
    propFeeButton.setFocusable(false);
    propFeeButton.addActionListener(this);
    frame.add(propFeeButton);

    // periodic summary button
    perSumButton = new JButton("Periodical Summary Report");
    perSumButton.setBounds(80, 200, 200, 40);
    perSumButton.setFocusable(false);
    perSumButton.addActionListener(this);
    frame.add(perSumButton);

    // search database button
    searchButton = new JButton("Search Database");
    searchButton.setBounds(80, 280, 200, 40);
    searchButton.setFocusable(false);
    searchButton.addActionListener(this);
    frame.add(searchButton);

    // logout button
    logoutButton = new JButton("Logout");
    logoutButton.setBounds(275, 10, 100, 20);
    logoutButton.setFocusable(false);
    logoutButton.addActionListener(this);
    frame.add(logoutButton);

    // frame for managerform
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
      // if this button is pressed,
      // create a SOLForm that takes in no arguments
      SOLForm myForm = new SOLForm();
    }

    // DONT KNOW IF THIS WORKS
    else if (e.getSource() == propFeeButton) {
      // changing method should have a check method that checks
      // values entered are correct since it will be taking in a string

      // pressing this button will open a window that
      // allows manager to input a period and a fee
      // for the new property fee
      JTextField periodField = new JTextField(5);
      JTextField feeField = new JTextField(5);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Period:"));
      myPanel.add(periodField);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Fee:"));
      myPanel.add(feeField);

      int result = JOptionPane.showConfirmDialog(
          null, myPanel, "Please Enter Period and Fee Values",
          JOptionPane.OK_CANCEL_OPTION);

      if (result == JOptionPane.OK_OPTION) {
        int period = Integer.parseInt(periodField.getText());
        double fee = Double.parseDouble(feeField.getText());
        Manager myManager= new Manager();
        myManager.enterPropertyFee(period, fee);

        // create property fee object that passes through
        // periodField.getText() and feeField.getText()
        // constructor for property fee should update database
      }
      
    }

    else if (e.getSource() == perSumButton) {
      PeriodicSummaryForm form = new PeriodicSummaryForm();
    }

    else if (e.getSource() == searchButton) {
      // joption pane to choose landlord, registered renter, property
      // use choice to open up form with info displaying category
      String[] responses = {"Landlords", "Renters", "Properties"};
      int choice = JOptionPane.showOptionDialog(
          null, "Select Database to Search", "Search Database",
          JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
          null, responses, 0);

      // pass in
      if (choice == 2) {
        frame.dispose();
        DatabaseForm myform = new DatabaseForm(choice);
      } else if (choice == 1) {
        frame.dispose();
        DatabaseForm myform = new DatabaseForm(choice);
      } else if (choice == 0) {
        frame.dispose();
        DatabaseForm myform = new DatabaseForm(choice);
      }

      // want one form that brings up info of category
    } else if (e.getSource() == logoutButton) {
      frame.dispose();
      LoginForm login = new LoginForm();
    }
  }
}
