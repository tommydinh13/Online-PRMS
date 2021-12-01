package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Properties {
    private SQLConnection db;

    public Properties() {
        db = new SQLConnection();
    }
}
