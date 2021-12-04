package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisteredRenter {
    private String name;
    private String password;
    private String email;
    private int idNum;
    private SQLConnection db;
    
    // Constructors
    public RegisteredRenter(String n, String p, String em) throws SQLException {
        name = n;
        password = p;
        email = em;
        db = new SQLConnection();

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Renters(name, password, email) " 
            + "SELECT * FROM ( SELECT '" + name + "' AS name, '" + password + "' AS password, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Renters WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE name ='" 
        + name + "' AND password ='" + password + "' AND email ='" + email + "';");
        
        if (results.next()) {
            idNum = Integer.parseInt(results.getString(1));
        }
    }
    public RegisteredRenter(int id) throws SQLException {
        db = new SQLConnection();

        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE rID ='" 
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

    // Method Functions
    public void unsubscribe() {
        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "DELETE FROM Renters WHERE " 
            + "name='" + name + "' AND password='" + password + "' AND email='" + email + "';";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
