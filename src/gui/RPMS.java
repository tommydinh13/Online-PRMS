/**
 * @author Kundai Dziwa <a href="mailto:kundai.dziwa@ucalgary.ca">
 *         kundai.dziwa@ucalgary.ca</a>
 *
*@author Tommy Dinh <a href="mailto:tommy.dinh@ucalgary.ca">
 *         tommy.dinh@ucalgary.ca</a>
 * 
*@author Tien Dat Johny Do <a href ="tiendat.do@ucalgary.ca">
 *        tiendat.do@ucalgary.ca</a>
 * 
 *@author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 * 
 * @version 1.9
 * @since 1.0
 */ 
package gui;

import java.io.FileWriter;
import java.io.IOException;

import Database.PropertyDatabaseController;

public class RPMS {
	public static void main(String[] args) {
		String dbs = args[0]; // Command line argument input 1
		String user = args[1]; // Command line argument input 2
		String pass = args[2]; // Command line argument input 3
		try {
            FileWriter writer = new FileWriter("serverInfo.txt", false);
            writer.write("jdbc:mysql://"+dbs+"/RPMSdb");
            writer.write("\r\n");
            writer.write(user);
            writer.write("\r\n");
            writer.write(pass);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

		PropertyDatabaseController pdc = new PropertyDatabaseController();
        if (!pdc.checkdb(dbs)) {
            System.out.println("Database does not exist.");
            System.out.println("******* Creating Database *******");
            pdc.createRPMSdb(dbs);
            System.out.println("Database created.");
        }

		System.out.println("RPMS App now running...");
		// this is where the program starts
		// starts by opening a loginForm
		LoginForm loginPage = new LoginForm();

	}

}