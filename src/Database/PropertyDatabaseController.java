package Database;

import java.sql.SQLException;

public class PropertyDatabaseController {
    public static void main(String []s) throws SQLException {
        RegisteredRenter rr1 = new RegisteredRenter("Johnny Does", "pass123", "example@email.com");
        rr1.unsubscribe();
        System.out.println("SQL Success!");
    }
}