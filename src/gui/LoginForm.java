package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Database.*;

// https://beginnersbook.com/2015/07/java-swing-tutorial/
public class LoginForm implements ActionListener {

  JFrame frame = new JFrame();
  private static JLabel emailLabel;
  private static JTextField emailText;
  private static JLabel passwordLabel;
  private static JPasswordField passwordText;

  private static JButton loginButton;
  private static JButton searchPropButton;
  private static JButton registerButton;
  private static JComboBox userComboBox;
  private static JComboBox roleComboBox;

  LoginForm() {

    loginButton = new JButton("Login");
    loginButton.setBounds(150, 80, 110, 30);
    loginButton.setFocusable(false);
    loginButton.addActionListener(this);
    frame.add(loginButton);

    // selecting user role
    String[] users = {"Manager", "Landlord", "Registered Renter"};
    userComboBox = new JComboBox(users);
    userComboBox.setBounds(275, 50, 105, 25);
    userComboBox.addActionListener(this);
    frame.add(userComboBox);

    registerButton = new JButton("Register");
    registerButton.setBounds(150, 120, 110, 30);
    registerButton.setFocusable(false);
    registerButton.addActionListener(this);
    frame.add(registerButton);

    searchPropButton = new JButton("Search For Properties");
    searchPropButton.setBounds(65, 200, 200, 40);
    searchPropButton.setFocusable(false);
    searchPropButton.addActionListener(this);
    frame.add(searchPropButton);

    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE); // exiting window will close window
    frame.setSize(400, 400);   // setting size of window
    frame.setLayout(null);     // no layout
    frame.setTitle("Rental Property Management System");

    emailLabel = new JLabel(
        "Email"); // label that goes beside textbox to tell user what to enter
    emailLabel.setBounds(
        30, 20, 100,
        20); // where the label will go on the panel (x,y,width,height)
    frame.add(emailLabel);

    emailText = new JTextField(20); // creating box that lets user enter chars
                                    // that takes in length argument
    emailText.setBounds(100, 20, 165, 25); // setting bounds (x,y,width,height)
    frame.add(emailText);

    passwordLabel = new JLabel("Password"); // label that goes beside Password
                                            // to tell user what to enter
    passwordLabel.setBounds(
        30, 50, 100,
        20); // where the label will go on the panel (x,y,width,height)
    frame.add(passwordLabel);

    passwordText = new JPasswordField(
        20); // creating box that lets user enter chars that are hidden
    passwordText.setBounds(100, 50, 165,
                           25); // setting bounds (x,y,width,height)
    frame.add(passwordText);

    frame.setVisible(true);
  }

  // dont need this funtion but confirm with group
  // check to see if passwords match
  public boolean checkUser(String username, String password, String role) {

    // this functions checks to see if username is in databse, if it is, the
    // password in the
    // database must match the password entered

    return false;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String email = emailText.getText();
    String password = passwordText.getText();
    String role = userComboBox.getSelectedItem()
                      .toString(); // get the value of the combo box
    int id = 0;
    if (e.getSource() == searchPropButton) {
      frame.dispose();
      SearchCriteriaForm mySearchCriteria = new SearchCriteriaForm();

    }

    else if (e.getSource() == registerButton) {
      frame.dispose();
      RegisterUserForm registerUser = new RegisterUserForm();
    }

    // THIS IS ONLY FOR TOMMY TESTING. COMMENT OUT WHEN WORKING WITH GROUP
    //		else if (e.getSource() == loginButton) {
    //			frame.dispose();
    //			LandlordForm test = new LandlordForm(5);
    //
    //		}

    else if (e.getSource() == loginButton) {
      //			// need another if statement that checks the
      //login and password after login
      //			// button
      //			// to ensure user is in database and go to
      //respected page
      //

      UserController loginCheck = new UserController();
      int check = 0;
      try {
        check = loginCheck.checkUser(email, password, role);
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      //
      if (check == 0) {
        //				// error message saying not registered
        JOptionPane.showMessageDialog(null, "Incorrect Email or Password",
                                      "Login Error", JOptionPane.ERROR_MESSAGE);
      }
      //
      //			//role == landlord
      //			// if landlord exist
      if (check > 0 && role.equals("Manager")) {
        frame.dispose();
        ManagerForm manager = new ManagerForm();
      }
      //
      // if account exists in database & role is landlord
      // goto landlord form
      else if (check > 0 && role.equals("Landlord")) {
        frame.dispose();
        LandlordForm landlord = new LandlordForm(check);

      }
      // if account exists in database & role is renter
      // goto renter form
      else if (check > 0 && role.equals("Registered Renter")) {
        frame.dispose();
        try {
          RegisteredRenterForm renter = new RegisteredRenterForm(check);
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
      //			else{
      //				// return error saying wrong User Type
      //		}
    }
  }
}
