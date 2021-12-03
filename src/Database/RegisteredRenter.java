package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisteredRenter {
    private String name;
    private String phone_number;
    private String email;
    private int idNum;
    private SQLConnection db;
    
    // Constructors
    public RegisteredRenter(String n, String pn, String em) throws SQLException {
        name = n;
        phone_number = pn;
        email = em;
        db = new SQLConnection();

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Renters(name, phone_number, email) " 
            + "SELECT * FROM ( SELECT '" + name + "' AS name, '" + phone_number + "' AS phone_number, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Renters WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE name ='" 
        + name + "' AND phone_number ='" + phone_number + "' AND email ='" + email + "';");
        
        if (results.next()) {
            idNum = Integer.parseInt(results.getString(1));
        }
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phone_number;
    }
    public String getEmail() {
        return email;
    }
    public int getID() {
        return idNum;
    }

    // Method Functions
    public void unsubscribe() {
        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "DELETE FROM Renters WHERE " 
            + "name='" + name + "' AND phone_number='" + phone_number + "' AND email='" + email + "';";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
