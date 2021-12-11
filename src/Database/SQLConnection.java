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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private String DBURL; // Databse name
    private String USERNAME;  // Server connection username
    private String PASSWORD;  // Server connection password 
    private Connection dbConnect;

    // Constructors
    public SQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"serverInfo.txt"));
			String line = reader.readLine();
            DBURL = line;
			line = reader.readLine();
            USERNAME = line;
            line = reader.readLine();
            PASSWORD = line;
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public SQLConnection(String dburl) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("serverInfo.txt"));
			String line = reader.readLine();
            DBURL = "jdbc:mysql://"+dburl;
			line = reader.readLine();
            USERNAME = line;
            line = reader.readLine();
            PASSWORD = line;
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
