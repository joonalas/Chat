/*Lassila Joonas 1402909
* Hölttä Niko 1503558
* Ly Hoang 1504238
*/

package chat;

import java.io.IOException;


public class Main {
    
    public static void main(String[] args) throws IOException{
        ChatServer server = new ChatServer();
        server.serve();
        
    }
}
