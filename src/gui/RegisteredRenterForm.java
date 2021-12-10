package gui;

import java.awt.Image;
import Database.RegisteredRenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RegisteredRenterForm implements ActionListener {
	JFrame frame = new JFrame();
	private static JButton searchButton;
	private static JButton unsubButton;
	private static JButton logoutButton;
	private static JButton notifButton;

	private static RegisteredRenter renter;
  private static int renterID;

	RegisteredRenterForm(int id) {
		renter = new RegisteredRenter(id);
    	renterID = id;

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
		logoutButton.setBounds(10, 10, 125, 30);
		logoutButton.setFocusable(false);
		logoutButton.addActionListener(this);
		frame.add(logoutButton);

		ImageIcon icon = new ImageIcon("src/gui/Images/bell.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		notifButton = new JButton();
		notifButton.setBounds(300, 10, 50, 50);
		notifButton.setFocusable(false);
		notifButton.setIcon(icon);
		notifButton.addActionListener(this);
		frame.add(notifButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(400, 400); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

   if (e.getSource() == searchButton) {
     SearchCriteriaForm search = new SearchCriteriaForm(renterID);

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
   else if(e.getSource() == notifButton){
		renter = new RegisteredRenter(renterID);
	   if(renter.getNotify() == "YES"){
		   JOptionPane.showConfirmDialog(null, "There are new listing(s) matching your Search Criteria!", "Notification", JOptionPane.INFORMATION_MESSAGE);
		   renter.setNotify();
	   }
	   else{
		   JOptionPane.showConfirmDialog(null, "There are NO new listing(s) matching your Search Criteria", "Notification", JOptionPane.INFORMATION_MESSAGE);
	   }
   }
	}
}
