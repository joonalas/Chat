/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joonas
 */
public class MessageListener implements Runnable{
    BufferedReader in;
    
    public MessageListener(InputStream inFromServer){
        in = new BufferedReader(new InputStreamReader(inFromServer));
    }
    
    @Override
    public void run(){
        while(true){
            try {
                String message = in.readLine();
                switch (message) {
                    case "> ":
                        System.out.print(message);
                        break;
                    case "-66612341818":
                        System.exit(0);
                        break;
                    default:
                        System.out.println(message);
                        break;
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
