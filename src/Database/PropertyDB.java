package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropertyDB {
    private String database;
    private SQLConnection db;

    // Constructors
    public PropertyDB(String myDB) {
        database = myDB;
        db = new SQLConnection();
    }
    public PropertyDB() {
        db = new SQLConnection();
    }

    // Methods
    public void add(String address, String houseType, String numBathrooms, String numBedrooms, String furnishedStatus, String cityQuadrant, double price, String stateofListing, String landlordName, String landlordPhone, String landlordEmail) throws SQLException {
        db.initializeConnection();
        int landlord = 0;

        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Landlords (name, phone_number, email) VALUES (" 
            + landlordName + ", '" + landlordPhone + "', '" + landlordEmail + ");";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE name ='" 
        + landlordName + "' AND phone_number ='" + landlordPhone + "' AND email ='" + landlordEmail + ";");
        
        if (results.next()) {
            landlord = Integer.parseInt(results.getString(1));
        }

        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Properties (address, p_type, bathrooms, bedrooms, furnished, city_quadrant, price, state_of_listing, landlord) VALUES (" 
            + address + ", '" + houseType + "', '" + numBathrooms + "', '" + numBedrooms + "', '" + furnishedStatus + "', '" + cityQuadrant + "', '" + Double.toString(price) + "', '" + stateofListing + "', '" + Integer.toString(landlord) + ");";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
