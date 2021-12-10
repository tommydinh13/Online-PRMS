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

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private final String DBURL; // Databse name
    private final String USERNAME;  // Server connection username
    private final String PASSWORD;  // Server connection password 
    private Connection dbConnect;

    // Constructors
    public SQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DBURL = "jdbc:mysql://127.0.0.1/RPMS";
        USERNAME = "KundaiTD";
        PASSWORD = "manage316";
    }
    public SQLConnection(String dburl) {
        DBURL = dburl;
        USERNAME = "KundaiTD";
        PASSWORD = "manage316";
    }
    public SQLConnection(String dburl, String username, String password) {
        DBURL = dburl;
        USERNAME = username;
        PASSWORD = password;
    }
    
    // Getters
    public String getDBURL() {
        return DBURL;
    }
    public String getUSERNAME() {
        return USERNAME;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }
    public Connection getConnection() {
        return dbConnect;
    }

    // Methods
    public void initializeConnection() {
        // Initializing a connection based off of the specified and set database details
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void closeConn() {
        // Closing the connection that was previously made.
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
