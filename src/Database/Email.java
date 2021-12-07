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
    public Email(int propID){
        property = propID;
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
    public void draft(String r, String s, String b) {
        rEmail = r;
        subject = s;
        body = b;
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
