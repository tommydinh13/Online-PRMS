package Database;

import java.util.ArrayList;

public class PropertyList implements Subject{
    
    private ArrayList<Observer> observers;
    private ArrayList<Property> property;

    private PropertyList(){
        observers = new ArrayList<Observer>();
        property = new ArrayList<Property>();
    }

    public void setProperty(ArrayList<Property> prop){
        property = prop;
    }


    public void setObservers(ArrayList<Observer> obs){
        observers = obs;
    }

    public void addProperty(Property prop){
        property.add(prop);
        notifyObservers();
    }

    public void removeProperty(Property prop){
        for (int i = 0; i < property.size(); i++) {
			if (property.get(i) == prop) {
				property.remove(i);
				break;
			}
		}
		notifyObservers();
    }

    public void updateProperty(Property prop) 
	{
		for (int i = 0; i < property.size(); i++) {
			if (property.get(i) == prop) {
				property.remove(i);
				break;
			}
		}
		addProperty(prop);
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
		observers.add(observer);
		observer.update(property);
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












