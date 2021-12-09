package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PropertyDatabaseController {
    private SQLConnection db;

    // Constructor
    public PropertyDatabaseController() {
        db = new SQLConnection();
    }

    // Method Functions
    public ArrayList<Property> performSearch(String[] ht, int bathMin, int bathMax, int bedMin, int bedMax, String[] furnished, String[] cityQ, double pLow, double pHigh) {
        ArrayList<Property> properties = new ArrayList<Property>();
        String hTypes = "";
        String furnish = "";
        String cityQuad = "";
        String bathMaximum = " AND bedrooms<=" + Integer.toString(bedMax);
        String bedMaximum = " AND bathrooms<=" + Integer.toString(bathMax);
        String priceMax = " AND price<=" + Double.toString(pHigh);

        for (int i = 0; i < ht.length; i++) {
            hTypes += "p_type='";
            hTypes += ht[i];
            if (i+1 == ht.length) {
                hTypes += "' AND ";
            } else {
                hTypes += "' OR ";
            }
        }
        for (int i = 0; i < furnished.length; i++) {
            furnish += "furnished='";
            furnish += furnished[i];
            if (i+1 == furnished.length) {
                furnish += "' AND ";
            } else {
                furnish += "' OR ";
            }
        }
        for (int i = 0; i < cityQ.length; i++) {
            cityQuad += "city_quadrant='";
            cityQuad += cityQ[i];
            if (i+1 == cityQ.length) {
                cityQuad += "' AND ";
            } else {
                cityQuad += "' OR ";
            }
        }
        if (bathMax == -1) bathMaximum = "";
        if (bedMax == -1) bedMaximum = "";
        if (pHigh == -1) priceMax = "";

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Properties WHERE " 
            + hTypes + "bedrooms>=" + Integer.toString(bedMin) + bedMaximum + " AND bathrooms>=" + Integer.toString(bathMin) + bathMaximum + " AND " + furnish + cityQuad + "state_of_listing='Active' AND "
            + "price>=" + Double.toString(pLow) + priceMax + ";";

            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            while (results.next()) {
                int idNum = Integer.parseInt(results.getString(1));
                Property prop = new Property(results.getString("address"), results.getString("p_type"), Integer.parseInt(results.getString("bathrooms")), Integer.parseInt(results.getString("bedrooms")), results.getString("furnished"), results.getString("city_quadrant"), Double.parseDouble(results.getString("price")));
                prop.setSOL(results.getString("state_of_listing"));
                prop.setID(idNum);
                Landlord l = new Landlord(Integer.parseInt(results.getString("landlord")));
                prop.setLandlord(l);
                properties.add(prop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public ArrayList<RegisteredRenter> getRenters() {
        ArrayList<RegisteredRenter> rrs = new ArrayList<RegisteredRenter>();

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Renters;";

            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            while (results.next()) {
                RegisteredRenter r = new RegisteredRenter(Integer.parseInt(results.getString("rID")));
                rrs.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rrs;
    }

    public static void main(String []s) throws SQLException {
    }
}