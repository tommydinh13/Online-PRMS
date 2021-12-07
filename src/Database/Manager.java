package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager {
    private String name;
    private String password;
    private String email;
    
    private SQLConnection db;
    
    // Constructors
    public Manager() throws SQLException {
        db = new SQLConnection();
    }
    public Manager(String n, String em, String p) {
        name = n;
        password = p;
        email = em;
        db = new SQLConnection();

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Managers (name, password, email) " 
            + "SELECT * FROM (SELECT '" + name + "' AS name, '" + password + "' AS password, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Managers WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Manager(int id) throws SQLException {
        db = new SQLConnection();

        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Managers WHERE mID ='" 
        + id + "';");
        
        if (results.next()) {
            int idNum = id;
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

    // Method Functions
    public ArrayList<Landlord> searchLandlords(){
        ArrayList<Landlord> landlords = new ArrayList<Landlord>();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Landlords;");
            
            while (results.next()) {
                int idNum = Integer.parseInt(results.getString(1));
                Landlord l = new Landlord(results.getString("name"), results.getString("email"), results.getString("password"));
                l.setID(idNum);
                landlords.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return landlords;
    }
    public ArrayList<RegisteredRenter> searchRenters(){
        ArrayList<RegisteredRenter> renters = new ArrayList<RegisteredRenter>();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Renters;");
            
            while (results.next()) {
                int idNum = Integer.parseInt(results.getString(1));
                RegisteredRenter rr = new RegisteredRenter(results.getString("name"), results.getString("email"), results.getString("password"));
                rr.setID(idNum);
                renters.add(rr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return renters;
    }
    public ArrayList<Property> searchProperties(String sol){
        ArrayList<Property> properties = new ArrayList<Property>();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE state_of_listing='"+sol+"';");
            
            while (results.next()) {
                int idNum = Integer.parseInt(results.getString(1));
                Property prop = new Property(results.getString("address"), results.getString("p_type"), Integer.parseInt(results.getString("bathrooms")), Integer.parseInt(results.getString("bedrooms")), results.getString("furnished"), results.getString("city_quadrant"), Double.parseDouble(results.getString("price")));
                prop.setSOL(sol);
                prop.setID(Integer.parseInt(results.getString("pID")));
                Landlord l = new Landlord(Integer.parseInt(results.getString("landlord")));
                prop.setLandlord(l);
                properties.add(prop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public ArrayList<Property> searchProperties(){
        ArrayList<Property> properties = new ArrayList<Property>();

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties;");
            
            while (results.next()) {
                int idNum = Integer.parseInt(results.getString(1));
                Property prop = new Property(results.getString("address"), results.getString("p_type"), Integer.parseInt(results.getString("bathrooms")), Integer.parseInt(results.getString("bedrooms")), results.getString("furnished"), results.getString("city_quadrant"), Double.parseDouble(results.getString("price")));
                prop.setSOL(results.getString("state_of_listing"));
                prop.setID(Integer.parseInt(results.getString("pID")));
                Landlord l = new Landlord(Integer.parseInt(results.getString("landlord")));
                prop.setLandlord(l);
                properties.add(prop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public int totalProperties(String sol){
        int count = 0;

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties WHERE state_of_listing='"+sol+"';");
            while (results.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public int totalProperties(){
        int count = 0;

        db.initializeConnection();
        try {
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery("SELECT * FROM Properties;");
            while (results.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
    public void changeSOL(int propID) {
        
    }
}
