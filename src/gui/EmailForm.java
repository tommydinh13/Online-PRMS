package gui;

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

public class EmailForm {
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

	private static int propID;

	EmailForm(int id) {
		propID = id;

		// takes in property id to get landlord email and send email

		titlePanel = new JPanel();
//		titlePanel.setBackground(Color.green);
		titlePanel.setBounds(0, 0, 700, 50);

		titleLabel = new JLabel("Email Form"); // label that goes beside textbox to tell user what to enter
		titlePanel.add(titleLabel);

		subjectPanel = new JPanel();
//		subjectPanel.setBackground(Color.red);
		subjectPanel.setBounds(0, 50, 700, 100);

		subjectLabel = new JLabel("Subject:"); // label that goes beside textbox to tell user what to enter
		subjectPanel.add(subjectLabel);

		subjectText = new JTextField(50); // creating box that lets user enter chars that takes in length argument
		subjectPanel.add(subjectText);

		bodyPanel = new JPanel();
//		bodyPanel.setBackground(Color.blue);
		bodyPanel.setBounds(0, 150, 700, 400);
		bodyPanel.setBorder(new TitledBorder(new EtchedBorder(), "List of Houses Rented This Period"));

		display = new JTextArea(22, 60);
		display.setEditable(true); // set textArea non-editable
		scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bodyPanel.add(scroll);

		sendButton = new JButton("Send");

		frame.add(titlePanel);
		frame.add(subjectPanel);
		frame.add(bodyPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(720, 700); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		frame.setVisible(true);

	}

}
