package client;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;



public class Client{
    
    public void connect() throws IOException{
        //byte[] addressBytes = {(byte)(87), (byte)(95), (byte)(52), (byte)(167)};
        byte[] addressBytes = {(byte)(10), (byte)(112), (byte)(213), (byte)(83)};
        InetAddress address = InetAddress.getByAddress(addressBytes);
		
        Socket clientSocket = new Socket(address, 52828);
        ChatSession chat = new ChatSession(clientSocket.getInputStream(), clientSocket.getOutputStream());
        chat.run();
    }
    
}
