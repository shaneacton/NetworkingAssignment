/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author abrsas002
 */
public class Client {

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    public Client() throws IOException
    {
        socket = new Socket("localhost", 4800);
        //socket.connect(4800);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }
    
    public void sendData(String data) throws IOException
    {
        out.writeChars(data);
    }
}
