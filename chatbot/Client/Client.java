/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot.Client;

import chatbot.Message;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author laptop
 */
public class Client {

    Socket socket;
    DataInputStream in;
    BufferedReader brinp = null;

    DataOutputStream out;

    String name;
    int ID;
    
    Thread serverListener;

    public Client(String name) {
        try {
            socket = new Socket("localhost", 1234);
            in = new DataInputStream(socket.getInputStream());
            brinp = new BufferedReader(new InputStreamReader(in));

            out = new DataOutputStream(socket.getOutputStream());

            sendToServer("name", name);
        } catch (IOException ex) {
        }
        this.name = name;
        
        serverListener = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Message message = receiveFromServer();
                    //System.out.println("received message " + message);
                    if(message.getCommand().equals("id")){
                        ID = Integer.parseInt(message.message);
                        System.out.println("CLIENT " + name + " has been given id " + ID);
                    }else if(message.getCommand().equals("test")){
                        System.out.println(message);
                    }else if(message.getCommand().equals("message")){
                        System.out.println("message from " + message.getArg(1) + ": " + message.message);
                    }
                }
            }
        });
        
        serverListener.start();
    }

    void sendToServer(String header, String message) {
        try {
            out.writeChars(header + "\n" + message + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Message receiveFromServer() {
        String line;

        try {
            line = brinp.readLine() + "\n";
            //System.out.println(line);
            line += brinp.readLine();

            return new Message(line);
        } catch (IOException e) {
            e.printStackTrace();
            return new Message("q\n\n");
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
