import java.io.*;
import java.rmi.*;

public class FileServer {
    
    public static void main(String argv[]) {
        if (System.getSecurityManager() == null) {
            System.out.println("Setting the RMISecurityManager on System...");
            System.setSecurityManager(new RMISecurityManager());
        }
        
        try { 
            
            FileInterface fi = new FileImpl("FileServer");
            Naming.rebind("//127.0.0.1/FileServer", fi);
            System.out.println("Server Ready...Service is Running");

        } catch (Exception e) {
            System.out.println("FileServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}