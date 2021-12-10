/**
 * @author Kundai Dziwa <a href="mailto:kundai.dziwa@ucalgary.ca">
 *         kundai.dziwa@ucalgary.ca</a>
 *
*@author Tommy Dinh <a href="mailto:tommy.dinh@ucalgary.ca">
 *         tommy.dinh@ucalgary.ca</a>
 * 
*@author Tien Dat Johny Do <ahref ="tiendat.do@ucalgary.ca">
 *        tiendat.do@ucalgary.ca</a>
 * 
 *@author Stalin D Cunha<a href="mailto:stalin.dcunha@ucalgary.ca">
 *         stalin.dcunha@ucalgary.ca</a>
 * 
 * @version 1.1
 * @since 1.0
 */ 
package Database;

import java.util.ArrayList;

public class PropertyList implements Subject{
    
    public ArrayList<Property> properties;
	public ArrayList<Observer> observers;
	public Property property;

    //constructor
	public PropertyList(){
        observers = new ArrayList<Observer>();
		properties  = new ArrayList <Property>();

		PropertyDatabaseController pdc = new PropertyDatabaseController();
		ArrayList<RegisteredRenter> ros = pdc.getRenters();
		for (int i = 0; i < ros.size(); i++) {observers.add(ros.get(i));}
		System.out.println(ros.size());
    }

    //setter to set the ArrayList of observers(renters) which then notifies all observers
	public void setObservers(ArrayList<Observer> obs){
        observers = obs;
		notifyObservers();
    }

	//getter to get the ArrayList of observers(renters)
	public ArrayList<Observer> getObservers(){
		return observers;
	}

    //adds a property to a property ArrayList and notifies the observers(renters) of a new property being added to the database
	public void addProperty(Property p){
        properties = new ArrayList<Property>();
		properties.add(p);
		property = p;
        notifyObservers();
    }

	//removes an observer(renter) from the ArrayList of observers(renters)
	public void remove(Observer observer) {
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i) == observer) {
				observers.remove(i);
				return;
			}
		}
	}

	//initializes the observers(renters) into an ArrayList by grabbing data from PropertyDatabaseController
	public void register(Observer observer) {
		PropertyDatabaseController pdc = new PropertyDatabaseController();
		ArrayList<RegisteredRenter> ros = pdc.getRenters();

		for (int i = 0; i < ros.size(); i++) observers.add(ros.get(i));
	}

	//notifies observers(renters) by calling the update function in RegisteredRenterForm to notify the change in new property listing
	public void notifyObservers() {
		for (Observer o: observers)
			o.update(property);
	}

	//Test Main Funtion
	public static void main(String[] args)
	{
		PropertyList list = new PropertyList();
		list.notifyObservers();
	}

}	












