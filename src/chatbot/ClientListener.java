/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chatbot;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author abrsas002
 */
public class ClientListener extends Thread {

    protected Socket client;

    InputStream inp = null;
    BufferedReader brinp = null;
    DataOutputStream out = null;
    
    String clientName;

    public ClientListener(Socket clientSocket) {
        this.client = clientSocket;
    }

    public void run() {

        try {
            inp = client.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            return;
        }

        while (true) {//forever read messages from the server
            Message message = receiveFromClient();
            //System.out.println(message);
            //System.out.println(message.header);
            
            if (message.getCommand().equals("q")) {
                try {
                    client.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return;
            } else if (message.getCommand().equals("name")) {
                System.out.println("SERVER: found new client conection: '" + message.message+"'");
                clientName = message.message;
            } else if (message.getCommand().equals("message")) {
                System.out.println("from " + clientName+ " to SERVER: '" + message + "'");
            }
        }
    }

    public Message receiveFromClient() {
        String line;

        try {
            line = brinp.readLine()+"\n";
            line+= brinp.readLine();
            
            return new Message(line);
        } catch (IOException e) {
            e.printStackTrace();
            return new Message("q\n\n");
        }
    }
}
