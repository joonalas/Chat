package chat;

import java.io.InputStream;
import java.io.PrintStream;
//import java.util.Scanner;

public class User implements Observer{
    private String name;
    //private final Scanner reader;
    private final PrintStream output;
    
    public User(String name, InputStream in, PrintStream out) {
        this.name = name;
        //this.reader = new Scanner(in);
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
