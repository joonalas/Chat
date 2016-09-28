/*Lassila Joonas 1402909
* Hölttä Niko 1503558
* Ly Hoang 1504238
*/


package chat;

import java.io.PrintStream;

public class User implements Observer{
    private String name;
    private final PrintStream output;
    
    public User(String name, PrintStream out) {
        this.name = name;
        this.output = out;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    /*if sentences format the output. Using telnet client, we don't want new meassages
    * pop up in our command line*/
    public void update(Message m) {
        if(this != m.getUser()){
            output.println();
        }
        output.println(m.toString());
        if(this != m.getUser()){
            output.println("> ");
        }
    }
}
