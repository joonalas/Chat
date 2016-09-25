package chat;

public interface Observer {
    public void update(Message m);
    public String getName();
}
