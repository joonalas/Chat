package chat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.io.PrintStream;

public class ChatServer {
    /*Database is a singleton object (only one instance) which implements observable
    * interface. Database takes care of preserving and updating the chat data (history, users, etc.).
    * Database also takes care of notifying all active observers when new message is sent.
    */
    
    private final DataBase data;
    

    public ChatServer() {
        /*getInstance() calls the DataBase constructor if data is not set (=null).
        * getInstance() return DataBase object.*/
        data = DataBase.getInstance();
    }
    
    /*ServerSocket.accept() throws IOException if I/O error occurs while waiting for connection.
    * IOException must be catched.*/
    public void serve() {
        try{
			/*Arbitary port number 52828 is used for the ServerSocket*/
            ServerSocket mainSocket = new ServerSocket(/*0*/52828,3);
            System.out.println("Main socket port number " + mainSocket.getLocalPort());
            
            while(true) {
				/*Server blocks until connection is made. New socket is created for the connection
				* CommandInterpreter takes client's input and does tasks according to the given commands.
				* DataBase is passed to CommandInterpreter, so that it can write and get data to and from
				* variables of DataBase.
				* CommandInterpreter is launched in a new Thread so new connections can be made to ServerSocket.
				*/
                Socket socket = mainSocket.accept();
                CommandInterpreter ci = new CommandInterpreter(socket.getInputStream(), new PrintStream(socket.getOutputStream(), true, "UTF-8"), data);
                Thread chatThread = new Thread(ci);
                chatThread.start();
                
            }
        } catch(IOException e) {
			/*In unfortunate event of IOException we just print the exception message*/
            System.out.println(e.getMessage());
        }
    }
    
}
