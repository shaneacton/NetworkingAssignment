/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author abrsas002
 */
public class RunServer {

    static Server server = new Server();

    public static void main(String[] args) {

        Socket newClientSocket = null;

        while (true) {
            try {
                newClientSocket = server.serverSocket.accept();//listening for new client connection
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a new client
            server.addClient(new ClientListener(newClientSocket));
        }
    }

}
