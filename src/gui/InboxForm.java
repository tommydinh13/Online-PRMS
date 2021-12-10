package gui;

import Database.Email;
import Database.Landlord;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

// inbox is meant to be used for landlord when checking their Emails
// gives them a brief outline of what the email is about
public class InboxForm implements ActionListener {
  JFrame frame = new JFrame();
  private static JPanel bodyPanel;

  private static JTextArea display;
  private static JScrollPane scroll;

  private static JButton viewButton;

  private static int landlordID;
  private Landlord myLandlord;

  InboxForm(int id) {
    landlordID = id;
    myLandlord = new Landlord(id);

    // panel for the inbox
    bodyPanel = new JPanel();
    bodyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Inbox"));
    bodyPanel.setBounds(50, 50, 700, 400);

    // ********* INBOX **********
    display = new JTextArea(22, 52);
    display.setEditable(false); // set textArea non-editable
    scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    bodyPanel.add(scroll);

    // adding column titles to inbox
    display.append("Email ID\t");
    display.append("Property ID\t");
    display.append("From\t\t");
    display.append("Subject\n\n");

    // create an arraylist of emails
    ArrayList<Email> emails = myLandlord.viewInbox();
    // loop that runs through the landlord's emails
    for (int i = 0; i < emails.size(); i++) {
      // get emailID, propID, sender of email, and subject of email
      // put these values into strings
      String emailID = Integer.toString(emails.get(i).getID()) + " \t ";
      String propID = Integer.toString(emails.get(i).getProperty()) + " \t ";
      String from = emails.get(i).getRenter() + " \t\t";
      String subject = emails.get(i).getSubject() + " \n\n";

      // adding these values for a specific email to the inbox
      display.append(emailID);
      display.append(propID);
      display.append(from);
      display.append(subject);
    }

    // a button to view a chosen email
    viewButton = new JButton("View Email");
    viewButton.setBounds(600, 475, 100, 50);
    viewButton.addActionListener(this);

    // frame for InboxForm
    frame.add(bodyPanel);
    frame.add(viewButton);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(800, 600);      // setting size of window
    frame.setLayout(null);        // no layout
    frame.setTitle("Rental Property Management System");
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    // view button is used to view the specified email
    if (e.getSource() == viewButton) {
      // pop up window that user inputs email id
      // user enters the email id that they want to open
      int emailID = Integer.parseInt(
          JOptionPane.showInputDialog("Please Enter Email ID:"));

      // create a DisplayEmail object that recieves the landlordID and the
      // emailID
      DisplayEmail viewEmail = new DisplayEmail(emailID, landlordID);
      frame.dispose();
    }
  }
}
