package Database;

public interface Subject {
   
    public void remove (Observer o);
    public void register(Observer o);
    public void notifyObservers();
}
