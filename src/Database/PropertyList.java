package Database;

import java.util.ArrayList;

public class PropertyList implements Subject{
    
    public ArrayList<Property> properties;
	public ArrayList<Observer> observers;
	public Property property;

    public PropertyList(){
        observers = new ArrayList<Observer>();
		properties  = new ArrayList <Property>();

		PropertyDatabaseController pdc = new PropertyDatabaseController();
		ArrayList<RegisteredRenter> ros = pdc.getRenters();
		for (int i = 0; i < ros.size(); i++) {observers.add(ros.get(i));}
		System.out.println(ros.size());
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
		property = p;
        notifyObservers();
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












