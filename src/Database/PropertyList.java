package Database;

import java.util.ArrayList;

public class PropertyList implements Subject{
    
    private ArrayList<Observer> observers;
    public ArrayList<Property> props;
	public Property property;

    public PropertyList(){
        observers = new ArrayList<Observer>();
    }

    public void setObservers(ArrayList<Observer> obs){
        observers = obs;
    }

    public void addProperty(Property p){
        props.add(p);
        notifyObservers();
    }

    public void removeRenter(Observer o){
        for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i) == o) {
				observers.remove(i);
				break;
			}
		}
    }

    public void updateRenter(Property r) 
	{
		for (int i = 0; i < props.size(); i++) {
			if (props.get(i) == r) {
				props.remove(i);
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
		observers.add(observer);
		// observer.update(renters);
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












