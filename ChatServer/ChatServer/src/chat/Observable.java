package chat;

interface Observable {
    public void register(Observer o);
    public void deregister(Observer o);
    void notifyObservers(Message m);
}
