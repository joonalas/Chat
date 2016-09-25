package chat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private final String text;
    private final User user;
    private final Timestamp ts;
    
    public Message(User user, String text) {
        this.text = text;
        this.user = user;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        long time = date.getTime();
        this.ts = new Timestamp(time);
    }

    public User getUser() {
        return user;
    }
    
    @Override
    public String toString() {
        return String.format("%s@%s: %s", this.user.getName(), this.ts.toString(), this.text);
    }
}
