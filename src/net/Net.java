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
public class Net {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Server s = new Server(4800);
        Client c = new Client();
        
        s.listen(1);
        s.makeStreams();
        c.sendData("test");
        
        
        System.out.println(s.in.readChar());
    }

}
