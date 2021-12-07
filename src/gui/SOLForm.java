package gui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SOLForm implements ActionListener {
	JFrame frame = new JFrame();

	// title panel
	private static JPanel titlePanel;
	private static JLabel titleLabel;

	// button panel
	private static JPanel buttonPanel;
	private static JLabel idLabel;
	private static JTextField idText;
	private static JComboBox stateComboBox;
	private String[] states = { "Rented", "Cancelled", "Suspended" };
	private static JButton applyButton;

	// display panel
	private static JPanel displayPanel;
	private static JTextArea display;
	private static JScrollPane scroll;

	SOLForm() {
		// ********* TITLE ************
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 800, 50);
//		titlePanel.setBackground(Color.green);

		titleLabel = new JLabel("Change State of Lisitng");
		titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
		titlePanel.add(titleLabel);

		// ********* BUTTON PANEL ************
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 50, 800, 75);
//		buttonPanel.setBackground(Color.red);

		idLabel = new JLabel("Property ID: ");
		idLabel.setFont(new Font("Courier", Font.PLAIN, 17));
		buttonPanel.add(idLabel);

		idText = new JTextField(10);
		buttonPanel.add(idText);
		buttonPanel.add(Box.createHorizontalStrut(60));

		stateComboBox = new JComboBox(states);
		buttonPanel.add(stateComboBox);

		applyButton = new JButton("Apply");
		applyButton.setBounds(new Rectangle(300, 500));
		applyButton.addActionListener(this);
		buttonPanel.add(applyButton);

		// ******** ACTIVE LISTINGS *******
		displayPanel = new JPanel();
		displayPanel.setBorder(new TitledBorder(new EtchedBorder(), "Active Listings"));
		displayPanel.setBounds(50, 150, 700, 400);
//		displayPanel.setBackground(Color.blue);

		display = new JTextArea(22, 52);
		display.setEditable(false); // set textArea non-editable
		scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		displayPanel.add(scroll);

		// must display listings here

		// ******** FRAME ********
		frame.add(titlePanel);
		frame.add(buttonPanel);
		frame.add(displayPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(800, 600); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String propID = idText.getText();
		String myState = stateComboBox.getSelectedItem().toString();

		if (e.getSource() == applyButton) {
			// have to call a check to make sure that id entered is within the list
			// if true, change the state

			System.out.println(propID);
			System.out.println(myState);

		}

	}

}
