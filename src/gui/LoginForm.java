package gui;

import Database.*;
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

  // login form is the first form in this program.
  // this window should let the user either login,
  // create an account, or search for properties

  LoginForm() {

    // button to login
    loginButton = new JButton("Login");
    loginButton.setBounds(150, 80, 110, 30);
    loginButton.setFocusable(false);
    loginButton.addActionListener(this);
    frame.add(loginButton);

    // selecting user role
    // string array that are the options for the combobox
    String[] users = {"Manager", "Landlord", "Registered Renter"};
    // combobox is a drop down list that contains the different types of users
    userComboBox = new JComboBox(users);
    userComboBox.setBounds(275, 50, 105, 25);
    userComboBox.addActionListener(this);
    frame.add(userComboBox);

    // button to register a new user
    registerButton = new JButton("Register");
    registerButton.setBounds(150, 120, 110, 30);
    registerButton.setFocusable(false);
    registerButton.addActionListener(this);
    frame.add(registerButton);

    // button to search for properties
    searchPropButton = new JButton("Search For Properties");
    searchPropButton.setBounds(65, 200, 200, 40);
    searchPropButton.setFocusable(false);
    searchPropButton.addActionListener(this);
    frame.add(searchPropButton);

    // frame for LoginForm
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

    else if (e.getSource() == loginButton) {
      // if user selects loginButton
      // create userController that will be used to check
      // if the values that user input match with db so then
      // login would be successful

      UserController loginCheck = new UserController();
      int check = 0;
      try {
        // method to check if user exists in database with inputs given
        // check will return the user ID
        check = loginCheck.checkUser(email, password, role);
      } catch (SQLException e1) {
        e1.printStackTrace();
      }

      // check == 0 means that user was not in db with inputs given
      if (check == 0) {
        //				// error message saying not registered
        JOptionPane.showMessageDialog(null, "Incorrect Email or Password",
                                      "Login Error", JOptionPane.ERROR_MESSAGE);
      }

      // if check says inputs were valid, and the selected type
      // was manager, create a managerform
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
        RegisteredRenterForm renter = new RegisteredRenterForm(check);
      }
    }
  }
}
