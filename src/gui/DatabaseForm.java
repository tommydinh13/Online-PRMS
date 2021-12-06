package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Database.Landlord;
import Database.Manager;
import Database.Property;
import Database.RegisteredRenter;

public class DatabaseForm {
    JFrame frame = new JFrame();
    private static JPanel titlePanel;
    private static JLabel titleLabel;
    private static JPanel bodyPanel;
    

	private static JTextArea display;
	private static JScrollPane scroll;

    private String titleRole;
    private String listRole;

    
    DatabaseForm(int num) throws SQLException{
        
        Manager myManager = new Manager();

        if(num == 2){
            titleRole = "Property";
            listRole = "Properties";
        }else if (num == 1){
            titleRole = "Renter";
            listRole = "Renters";
        }else{
            titleRole = "Landlord";
            listRole = "Landlords";
        }

        titlePanel = new JPanel();
		// titlePanel.setBackground(Color.red);
		titlePanel.setBounds(0, 0, 300, 100);

        titleLabel = new JLabel(titleRole +" Database");
		titlePanel.add(titleLabel);

        

        bodyPanel = new JPanel();
		bodyPanel.setBorder(new TitledBorder(new EtchedBorder(), "List of " + listRole));
		bodyPanel.setBounds(50, 100, 700, 400);
        
        display = new JTextArea(22, 52);
		display.setEditable(false); // set textArea non-editable
        scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bodyPanel.add(scroll);


        if(num == 2){
            display.append("ID\t\t");
            display.append("Address\t\t");
            display.append("House Type\t\t");
            display.append("Bath\t\t");
            display.append("Bed\t\t");
            display.append("Furnish\t\t");
            display.append("Quadrant\t\t");
            display.append("Price\t\t");
            display.append("State of Listing\t\t");
            display.append("Landlord \n\n");


            ArrayList<Property> myProperties = myManager.searchProperties();
            for (int i = 0; i<myProperties.size(); i++ ){
			String id = Integer.toString(myProperties.get(i).getID()) + " \t \t";
			String address = myProperties.get(i).getAddress() + " \t \t";
			String ht = myProperties.get(i).getHouseType() + " \t \t";
            String bath = Integer.toString(myProperties.get(i).getBathrooms()) + " \t \t";
            String bed = Integer.toString(myProperties.get(i).getBedrooms()) + " \t \t";
            String furnish = myProperties.get(i).getFurnishedStatus() + " \t \t";
            String quadrant = myProperties.get(i).getCityQuadrant() + " \t \t";
            String price = Double.toString(myProperties.get(i).getPrice()) + " \t \t";
            String sol = myProperties.get(i).getStateofListing() + " \t \t";
            String landlord = myProperties.get(i).getLandlord().getName() + " \n\n";
            
            display.append(id);
            display.append(address);
            display.append(ht);
            display.append(bath);
            display.append(bed);
            display.append(furnish);
            display.append(quadrant);
            display.append(price);
            display.append(sol);
            display.append(landlord);

		}

        }else if (num == 1){
            ArrayList<RegisteredRenter> myRenters = myManager.searchRenters();
            display.append("ID\t\t");
            display.append("Name\t\t");
            display.append("Email\t\t");
            display.append("Password\n\n");

                        for (int i = 0; i<myRenters.size(); i++ ){
			String id = Integer.toString(myRenters.get(i).getID()) + " \t \t";
			String name = myRenters.get(i).getName() + " \t \t";
			String email = myRenters.get(i).getEmail() + " \t \t";
            String password = myRenters.get(i).getPassword() + " \n\n";

            display.append(id);
            display.append(name);
            display.append(email);
            display.append(password);

            }
            

        }else{
            
            ArrayList<Landlord> myLandlords = myManager.searchLandlords();

            display.append("ID\t\t");
            display.append("Name\t\t");
            display.append("Email\t\t");
            display.append("Password\n\n");

            for (int i = 0; i<myLandlords.size(); i++ ){
			String id = Integer.toString(myLandlords.get(i).getID());
			String name = myLandlords.get(i).getName() ;
			String email = myLandlords.get(i).getEmail()  ;
            String password = myLandlords.get(i).getPassword() + " \n\n";
            

            display.append(checkLength(id));
            display.append(checkLength(name));
            display.append(checkLength(email));
            display.append(password);

            

            }
        }


        frame.add(titlePanel);
		frame.add(bodyPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exiting window will close window
		frame.setSize(800, 600); // setting size of window
		frame.setLayout(null); // no layout
		frame.setTitle("Rental Property Management System");

		frame.setVisible(true);

    }


    public String checkLength(String s){
        if(s.length()<14){
            return s + "\t\t";
        }else if (s.length() == 14){
            return s+ "\t\t";
        }

        return s + "\t";
    }
    
}
