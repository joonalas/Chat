package client;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;


public class ChatSession implements Runnable{
    private final MessageListener listener;
    private final Thread listenDaemon;
    private final Scanner reader;
    private final PrintWriter outToServer;
    
    public ChatSession(InputStream in, OutputStream out,Socket socket) throws UnsupportedEncodingException{
        this.listener = new MessageListener(in);
        this.listenDaemon = new Thread(this.listener);
        this.reader = new Scanner(System.in, "ISO8859_1");
        this.outToServer = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true);
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
