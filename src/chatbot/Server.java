/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author laptop
 */
public class Server {

    public ServerSocket serverSocket = null;

    final static int PORT = 1234;
    static ArrayList<ClientListener> clients;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients = new ArrayList<>();
    }

    public void addClient(ClientListener newClient) {
        clients.add(newClient);
        newClient.start();

        clients.get(clients.size() - 1).sendToClient("id", (clients.size() - 1) + "");
    }

}
