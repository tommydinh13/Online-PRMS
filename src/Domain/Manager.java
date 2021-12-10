/**
 *@author Kundai Dziwa <a href="mailto:kundai.dziwa@ucalgary.ca">
 *         kundai.dziwa@ucalgary.ca</a>
 *
 *@author Tommy Dinh <a href="mailto:tommy.dinh@ucalgary.ca">
 *         tommy.dinh@ucalgary.ca</a>
 * 
 *@author Tien Dat Johny Do <ahref ="tiendat.do@ucalgary.ca">
 *        tiendat.do@ucalgary.ca</a>
 * 
 *@author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 * 
 * @version 1.1
 * @since 1.0
 */

package Domain;

import Database.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager {
    private String name;
    private String password;
    private String email;
    
    private SQLConnection db;
    
    // Constructors
    public Manager() {
        db = new SQLConnection();
    }
    public Manager(String n, String em, String p) {
        // Creating a manager based on the passed in data values.
        name = n;
        password = p;
        email = em;
        db = new SQLConnection();   // Connection to the database.

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Insertion query that first checks to see if the manager exists.
            // If not then insert into the database.
            String insertSql = "INSERT INTO Managers (name, password, email) " 
            + "SELECT * FROM (SELECT '" + name + "' AS name, '" + password + "' AS password, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Managers WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Manager(int id) {
        // Create a Manager instance with the passed through manager id.
        db = new SQLConnection();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Selection query to find if the manager is in the database.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Managers WHERE mID ='" 
            + id + "';");
            
            if (results.next()) {
                // If the manager is found, populate all the class data values with that in the database.
                name = results.getString("name");
                email = results.getString("email");
                password = results.getString("password");
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    // Method Functions
    public ArrayList<Landlord> searchLandlords(){
        ArrayList<Landlord> landlords = new ArrayList<Landlord>();
        // Search through the entire landlords database and return all landlords.

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Query to retrieve all landlords.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords;");
            
            while (results.next()) {
                // Create a landlord instance for each landlord.
                int idNum = Integer.parseInt(results.getString(1));
                Landlord l = new Landlord(results.getString("name"), results.getString("email"), results.getString("password"));
                l.setID(idNum);
                // Add the new landlord to the ArrayList.
                landlords.add(l);
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return landlords; // return all the landlords in the database.
    }
    public ArrayList<RegisteredRenter> searchRenters(){
        ArrayList<RegisteredRenter> renters = new ArrayList<RegisteredRenter>();
        // Search through the entire renters database and return all renters. 

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Query to retrieve all renters.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Renters;");
            
            while (results.next()) {
                // Create a renter instance for each renter.
                int idNum = Integer.parseInt(results.getString(1));
                RegisteredRenter rr = new RegisteredRenter(results.getString("name"), results.getString("email"), results.getString("password"));
                rr.setID(idNum);
                // Add the new renter to the ArrayList.
                renters.add(rr);
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return renters; // return all the landlords in the database.
    }
    public ArrayList<Property> searchProperties(String sol) {
        ArrayList<Property> properties = new ArrayList<Property>();
        // Search through the properties database and return all properties with the certian sol.

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Query to retrieve all properties with specific state of listing.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE state_of_listing='"+sol+"';");
            
            while (results.next()) {
                // Create a property instance for each property.
                Property prop = new Property(results.getString("address"), results.getString("p_type"), Integer.parseInt(results.getString("bathrooms")), Integer.parseInt(results.getString("bedrooms")), results.getString("furnished"), results.getString("city_quadrant"), Double.parseDouble(results.getString("price")));
                prop.setSOL(sol);
                prop.setID(Integer.parseInt(results.getString("pID")));
                Landlord l = new Landlord(Integer.parseInt(results.getString("landlord")));
                prop.setLandlord(l);
                // Add the new property to the ArrayList.
                properties.add(prop);
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties; // return all the landlords in the database.
    }
    public ArrayList<Property> searchProperties(){
        ArrayList<Property> properties = new ArrayList<Property>();
        // Search through the entire properties database and return all properties. 

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Query to retrieve all properties.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties;");
            
            while (results.next()) {
                // Create a property instance for each property.
                Property prop = new Property(results.getString("address"), results.getString("p_type"), Integer.parseInt(results.getString("bathrooms")), Integer.parseInt(results.getString("bedrooms")), results.getString("furnished"), results.getString("city_quadrant"), Double.parseDouble(results.getString("price")));
                prop.setSOL(results.getString("state_of_listing"));
                prop.setID(Integer.parseInt(results.getString("pID")));
                Landlord l = new Landlord(Integer.parseInt(results.getString("landlord")));
                prop.setLandlord(l);
                // Add the new property to the ArrayList.
                properties.add(prop);
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties; // return all the landlords in the database.
    }
    public int totalProperties(String sol){
        int count = 0;
        // Search through the entire properties database and count the nummber of matches acccording to sol.

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Select all properties with a specific state of listing.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE state_of_listing='"+sol+"';");
            while (results.next()) {
                count++; // Number of properties found which have the wanted sol.
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count; // return the number of properties found. 
    }
    public int totalProperties(){
        int count = 0;
        // Search through the entire properties database and count the nummber of matches.

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Select all properties with a specific state of listing.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties;");
            while (results.next()) {
                count++; // Number of properties in the databse.
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count; // return the number of properties found in the database.
    }
    public void changeSOL(int propID, String sol) {
        // Change the state of listing for a specific property.

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Find the property which corresponds to the property id passed and sol passed.
            String insertSql = "UPDATE Properties SET state_of_listing='" + sol + "' WHERE pID=" + Integer.toString(propID) + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changePFees(int propID, double fee) {
        // Chamge the property fee set for all landlords to pay.

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Update the property fee database based to the passed through values.
            String insertSql = "UPDATE PropertyFee SET fee=" + Double.toString(fee) + " WHERE pID=" + Integer.toString(propID) + ";";
            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void enterPropertyFee(int period, Double fee) {
        db.initializeConnection();
        // Entering in the property fee data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            // Insert the property fee database based to the passed through values.
            String insertSql = "INSERT INTO PropertyFee (period, fee) VALUES (" 
            + Integer.toString(period) + ", " + Double.toString(fee) + ");";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
