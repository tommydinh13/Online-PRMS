package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Email {
    private int idNum;
    private int landlord;
    private int property;
    private String rEmail;
    private String subject;
    private String body;
    private SQLConnection db;

    // Constructors
    public Email(){
        db = new SQLConnection();
    }
    public Email(int l, int prop, String renter, String s, String b) {
        landlord = l;
        property = prop;
        rEmail = renter;
        subject = s;
        body = b;
        db = new SQLConnection();
    }
    public Email(int id){
        idNum = id;
        db = new SQLConnection();

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Emails WHERE " 
            + "eID=" + idNum + ";";

            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            if (results.next()) {
                landlord = Integer.parseInt(results.getString("landlord"));
                property = Integer.parseInt(results.getString("property"));
                rEmail = results.getString("renter_email");
                subject = results.getString("subject");
                body = results.getString("body");
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Email(String propID){
        property = Integer.parseInt(propID);
        db = new SQLConnection();

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Properties WHERE " 
            + "pID=" + property + ";";

            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                int l = Integer.parseInt(results.getString("landlord"));
                landlord = l;
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public int getID() {
        return idNum;
    }
    public int getLandlord() {
        return landlord;
    }
    public int getProperty() {
        return property;
    }
    public String getRenter() {
        return rEmail;
    }
    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
    public void setID(int id) {
        idNum = id;
    }
    public void setLandlord(int l) {
        landlord = l;
    }

    // Method Functions
    public void draft(int propID, String r, String s, String b) {
        rEmail = r;
        subject = s;
        body = b;
        property = propID;

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Properties WHERE " 
            + "pID=" + property + ";";

            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                int l = Integer.parseInt(results.getString("landlord"));
                landlord = l;
            }
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void sendEmail() {
        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Emails (property, landlord, subject, body, renter_email) VALUES (" + Integer.toString(property) + ", " + Integer.toString(landlord) + ", '" + subject + "', '" + body + "', '" + rEmail + "');";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
