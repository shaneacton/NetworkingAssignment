package chatbot.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
	public ServerSocket serverSocket = null;

	final static int PORT = 1234;
	public List<ClientListener> clients = new ArrayList<>();

	public Server()
	{
		try
		{
			serverSocket = new ServerSocket(PORT);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void addClient(ClientListener client)
	{
		clients.add(client);
		client.start();

		// Sending clientSocket ID
		clients.get(clients.size() - 1).sendToClient("id::", clients.size() - 1 + "");
		clients.get(clients.size() - 1).id = clients.size() - 1;
	}

	public void sendClientInfo(int clientID)
	{
		// Sending clientSocket list of connected clients
		String clientNamesInfo = "";
		for (ClientListener c : clients)
		{
			clientNamesInfo += c.id + "," + c.name + ":"; // TODO sep with ':'?
		}
		clients.get(clientID).sendToClient("sndclients::", clientNamesInfo, clientID);
	}
}
