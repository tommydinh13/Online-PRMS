package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Database.Landlord;
import Database.Manager;
import Database.RegisteredRenter;

public class RegisterUserForm implements ActionListener {
	JFrame frame = new JFrame();
	private static JLabel firstNameLabel;
	private static JTextField firstNameText;
	private static JLabel lastNameLabel;
	private static JTextField lastNameText;
	private static JLabel emailLabel;
	private static JTextField emailText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JLabel confirmPassLabel;
	private static JPasswordField confirmPassText;
	private static JComboBox userComboBox;
	private static JPanel titlePanel;
	private static JLabel titleLabel;
	private static JButton signupButton;
	private static JButton loginButton;
	private static JLabel success;
	private static JLabel userEmail;

	RegisterUserForm() {

		titlePanel = new JPanel();
		titleLabel = new JLabel("User Registration Form");
		titlePanel.add(titleLabel);

		firstNameLabel = new JLabel("First Name"); // label that goes beside Password to tell user what to enter
		firstNameLabel.setBounds(50, 40, 100, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(firstNameLabel);

		firstNameText = new JTextField(20); // creating box that lets user enter chars that are hidden
		firstNameText.setBounds(160, 40, 165, 25); // setting bounds (x,y,width,height)
		frame.add(firstNameText);

		lastNameLabel = new JLabel("Last Name"); // label that goes beside Password to tell user what to enter
		lastNameLabel.setBounds(50, 70, 100, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(lastNameLabel);

		lastNameText = new JTextField(20); // creating box that lets user enter chars that are hidden
		lastNameText.setBounds(160, 70, 165, 25); // setting bounds (x,y,width,height)
		frame.add(lastNameText);

		emailLabel = new JLabel("Enter Email"); // label that goes beside textbox to tell user what to enter
		emailLabel.setBounds(50, 100, 100, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(emailLabel);

		emailText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		emailText.setBounds(160, 100, 165, 25); // setting bounds (x,y,width,height)
		frame.add(emailText);

		passwordLabel = new JLabel("Enter Password"); // label that goes beside Password to tell user what to enter
		passwordLabel.setBounds(50, 130, 100, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(passwordLabel);

		passwordText = new JPasswordField(20); // creating box that lets user enter chars that are hidden
		passwordText.setBounds(160, 130, 165, 25); // setting bounds (x,y,width,height)
		frame.add(passwordText);

		confirmPassLabel = new JLabel("Confirm Password"); // label that goes beside Password to tell user what to enter
		confirmPassLabel.setBounds(50, 160, 130, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(confirmPassLabel);

		confirmPassText = new JPasswordField(20); // creating box that lets user enter chars that are hidden
		confirmPassText.setBounds(160, 160, 165, 25); // setting bounds (x,y,width,height)
		frame.add(confirmPassText);

		String[] users = { "Manager", "Landlord", "Registered Renter" };
		userComboBox = new JComboBox(users);
		userComboBox.setBounds(160, 190, 165, 25);
		frame.add(userComboBox);

		signupButton = new JButton("Sign Up");
		signupButton.setBounds(160, 250, 165, 25);
		signupButton.setFocusable(false);
		signupButton.addActionListener(this);
		frame.add(signupButton);

		loginButton = new JButton("Login");
		loginButton.setBounds(160, 280, 165, 50);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		frame.add(loginButton);
		loginButton.setVisible(false);

		success = new JLabel("");
		success.setBounds(160, 230, 200, 30);
		frame.add(success);

		userEmail = new JLabel("");
		userEmail.setBounds(160, 170, 200, 30);
		userEmail.setFont(new Font("Verdana", Font.BOLD, 14));
		frame.add(userEmail);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(500, 500); // setting size of window
		frame.setLayout(new BorderLayout()); // no layout
		frame.setTitle("Rental Property Management System");
		frame.add(titlePanel, BorderLayout.NORTH);

		frame.setVisible(true);
	}

	// check to see if passwords match
	public boolean checkPassword(String p1, String p2) {
		// check if passwords match and password is not null

		for (int i = 0; i < p1.length(); i++) {
			if (p1.charAt(i) == ' ') {
				return false;
			}
		}
		if (p1 == null || !p1.equals(p2)) {
			return false;
		}
		return true;
	}

	public boolean checkEmail(String email, String role) {
		// checks if specific email matches specific role in database
		// just checking to see if format is right, still need to check db
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == ' ')
				return false;
		}
		if (email.isEmpty()) {
			return false;
		}

		return true;
	}

	// making sure name is not null
	public boolean checkName(String first, String last) {

		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) == ' ')
				return false;
		}
		for (int i = 0; i < last.length(); i++) {
			if (last.charAt(i) == ' ')
				return false;
		}
		if ((first.isEmpty()) || (last.isEmpty())) {
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fname = firstNameText.getText();
		String lname = lastNameText.getText();
		String email = emailText.getText();
		String password = passwordText.getText();
		String confirmPass = confirmPassText.getText();
		String user = userComboBox.getSelectedItem().toString();
		System.out.println(fname);
		System.out.println(lname);

		if (e.getSource() == signupButton) {
			// should check to see if emailText is already registered
			// call checkPassword() if not registered in database already
			// if checkPasssword returns true, should enter info into database
			// use 'userComboBox.getSelectedItem()' to get value of comboBox selected
			// use 'passwordText.getText()' to get value in textbox

			// if user is good to signup
			// emailText is not in database + passwords match
			// THIS IF STATEMENT IS NOT THE REAL ONE JUST USING FOR NOW
			if (!checkName(fname, lname)) {
				JOptionPane.showMessageDialog(null, "Missing First/Last Name or Incorrect Format!", "Name Error",
						JOptionPane.ERROR_MESSAGE);

			} else if (!checkEmail(email, user)) {

				JOptionPane.showMessageDialog(null, email + " Incorrect Format or Already Exists!", "Email Error",
						JOptionPane.ERROR_MESSAGE);
//				success.setText("Account with email: ");
//				userEmail.setText(email);
//				alreadyExist.setVisible(true);
			}

			else if (!checkPassword(password, confirmPass)) {
				JOptionPane.showMessageDialog(null, " Password contains Whitespace or Passwords Do Not Match!",
						"Password Error", JOptionPane.ERROR_MESSAGE);
			}
			// if successful
			else {
				
				if(user == "Manager"){
					Manager myManager = new Manager(fname + " " + lname, email, password);
				}
				else if(user == "Landlord"){
					Landlord myLandlord = new Landlord(fname + " " + lname, email, password);
				}
				else if(user == "Registered Renter"){
					RegisteredRenter myRenter = new RegisteredRenter(fname + " " + lname, email, password);
				}

				signupButton.setVisible(false); // remove signup button
				loginButton.setVisible(true); // add login button that goes to loginForm
				success.setText("Succesfully Registered!"); // set message
			}

		}

		// send back to login form
		if (e.getSource() == loginButton) {
			frame.dispose();
			LoginForm login = new LoginForm();
		}

	}
}
