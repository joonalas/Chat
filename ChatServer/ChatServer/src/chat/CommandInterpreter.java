package chat;

import java.util.Scanner;
import java.io.*;
import java.util.NoSuchElementException;

public class CommandInterpreter implements Runnable {
	/*Reader for reading socket input, output to print servers output in the client's end,
	* data is the singleton we passed to CommandInterpreter constructor, currentUser is
	* the user object associated with this instance of CommandInterpreter.
	*/
	
    private final Scanner reader;
    private final PrintStream output;
    private final DataBase data;
    private final User currentUser;

    public CommandInterpreter(InputStream in, PrintStream out, DataBase database) {
        this.reader = new Scanner(in);
        this.output = out;
        this.data = database;
		/*see the User class*/
        this.currentUser = new User("", in, out);
        this.data.register(currentUser);
    }
    @Override
    public void run(){
        output.println("Hello!");
        //clears input from any protocol dependant mess (telnet).
        //reader.nextLine();
		
        boolean exit = false;
        while(!exit){
            output.println("> ");
            try{
                exit = this.interpretCommand(reader.nextLine());
            } catch(NoSuchElementException e){                //if client goes through an unexpected exit, reader won't be able to read next line
                exit = this.interpretCommand(":quit");        //and it throws NoSuchElementException. If :quit command is not executed after such exit,
            }                                                 //User associated with the CommandInterpreter will stay unwantedly in the DataBase.
    }
    
    public boolean interpretCommand(String input) {
		//sort out the command from user input. Command is the substring of user input which starts at 0 and ends in first space
        String command = (input.contains(" ")) ? input.substring(0, input.indexOf(' ')) : input;
        switch(command){
            case ":list":
                output.println("Users:");
                for(Observer observer : data.getObservers()){
                    output.println(observer.getName());
                }
                output.println();
                break;
            case ":history":
                output.println("History:");
                for(Message entry : data.getChatHistory()){  //chat history is an ArrayList<Message>
                    output.println(entry.toString());
                }
                output.println();
                break;
            case ":user":
				/*anything typed after the :user command is set as new username*/
                if(input.contains(" ")){
                    currentUser.setName(input.substring(input.indexOf(' ') + 1));
                    output.println("Username is " + currentUser.getName());
                } else{
                    output.println("Username is " + currentUser.getName());
                }
                break;
            case ":quit":
                data.deregister(currentUser);    //-66612341818 is just arbitary exit message used in self-made client programs
                output.println("Goodbye.");      //which gives clients a signal to stop execution.
                output.println("-66612341818");
                return true;
                
            default:
                if(currentUser.getName().equals("")){
                    output.println("Username not set. Give :user command.");
                } else{
					/*Messages are saved as instances of Message class*/
                    Message m = new Message(currentUser, input);
                    data.appendHistory(m);
                    data.notifyObservers(m);
                    
                }
                break;
            }
        return false;
        
        
    }
    
}
