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
    public ArrayList<Property> performSearch(String[] ht, int bathMin, int bathMax, int bedMin, int bedMax, String furnished, String cityQ, double pLow, double pHigh) {
        ArrayList<Property> properties = new ArrayList<Property>();

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Properties WHERE " 
            + "p_type='" + ht[0] + "' AND bedrooms>=" + Integer.toString(bedMin) + " AND bedrooms<=" + Integer.toString(bedMax) + " AND bathrooms>=" + Integer.toString(bathMin) + " AND bathrooms<=" + Integer.toString(bathMax) + " AND furnished='" + furnished + "' AND city_quadrant='" + cityQ + "' AND state_of_listing='Active' AND "
            + "price>=" + Double.toString(pLow) + " AND price<=" + Double.toString(pHigh) + ";";

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

    public static void main(String []s) throws SQLException {
    }
}