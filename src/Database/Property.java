package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Property {
    private String address;
    private String houseType;
    private int numBathrooms;
    private int numBedrooms;
    private String furnishedStatus;
    private String cityQuadrant;
    private double price;
    private String stateofListing;
    private int idNum;

    // Constructors
    public Property(String a, String ht, int ba, int be, String fs, String cq, double p, String sol) throws SQLException {
        address = a;
        houseType = ht;
        numBathrooms = ba;
        numBedrooms = be;
        furnishedStatus = fs;
        cityQuadrant = cq;
        price = p;
        stateofListing = sol;
    }

    // Getters and Setters
    public String getAddress() {
        return address;
    }
    public String getHouseType() {
        return houseType;
    }
    public int getBathrooms() {
        return numBathrooms;
    }
    public int getBedrooms() {
        return numBedrooms;
    }
    public String getFurnishedStatus() {
        return furnishedStatus;
    }
    public String getCityQuadrant() {
        return cityQuadrant;
    }
    public double getPrice() {
        return price;
    }
    public String getStateofListing() {
        return stateofListing;
    }
    public int getID() {
        return idNum;
    }
}
