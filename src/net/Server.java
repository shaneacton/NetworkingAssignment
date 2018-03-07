/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author abrsas002
 */
public class Server {

    ServerSocket ss;
    Socket socket;

    DataInputStream in;
    DataOutputStream out;

    public Server(int port) throws IOException {
        ss = new ServerSocket(port);
    }

    public String getMessage() {
        String message = "";
        try {
            while (true) {
                message += in.readChar();
            }
        } catch (EOFException ex) {
            return message;
        }
        catch (IOException ex) // TODO could be an issue
        {
            return message;
        }
//        finally
//        {
//            return message;
//        }
    }

    public void listen(int port) throws IOException {
        socket = ss.accept();
    }

    public void makeStreams() throws IOException {
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }
}
