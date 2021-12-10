package gui;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Database.Landlord;
import Database.Property;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PayForm implements ActionListener {

    JFrame frame = new JFrame();

	// title panel
	private static JPanel titlePanel;
	private static JLabel titleLabel;

    	// button panel
	private static JPanel buttonPanel;
	private static JLabel idLabel;
	private static JTextField idText;
	private static JButton payButton;

    	// display panel
	private static JPanel displayPanel;
	private static JTextArea display;
	private static JScrollPane scroll;

    ArrayList<Property> cancelledProperty;
    ArrayList<Property> suspendedProperty;
	private int landlordID;

    PayForm(int id){
        landlordID = id;
        Landlord myLandlord;
        myLandlord = new Landlord(id);
        cancelledProperty = myLandlord.searchProperties("Cancelled");
        suspendedProperty = myLandlord.searchProperties("Suspended");
        landlordID = id;

           // ********* TITLE ************
           titlePanel = new JPanel();
           titlePanel.setBounds(0, 0, 800, 50);
   //		titlePanel.setBackground(Color.green);
   
           titleLabel = new JLabel("Pay Property Fee");
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
   
   
           payButton = new JButton("Pay");
           payButton.setBounds(new Rectangle(300, 500));
           payButton.addActionListener(this);
           buttonPanel.add(payButton);
   
           // ******** ACTIVE LISTINGS *******
           displayPanel = new JPanel();
           displayPanel.setBorder(new TitledBorder(new EtchedBorder(), "Unpaid Listings"));
           displayPanel.setBounds(50, 150, 700, 400);
   //		displayPanel.setBackground(Color.blue);
   
           display = new JTextArea(22, 52);
           display.setEditable(false); // set textArea non-editable
           scroll = new JScrollPane(display);
           scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
           displayPanel.add(scroll);
   
           // must display listings here
           display.append("Property ID\t\t");
           display.append("State of Listing\t\t");
           display.append("Address\n\n");
   
           for (int i = 0; i<cancelledProperty.size(); i++){
               String propID = Integer.toString(cancelledProperty.get(i).getID());
               String state = cancelledProperty.get(i).getStateofListing();
               String address = cancelledProperty.get(i).getAddress();
               
   
               display.append(propID + "\t\t");
               display.append(state + "\t\t");
               display.append(address + "\n\n");
   
           }

                for (int i = 0; i<suspendedProperty.size(); i++){
               String propID = Integer.toString(suspendedProperty.get(i).getID());
               String state =  suspendedProperty.get(i).getStateofListing();
               String address = suspendedProperty.get(i).getAddress();
               
   
               display.append(propID + "\t\t");
               display.append(state + "\t\t");
               display.append(address + "\n\n");
   
           }


   
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
      int propID = Integer.parseInt(idText.getText());

      if(e.getSource() == payButton){
          Landlord payLandlord;
          payLandlord = new Landlord (landlordID);
          payLandlord.changeSOL(propID, "Active");

          // display success
      }
  }
}
