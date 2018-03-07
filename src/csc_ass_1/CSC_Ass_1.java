
package csc_ass_1;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CSC_Ass_1 
{

    
    public static void main(String[] args)
    {
        
        ServerSocket MyService = null;
        
        try 
        {
            MyService = new ServerSocket(1050);
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
        
        
        Socket MyClient = null;
        
        try 
        {
            //InetAddress addr = InetAddress.getByName("127.0.0.1");
            MyClient = new Socket("127.0.0.1",1050);
        } 
        catch (IOException ex) 
        {
            System.out.println(ex);
        }
        
        
        Socket serviceSocket = null;
        
        try
        {
            serviceSocket = MyService.accept();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
        
        
        DataInputStream input = null;
        
        try
        {
            input = new DataInputStream (MyClient.getInputStream());
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
        DataInputStream input2 = null;
        
        try
        {
            input2 = new DataInputStream(serviceSocket.getInputStream());
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
        DataOutputStream output = null;
        
        try
        {
            output = new DataOutputStream(MyClient.getOutputStream());
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
        PrintStream output2 = null;
        
        try
        {
            output2 = new PrintStream(serviceSocket.getOutputStream());
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
            
        try
        {
            output.close();
            input.close();
            MyClient.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
        try
        {
            output2.close();
            input2.close();
            serviceSocket.close();
            MyService.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
        
    }
    
}
