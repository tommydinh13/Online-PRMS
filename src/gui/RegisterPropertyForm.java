package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterPropertyForm implements ActionListener {
	JFrame frame = new JFrame();
	private static JButton submitButton;
	private static JLabel addressLabel;
	private static JTextField addressText;
	private static JLabel bathLabel;
	private static JTextField bathText;
	private static JLabel bedLabel;
	private static JTextField bedText;
	private static JLabel furnishLabel;
	private static JComboBox furnishComboBox;
	private static JLabel quadrantLabel;
	private static JComboBox quadrantComboBox;

	private static int idLL;

	RegisterPropertyForm(int id) {
		idLL = id;

		addressLabel = new JLabel("Address"); // label that goes beside textbox to tell user what to enter
		addressLabel.setBounds(30, 20, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(addressLabel);

		addressText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		addressText.setBounds(100, 20, 165, 25); // setting bounds (x,y,width,height)
		addressText.addActionListener(this);
		frame.add(addressText);

		bathLabel = new JLabel("Number of Bathroom(s)"); // label that goes beside textbox to tell user what to enter
		bathLabel.setBounds(30, 60, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(bathLabel);

		bathText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		bathText.setBounds(180, 60, 50, 25); // setting bounds (x,y,width,height)
		bathText.addActionListener(this);
		frame.add(bathText);

		bedLabel = new JLabel("Number of Bedroom(s)"); // label that goes beside textbox to tell user what to enter
		bedLabel.setBounds(30, 100, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(bedLabel);

		bedText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		bedText.setBounds(180, 100, 50, 25); // setting bounds (x,y,width,height)
		bedText.addActionListener(this);
		frame.add(bedText);

		furnishLabel = new JLabel("Furnished State"); // label that goes beside textbox to tell user what to enter
		furnishLabel.setBounds(30, 140, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(furnishLabel);

		String[] furnish = { "Furnished", "Unfurnised" };
		furnishComboBox = new JComboBox(furnish);
		furnishComboBox.setBounds(160, 140, 100, 25);
		furnishComboBox.addActionListener(this);
		frame.add(furnishComboBox);

		quadrantLabel = new JLabel("City Quadrant"); // label that goes beside textbox to tell user what to enter
		quadrantLabel.setBounds(30, 180, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(quadrantLabel);

		String[] quadrant = { "NE", "NW", "SE", "SW" };
		quadrantComboBox = new JComboBox(quadrant);
		quadrantComboBox.setBounds(160, 180, 60, 25);
		quadrantComboBox.addActionListener(this);
		frame.add(quadrantComboBox);

		submitButton = new JButton("Submit");
		submitButton.setBounds(175, 300, 150, 30);
		submitButton.setFocusable(false);
		submitButton.addActionListener(this);
		frame.add(submitButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(400, 400); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitButton) {
			String address = addressText.getText();
			String numBath = bathText.getText();
			String numRoom = bedText.getText();
			String furnish = furnishComboBox.getSelectedItem().toString();
			String quadrant = quadrantComboBox.getSelectedItem().toString();

//			Property newProp = new Property(idLL, address, numBath, numRoom, furnish, quadrant);
//			int check = newProp.check();

//			if (check) {
//				newProp.add();
//				int answer = JOptionPane.showOptionDialog(null, "Pay Property Fee for this New Property?", "Pay Property Fee",
//						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
//				if(answer == 0) {
//					// change listing to active
//				}
//			} else {
//				// label should show saying incorrect format
//			}

		}

	}
}
