/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot.Server;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author abrsas002
 */
public class RunServer
{

    static Server server;

    public static void main(String[] args)
    {

        Socket newClientSocket = null;
        server = new Server();
        while (true)
        {
            try
            {
                newClientSocket = server.serverSocket.accept();//listening for new client connection
            } catch (IOException e)
            {
                System.out.println("I/O error: " + e);
            }
            // new thread for a new client
            System.out.println("Adding client");
            server.addClient(new ClientListener(newClientSocket));
            System.out.println(Server.clients.size());
        }
    }

}
