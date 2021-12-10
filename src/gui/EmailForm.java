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


// this class is the for the user to construct an email
public class EmailForm implements ActionListener {
  // frame
  JFrame frame = new JFrame();

  // title
  private static JPanel titlePanel;
  private static JLabel titleLabel;

  // email subject
  private static JPanel subjectPanel;
  private static JLabel subjectLabel;
  private static JTextField subjectText;

  // email body
  private static JPanel bodyPanel;
  private static JLabel bodyLabel;
  private static JTextArea display;
  private static JScrollPane scroll;

  // button to send email
  private static JButton sendButton;

  // email address
  private static JPanel eaPanel;
  private static JLabel eaLabel;
  private static JTextField eaText;

  private static int propID;

  EmailForm(String id) {

    propID = Integer.parseInt(id);

    // takes in property id to get landlord email and send email

    titlePanel = new JPanel();
    //		titlePanel.setBackground(Color.green);
    titlePanel.setBounds(0, 0, 700, 50);

    titleLabel = new JLabel("Email Form"); // label that goes beside textbox to
                                           // tell user what to enter
    titlePanel.add(titleLabel);

    // *********** EMAIL ADDRESSS ***************/
    eaPanel = new JPanel();
    eaPanel.setBounds(0, 50, 700, 50);
    // Label that says "From"
    eaLabel = new JLabel("From:");
    eaPanel.add(eaLabel);
    // space for user to enter their email address
    eaText = new JTextField(50);
    eaPanel.add(eaText);

    // *********** EMAIL SUBJECT ***************/
    subjectPanel = new JPanel();
    subjectPanel.setBounds(0, 100, 700, 50);
    // label for subject
    subjectLabel = new JLabel("Subject:");
    subjectPanel.add(subjectLabel);
    // space for user to enter the subject of the email
    subjectText = new JTextField(50);
    subjectPanel.add(subjectText);
    // *********** EMAIL BODY ***************/
    bodyPanel = new JPanel();
    bodyPanel.setBounds(0, 175, 700,
                        400); // boundaries of panel on the frame
                              // format of the panel (different looking border)
    bodyPanel.setBorder(new TitledBorder(new EtchedBorder(),
                                         "Body"));

    // space for user to send the body portion of their email
    display = new JTextArea(22, 53); // size of space
    display.setEditable(true);       // user is able to type in the space

    // scroll bar component for display
    scroll = new JScrollPane(display);
    scroll.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    bodyPanel.add(scroll);

    // *********** SEND BUTTON ***************/
    sendButton = new JButton("Send");        // create and have button say send
    sendButton.setBounds(500, 575, 110, 30); // boundaries
    sendButton.setFocusable(false);
    sendButton.addActionListener(this);
    frame.add(sendButton);

    // *********** FRAME ***************/
    // adding panels to the frame
    frame.add(titlePanel);
    frame.add(eaPanel);
    frame.add(subjectPanel);
    frame.add(bodyPanel);
    frame.setDefaultCloseOperation(
        JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
    frame.setSize(720, 700);      // setting size of window
    frame.setLayout(null);        // no layout
    frame.setTitle("Rental Property Management System");

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == sendButton) {
      // if thhe user clicks sendButton
      //	get the texts that the user inputted to
      // email address, subject, and body
      // these values are passed to create an Email object
      // that creates a draft of the email then sends the email to db/landlord
      // must do a check if strings are null
      String email = eaText.getText();
      String subject = subjectText.getText();
      String body = display.getText();

      // INSTEAD OF PRINTING JUST SEND TO METHODS
      Email newEmail = new Email();
      newEmail.draft(propID, email, subject, body);
      newEmail.sendEmail();

      // success message
      JOptionPane.showMessageDialog(null, "Message has been sent!",
                                    "Successful Message",
                                    JOptionPane.PLAIN_MESSAGE);
      frame.dispose(); // close this window
    }
  }
}
