/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laptop
 */
public class Server {

    public ServerSocket serverSocket = null;

    final static int PORT = 1234;
    public static List<ClientListener> clients = new ArrayList<>();

    public Server() { 
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void addClient(ClientListener newClient) {
        clients.add(newClient);
        newClient.start();

        clients.get(clients.size() - 1).sendToClient("id", (clients.size() - 1) + "");
    }

}
