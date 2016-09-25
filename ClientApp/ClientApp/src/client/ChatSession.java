package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChatSession implements Runnable{
    private final MessageListener listener;
    private final Thread listenDaemon;
    private final Scanner reader;
    private final PrintWriter outToServer;
    
    public ChatSession(InputStream in, OutputStream out,Socket socket){
        this.listener = new MessageListener(in);
        this.listenDaemon = new Thread(this.listener);
        this.reader = new Scanner(System.in);
        this.outToServer = new PrintWriter(out, true);
    }
    
    @Override
    public void run(){
        
        listenDaemon.isDaemon();
        listenDaemon.start();
        while(true){
            outToServer.println(reader.nextLine());
        }
    }
}
