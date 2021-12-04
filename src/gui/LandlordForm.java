package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LandlordForm implements ActionListener {
	JFrame frame = new JFrame();
	private static JButton sofButton;
	private static JButton payFeeButton;
	private static JButton regisButton;
	private static JButton searchButton;

	private static int idLL;

	LandlordForm(int id) {

		sofButton = new JButton("Change State of Listing");
		sofButton.setBounds(80, 40, 200, 40);
		sofButton.setFocusable(false);
		sofButton.addActionListener(this);
		frame.add(sofButton);

		regisButton = new JButton("Register New Property");
		regisButton.setBounds(80, 120, 200, 40);
		regisButton.setFocusable(false);
		regisButton.addActionListener(this);
		frame.add(regisButton);

		payFeeButton = new JButton("Pay Fee for Property");
		payFeeButton.setBounds(80, 200, 200, 40);
		payFeeButton.setFocusable(false);
		payFeeButton.addActionListener(this);
		frame.add(payFeeButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(400, 400); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == sofButton) {

			// should pull up all active properties landlord specific
			// select one and change the listing
			// method should have 2 constructors, one with no args and one that takes in id
		}

		else if (e.getSource() == regisButton) {
			RegisterPropertyForm newProperty = new RegisterPropertyForm(idLL);
		}

		else if (e.getSource() == payFeeButton) {

			// have to pull up landlord's properties that are not active
			// should just be one button after that says pay that updates database saying
			// property is active
		}

	}
}
