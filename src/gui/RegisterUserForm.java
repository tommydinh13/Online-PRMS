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
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

		String[] users = { "Manager", "Landlord", "Registered Renter" };	// array that takes in options
		userComboBox = new JComboBox(users);	// combobox that takes in options
		userComboBox.setBounds(160, 190, 165, 25);	// set bounds
		frame.add(userComboBox);	

		// sign up button
		signupButton = new JButton("Sign Up");
		signupButton.setBounds(160, 250, 165, 25);
		signupButton.setFocusable(false);
		signupButton.addActionListener(this);
		frame.add(signupButton);

		// login button
		loginButton = new JButton("Login");
		loginButton.setBounds(160, 280, 165, 50);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		frame.add(loginButton);
		loginButton.setVisible(false);

		//success label
		success = new JLabel("");
		success.setBounds(160, 230, 200, 30);
		frame.add(success);

		//label for email
		userEmail = new JLabel("");
		userEmail.setBounds(160, 170, 200, 30);
		userEmail.setFont(new Font("Verdana", Font.BOLD, 14));
		frame.add(userEmail);

		// frame for register user form
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
		frame.setSize(500, 500); // setting size of window
		frame.setLayout(new BorderLayout()); // no layout
		frame.setTitle("Rental Property Management System");
		frame.add(titlePanel, BorderLayout.NORTH);

		frame.setVisible(true);
	}

	// check to see if passwords match
	public boolean checkPassword(String p1, String p2) {
		// check if passwords match and password is not null
		
		if (p1.length() == 0|| !p1.equals(p2)) {
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
		// first name cannot have space
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) == ' ')
				return false;
		}
		// last name cannot have space
		for (int i = 0; i < last.length(); i++) {
			if (last.charAt(i) == ' ')
				return false;
		}
		// first or last name is not null
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

		if (e.getSource() == signupButton) {

			if (!checkName(fname, lname)) {
				// if name check fails, send error message
				JOptionPane.showMessageDialog(null, "Missing First/Last Name or Incorrect Format!", "Name Error",
						JOptionPane.ERROR_MESSAGE);

			} else if (!checkEmail(email, user)) {
				// if email check fails, send error message
				JOptionPane.showMessageDialog(null, email + " Incorrect Format or Already Exists!", "Email Error",
						JOptionPane.ERROR_MESSAGE);

			}

			else if (!checkPassword(password, confirmPass)) {
				// if password check fails, send error message
				JOptionPane.showMessageDialog(null, " Password is null or Passwords Do Not Match!",
						"Password Error", JOptionPane.ERROR_MESSAGE);
			}
			// if successful
			else {
				
				if(user == "Manager"){
					// create new manager
					Manager myManager = new Manager(fname + " " + lname, email, password);
				}
				else if(user == "Landlord"){
					// create new landlord
					Landlord myLandlord = new Landlord(fname + " " + lname, email, password);
				}
				else if(user == "Registered Renter"){
					// create new Registered Renter
					RegisteredRenter myRenter = new RegisteredRenter(fname + " " + lname, email, password);
				}
				
				signupButton.setVisible(false); // remove signup button
				loginButton.setVisible(true); // add login button that goes to loginForm
				success.setText("Succesfully Registered!"); // set message
			}

		}

		
		if (e.getSource() == loginButton) {
			// send back to login form
			frame.dispose();
			LoginForm login = new LoginForm();
		}

	}
}
