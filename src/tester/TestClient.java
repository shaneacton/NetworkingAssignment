/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author abrsas002
 */
public
        class TestClient
{

    public static
            void main(String[] args)
    {
        try
        {
            Scanner s = new Scanner(System.in);
            Socket socket = new Socket("localhost", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String send = s.next();
            while (!send.equals("q"))
            {
                out.writeChars(send + "\n");
                send = s.next();
            }
            socket.close();
            out.close();
            in.close();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
