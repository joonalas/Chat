/*Lassila Joonas 1402909
* Hölttä Niko 1503558
* Ly Hoang 1504238
*/


package chat;

interface Observable {
    public void register(Observer o);
    public void deregister(Observer o);
    void notifyObservers(Message m);
}
