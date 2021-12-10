package Database;

import java.util.ArrayList;

public class PropertyList implements Subject{
    
    private ArrayList<Observer> observers;
    public ArrayList<Property> properties;
	public Property property;

    public PropertyList(){
        observers = new ArrayList<Observer>();
    }

    public void setObservers(ArrayList<Observer> obs){
        observers = obs;
    }

    public void addProperty(Property p){
        properties.add(p);
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












