package gui;

import Database.RegisteredRenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RegisteredRenterForm implements ActionListener {
  JFrame frame = new JFrame();
  private static JButton searchButton;
  private static JButton unsubButton;
  private static JButton logoutButton;

  private static RegisteredRenter renter;

  RegisteredRenterForm(int id) throws SQLException {
    renter = new RegisteredRenter(id);

    searchButton = new JButton("Search Properties");
    searchButton.setBounds(80, 150, 200, 40);
    searchButton.setFocusable(false);
    searchButton.addActionListener(this);
    frame.add(searchButton);

    unsubButton = new JButton("Unsubscribe");
    unsubButton.setBounds(250, 315, 125, 20);
    unsubButton.setFocusable(false);
    unsubButton.addActionListener(this);
    frame.add(unsubButton);

    logoutButton = new JButton("Logout");
    logoutButton.setBounds(250, 10, 125, 30);
    logoutButton.setFocusable(false);
    logoutButton.addActionListener(this);
    frame.add(logoutButton);

    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE); // exiting window will close window
    frame.setSize(400, 400);   // setting size of window
    frame.setLayout(null);     // no layout
    frame.setTitle("Rental Property Management System");
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == searchButton) {
      SearchCriteriaForm search = new SearchCriteriaForm();

    }

    else if (e.getSource() == unsubButton) {
      renter.unsubscribe();
      frame.dispose();
      JOptionPane.showMessageDialog(
          null, "You have been successfully Unsubscribed", "Unsubscribe",
          JOptionPane.INFORMATION_MESSAGE);
    } else if (e.getSource() == logoutButton) {
      frame.dispose();
      LoginForm login = new LoginForm();
    }
  }
}
