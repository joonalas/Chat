/*Lassila Joonas 1402909
* Hölttä Niko 1503558
* Ly Hoang 1504238
*/

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
        
		/*Timestamp creation*/
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        long time = date.getTime();
        this.ts = new Timestamp(time);
    }

    public User getUser() {
        return user;
    }
    
    @Override
	/*Messages are in form Username@dd-MM-yyyy hh:mm:ss: message text*/
    public String toString() {
        return String.format("%s@%s: %s", this.user.getName(), this.ts.toString(), this.text);
    }
}
