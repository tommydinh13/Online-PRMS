package Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyList implements Subject{
    
    public ArrayList<Property> properties;
	public ArrayList<Observer> observers;
	public Property property;

    public PropertyList(){
        observers = new ArrayList<Observer>();
		properties  = new ArrayList <Property>();
    }

    public void setObservers(ArrayList<Observer> obs){
        observers = obs;
		notifyObservers();
    }

	public ArrayList<Observer> getObservers(){
		return observers;
	}

    public void addProperty(Property p){
        properties = new ArrayList<Property>();
		properties.add(p);
        notifyObservers();
    }

    public void updateRenter(Property r) 
	{
		for (int i = 0; i < properties.size(); i++) {
			if (properties.get(i) == r) {
				properties.remove(i);
				break;
			}
		}
		addProperty(r);
	}

	public void remove(Observer observer) {
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i) == observer) {
				observers.remove(i);
				return;
			}
		}
	}

	public void register(Observer observer) {
		PropertyDatabaseController pdc = new PropertyDatabaseController();
		ArrayList<RegisteredRenter> ros = pdc.getRenters();

		for (int i = 0; i < ros.size(); i++) observers.add(ros.get(i));
	}

	public void notifyObservers() {
		for (Observer o: observers)
			o.update(property);
	}
	
	public static void main(String[] args)
	{
		PropertyList list = new PropertyList();
		list.notifyObservers();
	}

}	












