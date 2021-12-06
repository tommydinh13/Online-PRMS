package Database;

import java.util.ArrayList;

public interface Observer {
    
    public abstract void update(ArrayList<Property> properties);
}
