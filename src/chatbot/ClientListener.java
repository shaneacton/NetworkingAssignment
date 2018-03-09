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
        
        try {
            inp = client.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(client.getOutputStream());
            //System.out.println("out is not null");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {


        while (true) {//forever read messages from the server
            Message message = receiveFromClient();

            if (message.getCommand().equals("quit")) {
                try {
                    client.close();
                    System.out.println("closing client");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return;
            } else if (message.getCommand().equals("name")) {
                System.out.println("SERVER: found new client conection: '" + message.message + "'");
                clientName = message.message;
            } else if (message.getCommand().equals("message")) {
//                System.out.println(message.header);
//                System.out.println(message.getArg(2));
                sendToClient("message:"+ message.getArg(1),message.message, Integer.parseInt(message.getArg(2)));
                System.out.println("from " + clientName + " to SERVER: '" + message.message + "'");
            }
            //System.out.println(message);
        }
    }

    public Message receiveFromClient() {
        String line;

        try {
            line = brinp.readLine() + "\n";
            line += brinp.readLine();

            return new Message(line);
        } catch (IOException e) {
            e.printStackTrace();
            return new Message("q\n\n");
        }
    }

    public void sendToClient(String header, String message) {
        try {
            System.out.println("sending message to client " + header + " " + message);
            //System.out.println("is out null? "+out == null);
            out.writeChars(header + "\n" + message + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void sendToClient(String header, String message, int clientID) {
        try {
            System.out.println("sending message to client " + header + " " + message);
            //System.out.println("is out null? "+out == null);
            Server.clients.get(clientID).out.writeChars(header + "\n" + message + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
