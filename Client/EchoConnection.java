/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 12.05.2016
  * @author
  */
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



public class EchoConnection extends Client {
  clientGui cl;
  
  
  public EchoConnection(String pServerIP, int pServerPort,clientGui cl) {
    super(pServerIP, pServerPort,cl);
    this.cl=cl;
  }
  
  public void processMessage(String pMessage) { 
  cl.processNewMessage(pMessage);
  }
  
    
} // end of class TimeConnection
  