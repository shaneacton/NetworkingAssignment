package chatbot.Client;

import java.util.Scanner;

/**
 * @author abrsas002
 */
public class RunClient
{
	public static void main(String[] args)
	{

		Scanner s = new Scanner(System.in);
		System.out.println("Enter Name");
		Client client = new Client(s.next());
		client.startListening();

		System.out.println("Enter receiver ID:");
		int receiverID = Integer.parseInt(s.next());

		System.out.println("Type Messages:");
		String send = s.next();

		while(!send.equals("q"))
		{//taking in messages
			client.sendToServer("message:" + client.ID + ":" + receiverID, send);

			System.out.println("Enter receiver ID:");
			receiverID = Integer.parseInt(s.next());
			System.out.println("Type Messages:");
			send = s.next();
		}
	}
}
