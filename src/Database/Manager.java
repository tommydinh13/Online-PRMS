package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Manager {
    private String name;
    private String phone_number;
    private String email;
    private SQLConnection db;
    
    // Constructors
    public Manager(String n, String pn, String em) {
        name = n;
        phone_number = pn;
        email = em;
        db = new SQLConnection();

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Managers (name, phone_number, email) " 
            + "SELECT * FROM (SELECT '" + name + "' AS name, '" + phone_number + "' AS phone_number, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Managers WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
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

    // Method Functions
    
}
