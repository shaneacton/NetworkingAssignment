/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author abrsas002
 */
public
        class AClient extends Thread
{

    protected
            Socket socket;

    public
            AClient(Socket clientSocket)
    {
        this.socket = clientSocket;
    }

    public
            void run()
    {
        InputStream inp = null;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        try
        {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e)
        {
            return;
        }

        String line;
        while (true)
        {
            try
            {
                line = brinp.readLine();
                if (line.equals("q"))
                {
                    socket.close();
                    return;
                }
                else if (line != null)
                {
                    System.out.println(line + " this is the line");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return;
            }
        }
    }
}
