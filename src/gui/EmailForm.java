package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Database.Email;

public class EmailForm implements ActionListener {
	JFrame frame = new JFrame();
	private static JPanel titlePanel;
	private static JLabel titleLabel;
	private static JPanel subjectPanel;
	private static JLabel subjectLabel;
	private static JTextField subjectText;
	private static JPanel bodyPanel;
	private static JLabel bodyLabel;
	private static JTextArea display;
	private static JScrollPane scroll;
	private static JButton sendButton;

	private static JPanel eaPanel;
	private static JLabel eaLabel;
	private static JTextField eaText;

	private static int propID;

	EmailForm(int id) {
		propID = id;

		// takes in property id to get landlord email and send email

		titlePanel = new JPanel();
//		titlePanel.setBackground(Color.green);
		titlePanel.setBounds(0, 0, 700, 50);

		titleLabel = new JLabel("Email Form"); // label that goes beside textbox to tell user what to enter
		titlePanel.add(titleLabel);

		// *********** EMAIL ADDRESSS ***************/
		eaPanel = new JPanel();
		eaPanel.setBounds(0, 50, 700, 50);

		eaLabel = new JLabel("From:");
		eaPanel.add(eaLabel);

		eaText = new JTextField(50);
		eaPanel.add(eaText);

		subjectPanel = new JPanel();
//		subjectPanel.setBackground(Color.red);
		subjectPanel.setBounds(0, 100, 700, 50);

		subjectLabel = new JLabel("Subject:"); // label that goes beside textbox to tell user what to enter
		subjectPanel.add(subjectLabel);

		subjectText = new JTextField(50); // creating box that lets user enter chars that takes in length argument
		subjectPanel.add(subjectText);

		bodyPanel = new JPanel();
//		bodyPanel.setBackground(Color.blue);
		bodyPanel.setBounds(0, 175, 700, 400);
		bodyPanel.setBorder(new TitledBorder(new EtchedBorder(), "List of Houses Rented This Period"));

		display = new JTextArea(22, 55);
		display.setEditable(true); // set textArea non-editable
		scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bodyPanel.add(scroll);

		sendButton = new JButton("Send");
		sendButton.setBounds(500, 575, 110, 30);
		sendButton.setFocusable(false);
		sendButton.addActionListener(this);
		frame.add(sendButton);

		frame.add(titlePanel);
		frame.add(eaPanel);
		frame.add(subjectPanel);
		frame.add(bodyPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(720, 700); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == sendButton) {
			String emailAddress = eaText.getText();
			String subject = subjectText.getText();
			String body = display.getText();

			// INSTEAD OF PRINTING JUST SEND TO METHODS
			Email myEmail = new Email(propID);
			myEmail.draft(emailAddress, subject, body);
			myEmail.sendEmail();
		}
	}

}
