package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class UserController {
    private SQLConnection db;

    // Constructors
    public UserController() {
        db = new SQLConnection(); // Creating a SQL connection to use for SQL queries
    }
    
    // Method Functions 
    public int checkUser(String email, String password, String role) {
        if (role.equals("Landlord")) {
            db.initializeConnection();
            try {
                Statement myStmt = db.getConnection().createStatement();
                ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords WHERE email ='" 
                + email + "' AND password ='" + password + "';");
                
                if (results.next()) {
                    return Integer.parseInt(results.getString(1));
                }
                db.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (role.equals("Manager")) {
            db.initializeConnection();
            try {
                Statement myStmt = db.getConnection().createStatement();
                ResultSet results = myStmt.executeQuery("SELECT * FROM Managers WHERE email ='" 
                + email + "' AND password ='" + password + "';");
                
                if (results.next()) {
                    return Integer.parseInt(results.getString(1));
                }
                db.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (role.equals("Registered Renter")) {
            db.initializeConnection();
            try {
                Statement myStmt = db.getConnection().createStatement();
                ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE email ='" 
                + email + "' AND password ='" + password + "';");
                
                if (results.next()) {
                    return Integer.parseInt(results.getString(1));
                }
                db.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
