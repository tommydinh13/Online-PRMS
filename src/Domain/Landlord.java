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

public class Landlord {
    private Property property;
    private String name;
    private String password;
    private String email;
    private int idNum;
    private SQLConnection db;

    // Constructors
    public Landlord(String n, String em, String ps) {
        // Create a Landlord instance based on passed in values.
        name = n;
        password = ps;
        email = em;
        db = new SQLConnection();

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Search through the Landlords database and insert the new landlord if the data does not exist.
            String insertSql = "INSERT INTO Landlords (name, password, email) " 
            + "SELECT * FROM (SELECT '" + name + "' AS name, '" + password + "' AS password, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Landlords WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Select the landlord with the matching data and set their id. 
            ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE name ='" 
            + name + "' AND password ='" + password + "' AND email ='" + email + "';");
            
            if (results.next()) {
                idNum = Integer.parseInt(results.getString(1));
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Landlord(int id) {
        db = new SQLConnection();

        // Creating a new landlord based on the passed in landlord id.
        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Query to find the corresponding landlord through id. 
            ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE lID ='" 
            + id + "';");
            
            if (results.next()) {
                // If a landlord is found then assign all of the data values.
                idNum = id;
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
    public int getID() {
        return idNum;
    }
    public void setID(int id) {
        idNum = id;
    }
    
    // Method Functions
    public void registerProperty(Property pData) {
        // Registering a new property/ 
        property = pData;   // The new property which a landlord wants to register.

        db.initializeConnection();
        // Entering the property data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, landlord) VALUES ('" 
            + property.getAddress() + "', '" + property.getHouseType() + "', " + Integer.toString(property.getBathrooms()) + ", " + Integer.toString(property.getBedrooms()) + ", '" + property.getFurnishedStatus() + "', '" + property.getCityQuadrant() + "', " + Double.toString(property.getPrice()) + ", " + Integer.toString(idNum) + ");";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Finding the property which holds the matching address to the newly inserted property.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE address ='" + property.getAddress() + "';");
            if (results.next()) {
                // If it is found, obtain the id and set it to the classes id.
                property.setID(Integer.parseInt(results.getString("pID")));
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Property> searchProperties(String sol){
        // Search the property database for the desired properties.
        ArrayList<Property> properties = new ArrayList<Property>();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Select only the databses which are currently listed as "Active" and that belong to the landlord user.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE state_of_listing='"+ sol + "' AND landlord=" + idNum + ";");
            
            while (results.next()) {
                // For each property found create a new Property and pass in all of the appropriate values and data. 
                Property prop = new Property(results.getString("address"), results.getString("p_type"), Integer.parseInt(results.getString("bathrooms")), Integer.parseInt(results.getString("bedrooms")), results.getString("furnished"), results.getString("city_quadrant"), Double.parseDouble(results.getString("price")));
                prop.setSOL(sol);
                prop.setID(Integer.parseInt(results.getString("pID")));
                prop.setLandlord(this);
                // Add the newly created property to the properties ArrayList.
                properties.add(prop);
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public void changeSOL(Property prop) {
        // Changing the state of listing for a property based on the specific property passed through. 
        // This is specifically used when a new property is registered.
        property = prop;

        db.initializeConnection();
        // Entering in the state of listing data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            // The insert query to change the current state of listing for that property in the database.
            String insertSql = "UPDATE Properties SET state_of_listing='" + property.getStateofListing() + "' WHERE pID=" + Integer.toString(property.getID()) + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changeSOL(int propID, String sol) {
        db.initializeConnection();
        // Entering in the state of listing data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "UPDATE Properties SET state_of_listing='" + sol + "' WHERE pID=" + Integer.toString(propID) + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Email> viewInbox() {
        ArrayList<Email> emails = new ArrayList<Email>();
        // Produce all of the emails which belong to the landlord.

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Scan through the Emails database and find the emails that belong to the landlord.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Emails WHERE landlord=" + idNum + ";");
            
            while (results.next()) {
                // Create an email based on the raw data found in the database.
                int idNum = Integer.parseInt(results.getString(1));
                Email em = new Email(Integer.parseInt(results.getString("landlord")), Integer.parseInt(results.getString("property")), results.getString("renter_email"), results.getString("subject"), results.getString("body"));
                em.setID(idNum);
                // Add it to the email ArrayList.
                emails.add(em);
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }
    public void deleteEmail(int id) {
        db.initializeConnection();
        // Search thorugh the email database then find the mail which has the mathing email id and delete it. 
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "DELETE FROM Emails WHERE eID=" + id + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
