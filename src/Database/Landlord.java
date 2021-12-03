package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Landlord {
    private Property property;
    private String name;
    private String password;
    private String email;
    private int idNum;
    private SQLConnection db;

    // Constructors
    public Landlord(Property p, String n, String pn, String em) throws SQLException {
        property = p;
        name = n;
        password = pn;
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

        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE name ='" 
        + name + "' AND password ='" + password + "' AND email ='" + email + ";");
        
        if (results.next()) {
            idNum = Integer.parseInt(results.getString(1));
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
    public void registerProperty(PropertyFee pfData) throws SQLException {
        db = new SQLConnection();

        db.initializeConnection();

        // Entering the property data into the database
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord) VALUES (" 
            + property.getAddress() + ", '" + property.getHouseType() + "', '" + Integer.toString(property.getBathrooms()) + "', '" + Integer.toString(property.getBedrooms()) + "', '" + property.getFurnishedStatus() + "', '" + property.getCityQuadrant() + "', '" + Double.toString(property.getPrice()) + "', '" + property.getStateofListing() + "', '" + Integer.toString(idNum) + ");";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
