package Database;

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
    public Landlord(String n, String em, String ps) throws SQLException {
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
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE name ='" 
        + name + "' AND password ='" + password + "' AND email ='" + email + "';");
        
        if (results.next()) {
            idNum = Integer.parseInt(results.getString(1));
        }
    }
    public Landlord(int id) throws SQLException {
        db = new SQLConnection();

        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE lID ='" 
        + id + "';");
        
        if (results.next()) {
            idNum = id;
            name = results.getString("name");
            email = results.getString("email");
            password = results.getString("password");
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
    public void registerProperty(Property pData) throws SQLException {
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
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE address ='" + property.getAddress() + "';");
        if (results.next()) {
            property.setID(Integer.parseInt(results.getString("pID")));
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
    public void check(){}
}
