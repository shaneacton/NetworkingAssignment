package chatbot.Server;

import java.io.IOException;
import java.net.Socket;

/**
 * @author abrsas002
 */
public class RunServer
{


	public static void main(String[] args)
	{
		Server server;

		Socket newClientSocket = null;
		server = new Server();
		while(true)
		{
			try
			{
				newClientSocket = server.serverSocket.accept(); //listening for new clientSocket connection
			}
			catch (IOException e)
			{
				System.out.println("I/O error: " + e);
			}
			// new thread for a new clientSocket
			System.out.println("Adding clientSocket");
			server.addClient(new ClientListener(newClientSocket, server));
			System.out.println(server.clients.size());
		}
	}

}
