package Database;

public interface Subject {
   
    public void remove (Observer observer);
    public void register(Observer observer);
    public void notifyObservers();
}
