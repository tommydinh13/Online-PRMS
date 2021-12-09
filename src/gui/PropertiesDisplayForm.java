package gui;

import java.util.ArrayList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Database.Property;

public class PropertiesDisplayForm implements ActionListener{
    JFrame frame = new JFrame();
    private static JPanel titlePanel;
    private static JLabel titleLabel;
    private static JPanel bodyPanel;
    
    private static JButton detailButton;
    private static JButton contactButton;
	private static JTextArea display;
	private static JScrollPane scroll;

    private ArrayList<Property> myList;
    PropertiesDisplayForm(ArrayList<Property> p) {
            myList = p;


        bodyPanel = new JPanel();
		bodyPanel.setBorder(new TitledBorder(new EtchedBorder(), "List of Properties"));
		bodyPanel.setBounds(50, 100, 700, 400);


        display = new JTextArea(22, 52);
		display.setEditable(false); // set textArea non-editable
        scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bodyPanel.add(scroll);

        display.append("ID\t\t");
        display.append("Address\t\t");
        display.append("House Type\t\t");
        display.append("Bath\t\t");
        display.append("Bed\t\t");
        display.append("Furnish\t\t");
        display.append("Quadrant\t\t");
        display.append("Price\n\n");

             for (int i = 0; i<myList.size(); i++ ){

			String id = Integer.toString(myList.get(i).getID()) + " \t \t";
			String address = myList.get(i).getAddress() + " \t";
			String ht = myList.get(i).getHouseType() + " \t \t";
            String bath = Integer.toString(myList.get(i).getBathrooms()) + " \t \t";
            String bed = Integer.toString(myList.get(i).getBedrooms()) + " \t \t";
            String furnish = myList.get(i).getFurnishedStatus() + " \t \t";
            String quadrant = myList.get(i).getCityQuadrant() + " \t \t";
            String price = "$"+ Double.toString(myList.get(i).getPrice()) + " \n \n";

            
            display.append(id);
            display.append(address);
            display.append(ht);
            display.append(bath);
            display.append(bed);
            display.append(furnish);
            display.append(quadrant);
            display.append(price);


		}

        detailButton = new JButton("Get More Details");
        detailButton.setBounds(600, 520, 150, 60);
        detailButton.addActionListener(this);

        contactButton = new JButton("Contact Property");
        contactButton.setBounds(400, 520, 150, 60);
        contactButton.addActionListener(this);
        



        // frame.add(titlePanel);
		frame.add(bodyPanel);
        frame.add(detailButton);
        frame.add(contactButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(850, 600); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if((e.getSource() == detailButton) || (e.getSource()== contactButton)){
            String insert = JOptionPane.showInputDialog("Please Enter Property ID:");
			// System.out.println(insert);
			EmailForm myEmail = new EmailForm(insert);
        }
        
        
    }
}
