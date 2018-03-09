package gui_design;

import chatbot.Client.*;
import chatbot.Message;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClientGUI
{

    public static void main(String[] args)
    {
        String name = JOptionPane.showInputDialog("Enter name");
        Client newClient = new Client(name);
        
        GUI gui = new GUI();
        gui.setVisible(true);
        
    }

}
