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

package Domain;

import Database.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegisteredRenter implements Observer{
    private String name;
    private String password;
    private String email;
    private int idNum;
    private SQLConnection db; // Connection to the database.
    
    // Constructors
    public RegisteredRenter(String n, String p, String em) {
        // Creating a renter instance based on the passed in data values.
        name = n;
        password = p;
        email = em;
        db = new SQLConnection();   // Connection to the database.

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Insertion query that first checks to see if the Renter exists.
            // If not then insert into the database.
            String insertSql = "INSERT INTO Renters(name, password, email) " 
            + "SELECT * FROM ( SELECT '" + name + "' AS name, '" + password + "' AS password, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Renters WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Find the newly registered renter or the previuos and retrieve the id.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE name ='" 
            + name + "' AND password ='" + password + "' AND email ='" + email + "';");
            
            if (results.next()) {
                // If it exists then assign the id to class variable.
                idNum = Integer.parseInt(results.getString(1));
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public RegisteredRenter(int id) {
        // Create a RegisteredRenter instance with the passed through renter id.
        db = new SQLConnection();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            // Selection query to find if the registered renter is in the database based on id.
            ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE rID ='" 
            + id + "';");
            
            if (results.next()) {
                // If the renter is found, populate all the class data values with that in the database.
                idNum = id;
                name = results.getString("name");
                email = results.getString("email");
                password = results.getString("password");
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
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
    // Notify Getter and Setters
    public String getNotify() {
        String notify = "NO";
        // Retrieve the Notification status of this registered renter.

        db.initializeConnection();
        try {
            // Select the renter who has the matching renter id.
            String search = "SELECT * FROM Renters WHERE rID=" + idNum + ";";
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                // If he exists get his notification status.
                notify = results.getString("notify");
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notify; // Return the retrived notification status, either YES or NO.
    }
    public void setNotify() {
        // Sets the Notification status of this renter to NO.

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Update query which changes the renters notify setting to NO.
            String insertSql = "UPDATE Renters SET " + "notify='NO' WHERE rID=" + idNum + ";";
            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method Functions
    public void unsubscribe() {
        // Unsubscribes the RegisterRenter from the database, making them a regular renter.
        // As well deletes their corresponding search criteria.

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Deletion query for the Renter in the Renters table within the database.
            String insertSql = "DELETE FROM Renters WHERE " 
            + "name='" + name + "' AND password='" + password + "' AND email='" + email + "';";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Deletion query for the Renters search criteria in the Search_Criteria table within the database.
            String insertSql = "DELETE FROM Search_Criteria WHERE " 
            + "renter=" + idNum + ";";
            
            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Property> performSearch() {
        ArrayList<Property> properties = new ArrayList<Property>();
        // Search through the entire Properties database and return all Properties which match the Registered Renter's saved search criteria.

        db.initializeConnection();
        try {
            // Find the search criteria which matches the renters id.
            String search = "SELECT * FROM Search_Criteria WHERE renter=" + idNum + ";";
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                // If it exists, run a search based on the saved criteria.
                String hTypes[] = results.getString("p_type").split("-", -1);
                String furnish[] = results.getString("furnished").split("-", -1);
                String cityQuad[] = results.getString("city_q").split("-", -1);
                PropertyDatabaseController pd = new PropertyDatabaseController();
                properties = pd.performSearch(hTypes, Integer.parseInt(results.getString("bath_min")), Integer.parseInt(results.getString("bath_max")), Integer.parseInt(results.getString("bed_min")), Integer.parseInt(results.getString("bath_max")), furnish, cityQuad, Double.parseDouble(results.getString("price_min")), Double.parseDouble(results.getString("price_max")));
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties; // Return all of the corresponding properties.
    }
    public void saveCriteria(String[] ht, int bathMin, int bathMax, int bedMin, int bedMax, String[] furnished, String[] cityQ, double pLow, double pHigh) {
        String hTypes = "";
        String furnish = "";
        String cityQuad = "";
        boolean exists = false;
        // Save the entered criteria to the Search criteria database under this renters id.

        // Stringify all the house type inputs.
        for (int i = 0; i < ht.length; i++) {
            hTypes += ht[i];
            if (i+1 != ht.length) {
                hTypes += "-";
            }
        }
        // Stringify all the furnished inputs.
        for (int i = 0; i < furnished.length; i++) {
            furnish += furnished[i];
            if (i+1 != furnished.length) {
                furnish += "-";
            }
        }
        // Stringify all the city quadrant inputs.
        for (int i = 0; i < cityQ.length; i++) {
            cityQuad += cityQ[i];
            if (i+1 != cityQ.length) {
                cityQuad += "-";
            }
        }

        db.initializeConnection();
        try {
            // Search for the search criteria which belong to this renters id.
            String search = "SELECT * FROM Search_Criteria WHERE renter=" + idNum + ";";
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                // Mark that the found criteria exists in the database.
                exists = true;
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (exists) {
            // If it exists the change or update the previously recorded and save criteria.
            changeCriteria(hTypes, bathMin, bathMax, bedMin, bedMax, furnish, cityQuad, pLow, pHigh);
        } else {
            // Otherwise, enter in for the first time the registered renter's search criteria.
            db.initializeConnection();
            try (Statement stmt = db.getConnection().createStatement();) {
                String insertSql = "INSERT INTO Search_Criteria (renter, p_type, bath_min, bath_max, bed_min, bed_max, furnished, city_q, price_min, price_max) VALUES (" 
                + idNum + ", '" + hTypes + "', " + Integer.toString(bathMin) + ", " + Integer.toString(bathMax) + ", " + Integer.toString(bedMin) + ", " + Integer.toString(bedMax) + ", '" + furnish + "', '" + cityQuad + "', " + Double.toString(pLow) + ", " + Double.toString(pHigh) + ");";

                stmt.executeUpdate(insertSql);
                db.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void changeCriteria(String hTypes, int bathMin, int bathMax, int bedMin, int bedMax, String furnish, String cityQuad, double pLow, double pHigh) {
        // Changes the previously set criteria to the new passed through values. 

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            // Query which updates all of the search criteria options for this registered renter. 
            String insertSql = "UPDATE Search_Criteria SET " 
            + "p_type='" + hTypes + "', bath_min=" + Integer.toString(bathMin) + ", bath_max=" + Integer.toString(bathMax) + ", bed_min=" + Integer.toString(bedMin) + ", bed_max=" + Integer.toString(bedMax) + ", furnished='" + furnish + "', city_q='" + cityQuad + "', price_min=" + Double.toString(pLow) + ", price_max=" + Double.toString(pHigh)
            + " WHERE renter=" + idNum + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean checkCriteria() {
        boolean exists = false;
        // Check wether this renter has an existing set search criteria. 
        // So that he can search based on that.

        db.initializeConnection();
        try {
            // Find the criteria which belongs to this renter based on his connected renter id.
            String search = "SELECT * FROM Search_Criteria WHERE renter=" + idNum + ";";
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                // If it is found, mark exists as true. 
                exists = true;
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists; // Return the exists values wether it is true or false.
    }
    public void updateNotify(Property prop) {
        // Observer design pattern implementation. 
        ArrayList<Property> searched = performSearch(); // Gets all properties that match this renter's search criteria.
        boolean notify = false;
        // Check if the property match the search criteria.

        for (int i = 0; i < searched.size(); i++) {
            // Check to see if the id of the new property match that of the property in the searched matches.
            // If they match, mark notify as true.
            if (searched.get(i).getID() == prop.getID()) notify = true;
        }

        if (notify) {
            db.initializeConnection();
            try (Statement stmt = db.getConnection().createStatement();) {
                // Udpate the registered renter's notify marker and YES.
                String insertSql = "UPDATE Renters SET notify='YES' WHERE rID=" + idNum + ";";
                stmt.executeUpdate(insertSql);
                db.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void update(Property prop) {
        // Update and an observer when notified by a change in the subject.
        // Pass the new property to be checked wether it matches this renters saved search criteria.
        updateNotify(prop);
    }
}
