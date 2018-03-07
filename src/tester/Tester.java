/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author abrsas002
 */
public
        class Tester
{

    /**
     * @param args the command line arguments
     */
    final static
            int PORT = 1234;

    public static
            void main(String[] args)
    {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try
        {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
        while (true)
        {
            try
            {
                socket = serverSocket.accept();
            }
            catch (IOException e)
            {
                System.out.println("I/O error: " + e);
            }
            System.out.println("found");
            // new thread for a client
            new AClient(socket).start();
        }
    }

}
