/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

import java.util.Scanner;

/**
 *
 * @author abrsas002
 */
public class RunClient {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter Name");
        Client client = new Client(s.next());

        System.out.println("Type Messages:");
        String send = s.next();
            while (!send.equals("q")) {//taking in messages
                client.sendToServer("message:", send);
                send = s.next();
            }
    }
}
