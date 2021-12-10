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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegisterPropertyForm implements ActionListener {
	JFrame frame = new JFrame();
	private static JButton submitButton;
	private static JLabel addressLabel;
	private static JTextField addressText;
	private static JLabel houseTypeLabel;
	private static JComboBox houseTypeComboBox;
	private static JLabel bathLabel;
	private static JTextField bathText;
	private static JLabel bedLabel;
	private static JTextField bedText;
	private static JLabel furnishLabel;
	private static JComboBox furnishComboBox;
	private static JLabel quadrantLabel;
	private static JComboBox quadrantComboBox;
	private static JLabel priceLabel;
	private static JTextField priceText;

	private static Landlord landlord;

	RegisterPropertyForm(int id) {
		landlord = new Landlord(id);

		addressLabel = new JLabel("Address"); // label that goes beside textbox to tell user what to enter
		addressLabel.setBounds(30, 20, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(addressLabel);

		addressText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		addressText.setBounds(100, 20, 165, 25); // setting bounds (x,y,width,height)
		addressText.addActionListener(this);
		frame.add(addressText);

				houseTypeLabel = new JLabel("House Type"); // label that goes beside textbox to tell user what to enter
		houseTypeLabel.setBounds(30, 60, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(houseTypeLabel);

		String[] houseType = { "Apartment", "Bungalow" , "Condo" , "Duplex", "Townhouse"};
		houseTypeComboBox = new JComboBox(houseType);
		houseTypeComboBox.setBounds(120, 60, 200, 25);
		houseTypeComboBox.addActionListener(this);
		frame.add(houseTypeComboBox);



		bathLabel = new JLabel("Number of Bathroom(s)"); // label that goes beside textbox to tell user what to enter
		bathLabel.setBounds(30, 100, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(bathLabel);

		bathText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		bathText.setBounds(180, 100, 50, 25); // setting bounds (x,y,width,height)
		bathText.addActionListener(this);
		frame.add(bathText);

		bedLabel = new JLabel("Number of Bedroom(s)"); // label that goes beside textbox to tell user what to enter
		bedLabel.setBounds(30, 140, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(bedLabel);

		bedText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		bedText.setBounds(180, 140, 50, 25); // setting bounds (x,y,width,height)
		bedText.addActionListener(this);
		frame.add(bedText);

			furnishLabel = new JLabel("Furnished State"); // label that goes beside textbox to tell user what to enter
		furnishLabel.setBounds(30, 180, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(furnishLabel);

		String[] furnish = { "Furnished", "Unfurnised" };	// string array for options
		furnishComboBox = new JComboBox(furnish);	// create combobox with the furnish options
		furnishComboBox.setBounds(160, 180, 150, 25);	
		furnishComboBox.addActionListener(this);
		frame.add(furnishComboBox);

		quadrantLabel = new JLabel("City Quadrant"); // label that goes beside textbox to tell user what to enter
		quadrantLabel.setBounds(30, 220, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(quadrantLabel);

		String[] quadrant = { "NE", "NW", "SE", "SW" };	// string array for options
		quadrantComboBox = new JComboBox(quadrant);	// create combobox with the furnish options
		quadrantComboBox.setBounds(160, 220, 80, 25);
		quadrantComboBox.addActionListener(this);
		frame.add(quadrantComboBox);

		priceLabel = new JLabel("Price (per month)"); // label that goes beside textbox to tell user what to enter
		priceLabel.setBounds(30, 260, 150, 20); // where the label will go on the panel (x,y,width,height)
		frame.add(priceLabel);

		priceText = new JTextField(20); // creating box that lets user enter chars that takes in length argument
		priceText.setBounds(150, 260, 165, 25); // setting bounds (x,y,width,height)
		priceText.addActionListener(this);
		frame.add(priceText);

		// button for submit
		submitButton = new JButton("Submit");
		submitButton.setBounds(175, 300, 150, 30);
		submitButton.setFocusable(false);
		submitButton.addActionListener(this);
		frame.add(submitButton);

		// frame for RegisterPropertyForm
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // exiting window will close window
		frame.setSize(400, 400); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submitButton) {
			String stringBath = bathText.getText();
			String stringBed = bedText.getText();
			if(!isInteger(stringBath) || !isInteger(stringBed)){
				          						        JOptionPane.showMessageDialog(
            null, "Bed and Bath must be Integers!",
            "Input Error", JOptionPane.ERROR_MESSAGE);

			}else{
				
			String address = addressText.getText();	// get address user input
			String ht = houseTypeComboBox.getSelectedItem().toString();	// get housetype user input
			int numBath = Integer.parseInt(bathText.getText());	// get bath num user input
			int numRoom = Integer.parseInt(bedText.getText());	// get bed num user input
			String furnish = furnishComboBox.getSelectedItem().toString();	// get furnish state user input
			String quadrant = quadrantComboBox.getSelectedItem().toString();	// get quadrant user input
			Double price = Double.parseDouble(priceText.getText());	// get price user input
			
			// create a new property with the user inputs
			Property newProp = new Property(address, ht, numBath, numRoom, furnish, quadrant, price);

				landlord.registerProperty(newProp);	// register property
				int answer = JOptionPane.showOptionDialog(null, "Pay Property Fee for this New Property?", "Pay Property Fee",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);	// ask landlord if they want to pay the fee
				if(answer == 0) {
					// yes answer changes listing to active
					newProp.setSOL("Active");	// make property active
					PropertyList myList = new PropertyList();
					myList.addProperty(newProp);


				}else if(answer == 1){
					// no answer change listing to cancelled
					newProp.setSOL("Cancelled");	// make property cancelled
					System.out.println(answer);
				}
				
				landlord.changeSOL(newProp);
			} 

			}




	}

	   /*https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java*/
  public static boolean isInteger(String str) {
    if (str == null) {
        return false;
    }
    int length = str.length();
    if (length == 0) {
        return false;
    }
    int i = 0;
    if (str.charAt(0) == '-') {
        if (length == 1) {
            return false;
        }
        i = 1;
    }
    for (; i < length; i++) {
        char c = str.charAt(i);
        if (c < '0' || c > '9') {
            return false;
        }
    }
    return true;
}
}
