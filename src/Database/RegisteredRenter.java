package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RegisteredRenter implements Observer{
    private String name;
    private String password;
    private String email;
    private int idNum;
    private SQLConnection db;
    
    // Constructors
    public RegisteredRenter(String n, String p, String em) throws SQLException {
        name = n;
        password = p;
        email = em;
        db = new SQLConnection();

        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "INSERT INTO Renters(name, password, email) " 
            + "SELECT * FROM ( SELECT '" + name + "' AS name, '" + password + "' AS password, '" + email + "' AS email) AS temp "
            + "WHERE NOT EXISTS (SELECT name FROM Renters WHERE name = '" + name + "') LIMIT 1;";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE name ='" 
        + name + "' AND password ='" + password + "' AND email ='" + email + "';");
        
        if (results.next()) {
            idNum = Integer.parseInt(results.getString(1));
        }
    }
    public RegisteredRenter(int id) throws SQLException {
        db = new SQLConnection();

        db.initializeConnection();
        Statement myStmt = db.getConnection().createStatement();
        ResultSet results = myStmt.executeQuery("SELECT * FROM Renters WHERE rID ='" 
        + id + "';");
        
        if (results.next()) {
            idNum = id;
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
    public int getID() {
        return idNum;
    }
    public void setID(int id) {
        idNum = id;
    }
    public String getNotify() {
        String notify = "NO";

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Renters WHERE rID=" + idNum + ";";
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                notify = results.getString("notify");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notify;
    }
    public void setNotify() {
        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "UPDATE Renters SET " + "notify='NO' WHERE rID=" + idNum + ";";
            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method Functions
    public void unsubscribe() {
        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "DELETE FROM Renters WHERE " 
            + "name='" + name + "' AND password='" + password + "' AND email='" + email + "';";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Property> performSearch() {
        ArrayList<Property> properties = new ArrayList<Property>();

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Search_Criteria WHERE renter=" + idNum + ";";
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                String hTypes[] = results.getString("p_type").split("-", -1);
                String furnish[] = results.getString("furnished").split("-", -1);
                String cityQuad[] = results.getString("city_q").split("-", -1);
                PropertyDatabaseController pd = new PropertyDatabaseController();
                properties = pd.performSearch(hTypes, Integer.parseInt(results.getString("bath_min")), Integer.parseInt(results.getString("bath_max")), Integer.parseInt(results.getString("bed_min")), Integer.parseInt(results.getString("bath_max")), furnish, cityQuad, Double.parseDouble(results.getString("price_min")), Double.parseDouble(results.getString("price_max")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public void saveCriteria(String[] ht, int bathMin, int bathMax, int bedMin, int bedMax, String[] furnished, String[] cityQ, double pLow, double pHigh) {
        String hTypes = "";
        String furnish = "";
        String cityQuad = "";
        boolean exists = false;

        for (int i = 0; i < ht.length; i++) {
            hTypes += ht[i];
            if (i+1 != ht.length) {
                hTypes += "-";
            }
        }
        for (int i = 0; i < furnished.length; i++) {
            furnish += furnished[i];
            if (i+1 != furnished.length) {
                furnish += "-";
            }
        }
        for (int i = 0; i < cityQ.length; i++) {
            cityQuad += cityQ[i];
            if (i+1 != cityQ.length) {
                cityQuad += "-";
            }
        }

        db.initializeConnection();
        try {
            String search = "SELECT * FROM Search_Criteria WHERE renter=" + idNum + ";";
            Statement myStmt = db.getConnection().createStatement();
            ResultSet results = myStmt.executeQuery(search);
            
            if (results.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (exists) {
            changeCriteria(hTypes, bathMin, bathMax, bedMin, bedMax, furnish, cityQuad, pLow, pHigh);
        } else {
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
        db.initializeConnection();
        try (Statement stmt = db.getConnection().createStatement();) {
            String insertSql = "UPDATE Search_Criteria SET " 
            + "p_type='" + hTypes + "', bath_min=" + Integer.toString(bathMin) + ", bath_max=" + Integer.toString(bathMax) + ", bed_min=" + Integer.toString(bedMin) + ", bed_max=" + Integer.toString(bedMax) + ", furnished='" + furnish + "', city_q='" + cityQuad + "', price_min=" + Double.toString(pLow) + ", price_max=" + Double.toString(pHigh)
            + " WHERE renter=" + idNum + ";";

            stmt.executeUpdate(insertSql);
            db.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateNotify(Property prop) {
        ArrayList<Property> searched = performSearch();
        boolean notify = false;

        for (int i = 0; i < searched.size(); i++) {
            if (searched.get(i).getID() == prop.getID()) notify = true;
        }

        if (notify) {
            db.initializeConnection();
            try (Statement stmt = db.getConnection().createStatement();) {
                String insertSql = "UPDATE Renters SET " + "notify='YES' WHERE rID=" + idNum + ";";
                stmt.executeUpdate(insertSql);
                db.closeConn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void update(Property prop) {
        // TODO Auto-generated method stub
        updateNotify(prop);
    }
}
