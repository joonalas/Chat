package chat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/*DataBase is for reserving and serving the chat data, like
* current observers, messages history, etc.
*/

public class DataBase implements Observable{
    private final ArrayList<Message> chatHistory;
    private final Set<Observer> observers;
    private static volatile DataBase instance = null;
    
	/*DataBase implements singleton pattern*/
    private DataBase() {
        this.chatHistory = new ArrayList<>();
        this.observers = new HashSet();
    }
    
    public static DataBase getInstance(){
        if(instance == null) {
            synchronized(DataBase.class) {
                if(instance == null) {
                    instance = new DataBase();
                }
            }
        } 
        return instance;
    }
        
    public void appendHistory(Message m){
        chatHistory.add(m);
    }

    public ArrayList<Message> getChatHistory() {
        return chatHistory;
    }

    public Set<Observer> getObservers() {
        return this.observers;
    }

    @Override
    public void register(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void deregister(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers(Message m) {
        for(Observer o : this.observers) {
            o.update(m);
        }
    }
    
        
}
