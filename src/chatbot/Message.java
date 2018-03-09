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
 * name//client tells server what its name is
 * id//server tells client its id
 * quit//client tells server it is disconnecting
 * test//for test messages
 * message:senderID:receiverID//for sending message client to server
 * message:senderID//for sending message server to client
 * gmessage:senderid:groupID//sending message to server with the intent of it being sent to a group
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
    
    public String getArg(int argNo){
        return header.split(":")[argNo];
    }

    @Override
    public String toString() {
        return "Message{" + "header=" + header + ", message=" + message + '}';
    }
    
    
}
