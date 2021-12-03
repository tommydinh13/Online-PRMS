package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
    private SQLConnection db;

    // Constructors
    public UserController() {
        db = new SQLConnection();
    }
    
    // Method Functions 
    public int checkUser(String email, String password, String role) throws SQLException {
        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE email ='" 
        + email + "' AND password ='" + password + "';");
        
        if (results.next()) {
            return Integer.parseInt(results.getString(1));
        }

        return 0;
    }
}
