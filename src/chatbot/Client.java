/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbot;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author laptop
 */
public class Client {
    
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    
    String name;
    
    public Client(String name) {
        try {
            socket = new Socket("localhost", 1234);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            
            sendToServer("name",  name);
        } catch (IOException ex) {
        }
        this.name = name;
    }
    
    void sendToServer(String header, String message) {
        try {
            out.writeChars(header + "\n" + message + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    void closeClient() {
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
