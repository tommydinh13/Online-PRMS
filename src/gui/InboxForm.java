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
    for (int i = 0; i < emails.size(); i++) {

      String emailID = Integer.toString(emails.get(i).getID()) + " \t ";
      String propID = Integer.toString(emails.get(i).getProperty()) + " \t ";
      String from = emails.get(i).getRenter() + " \t\t";
      String subject = emails.get(i).getSubject() + " \n\n";

      display.append(emailID);
      display.append(propID);
      display.append(from);
      display.append(subject);
    }

    viewButton = new JButton("View Email");
    viewButton.setBounds(600, 475, 100, 50);
    viewButton.addActionListener(this);

    frame.add(bodyPanel);
    frame.add(viewButton);
    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE); // exiting window will close window
    frame.setSize(800, 600);   // setting size of window
    frame.setLayout(null);     // no layout
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == viewButton) {
      // pop up window that user inputs email id
      // need a check to make sure that email id entered was in the landlord
      // email db
      int emailID = Integer.parseInt(
      JOptionPane.showInputDialog("Please Enter Email ID:"));
      System.out.println(emailID);
      DisplayEmail viewEmail = new DisplayEmail(emailID, landlordID);
      frame.dispose();
    }
  }
}
