/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatbot;

/**
 *
 * @author actsha001
 * 
 * messages:
 * name
 */
public class Message {
    String header;
    String message;

    public Message(String lines){
        lines = lines.replaceAll(" ", "");
        header = lines.split("\n")[0];
        message = lines.split("\n")[1];
    }
    
    public String getCommand(){
        return header.split(":")[0];
    }

    @Override
    public String toString() {
        return "Message{" + "header=" + header + ", message=" + message + '}';
    }
    
    
}
