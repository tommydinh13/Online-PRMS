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


public class DisplayEmail implements ActionListener {
  // frame
  JFrame frame = new JFrame();

  // email address components
  private static JPanel eaPanel;
  private static JLabel eaLabel;
  private static JTextField eaText;

  // email subject components
  private static JPanel subjectPanel;
  private static JLabel subjectLabel;
  private static JTextField subjectText;

  // email body components
  private static JPanel bodyPanel;
  private static JLabel bodyLabel;
  private static JTextArea display;
  private static JScrollPane scroll;

  // delete email button
  private static JButton deleteButton;

  private static int emailID;
  private static int landlordID;
  private Email email;
  private Landlord mylandlord;

  DisplayEmail(int eid, int lID) {

    emailID = eid;
    landlordID = lID;
    email = new Email(emailID);

    //  email address panel
    eaPanel = new JPanel();
    eaPanel.setBounds(0, 50, 700, 50);

    // label that displays "From" for email address
    eaLabel = new JLabel("From:");
    eaPanel.add(eaLabel);

    // who the email will be from
    eaText = new JTextField(50);
    eaText.setEditable(false);
    eaText.setText(email.getRenter());
    eaPanel.add(eaText);

    // panel for subject
    subjectPanel = new JPanel();
    subjectPanel.setBounds(0, 100, 700, 50);

    // label for subject
    subjectLabel = new JLabel("Subject:");
    subjectPanel.add(subjectLabel);

    // text that will display the subject of the email
    subjectText = new JTextField(50);
    subjectText.setEditable(false);
    subjectText.setText(email.getSubject());
    subjectPanel.add(subjectText);

    // creating body panel
    bodyPanel = new JPanel();
    bodyPanel.setBounds(0, 175, 700, 400);
    bodyPanel.setBorder(new TitledBorder(new EtchedBorder(),
                                         "List of Houses Rented This Period"));

    // display is for the body of the email
    display = new JTextArea(22, 53); // size of display box
    display.setEditable(false);      // user cannot type in box
    display.append(email.getBody()); // get body of email and add to display
    // scroll bar for displaying body
    scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    bodyPanel.add(scroll);

    // delete button
    deleteButton = new JButton("Delete");
    deleteButton.setBounds(500, 575, 110, 30);
    deleteButton.setFocusable(false);
    deleteButton.addActionListener(this);
    frame.add(deleteButton);

    // frame for DisplayEmail
    frame.add(eaPanel);
    frame.add(subjectPanel);
    frame.add(bodyPanel);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(720, 700);   // setting size of window
    frame.setLayout(null);     // no layout
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // when deleteButton is clicked,
    // the email should be deleted from database
    // and will call InboxForm again to show updated information
    if (e.getSource() == deleteButton) {
      mylandlord = new Landlord(landlordID);
      mylandlord.deleteEmail(emailID);
      frame.dispose();
      InboxForm updated = new InboxForm(landlordID);

      // display email delete success
      JOptionPane.showMessageDialog(null, "Message Deleted Successfully",
                                    "Email Deleted", JOptionPane.PLAIN_MESSAGE);
    }
  }
}
