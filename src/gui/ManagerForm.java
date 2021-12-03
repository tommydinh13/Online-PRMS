package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ManagerForm {

	JFrame frame = new JFrame();
	private static JLabel emailLabel;
	private static JTextField emailText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;

	private static JButton loginButton;
	private static JButton searchPropButton;
	private static JButton registerButton;

	private static JComboBox roleComboBox;

	ManagerForm() {
		loginButton = new JButton("Login");
		loginButton.setBounds(150, 80, 110, 30);
		loginButton.setFocusable(false);

		frame.add(loginButton);

		registerButton = new JButton("Register");
		registerButton.setBounds(150, 120, 110, 30);
		registerButton.setFocusable(false);

		frame.add(registerButton);

		searchPropButton = new JButton("Search For Properties");
		searchPropButton.setBounds(65, 200, 200, 40);
		searchPropButton.setFocusable(false);

		frame.add(searchPropButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(400, 400); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		emailLabel = new JLabel("Email"); // label that goes beside textbox to tell user what to enter
		emailLabel.setBounds(30, 20, 100, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(emailLabel);

		emailText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		emailText.setBounds(100, 20, 165, 25); // setting bounds (x,y,width,height)
		frame.add(emailText);

		passwordLabel = new JLabel("Password"); // label that goes beside Password to tell user what to enter
		passwordLabel.setBounds(30, 50, 100, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(passwordLabel);

		passwordText = new JPasswordField(20); // creating box that lets user enter chars that are hidden
		passwordText.setBounds(100, 50, 165, 25); // setting bounds (x,y,width,height)
		frame.add(passwordText);

		frame.setVisible(true);
	}

}
