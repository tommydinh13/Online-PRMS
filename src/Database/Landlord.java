package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.result.SqlDateValueFactory;

public class Landlord {
    private Property property;
    private String name;
    private String password;
    private String email;
    private int idNum;
    private SQLConnection db;

    // Constructors
    public Landlord(String n, String em, String ps) {
        name = n;
        password = ps;
        email = em;
        db = new SQLConnection();

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
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

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE lID ='" 
            + id + "';");
            
            if (results.next()) {
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
        property = pData;
        db = new SQLConnection();

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
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE address ='" + property.getAddress() + "';");
            if (results.next()) {
                property.setID(Integer.parseInt(results.getString("pID")));
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void enterPropertyFee(PropertyFee pfData) {
        db = new SQLConnection();

        db.initializeConnection();
        // Entering in the property fee data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO PropertyFee (property, landlord, period_start, property_end) VALUES (" 
            + property.getID() + ", '" + idNum + "', '" + pfData.getPeriod() + "', '" + pfData.getPeriodStart() + "', '" + pfData.getPeriodEnd() + "');";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Property> searchProperties(String sol){
        ArrayList<Property> properties = new ArrayList<Property>();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE state_of_listing='"+ sol + "' AND landlord=" + idNum + ";");
            
            while (results.next()) {
                Property prop = new Property(results.getString("address"), results.getString("p_type"), Integer.parseInt(results.getString("bathrooms")), Integer.parseInt(results.getString("bedrooms")), results.getString("furnished"), results.getString("city_quadrant"), Double.parseDouble(results.getString("price")));
                prop.setSOL(sol);
                prop.setID(Integer.parseInt(results.getString("pID")));
                prop.setLandlord(this);
                properties.add(prop);
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public void changeSOL(Property prop) {
        property = prop;
        db = new SQLConnection();

        db.initializeConnection();
        // Entering in the state of listing data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "UPDATE Properties SET state_of_listing='" + property.getStateofListing() + "' WHERE pID=" + Integer.toString(property.getID()) + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changeSOL(int propID, String sol) {
        db = new SQLConnection();

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

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Emails WHERE landlord=" + idNum + ";");
            
            while (results.next()) {
                int idNum = Integer.parseInt(results.getString(1));
                Email em = new Email(Integer.parseInt(results.getString("landlord")), Integer.parseInt(results.getString("property")), results.getString("renter_email"), results.getString("subject"), results.getString("body"));
                em.setID(idNum);
                emails.add(em);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }
    public void deleteEmail(int id) {
        db.initializeConnection();
        // Entering in the state of listing data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "DELETE FROM Emails WHERE eID=" + id + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void check(){}
    //  + " AND landlord=" + idNum
}
