package chatbot.Server;

import chatbot.Message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author abrsas002
 */
public class ClientListener extends Thread
{

	protected Socket clientSocket;
	Server server;

	InputStream inp = null;
	BufferedReader brinp = null;
	DataOutputStream out = null;

	String name;
	int id;

	public ClientListener(Socket clientSocket, Server server)
	{
		this.clientSocket = clientSocket;
		this.server = server;

		try
		{
			inp = this.clientSocket.getInputStream();
			brinp = new BufferedReader(new InputStreamReader(inp));
			out = new DataOutputStream(this.clientSocket.getOutputStream());
			//System.out.println("out is not null");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		while(true)
		{
			//forever read messages from the server
			Message message = receiveFromClient();

			if (message.getCommand().equals("quit"))
			{
				try
				{
					clientSocket.close();
					System.out.println("closing clientSocket");
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
				return;
			}
			else if (message.getCommand().equals("name"))
			{
				System.out.println("SERVER: found new client conection: '" + message.message + "'");
				name = message.message;
			}
			else if (message.getCommand().equals("message"))
			{
				sendToClient("message:" + message.getArg(1), message.message, Integer.parseInt(message.getArg(2)));
				System.out.println("from " + name + " to SERVER: '" + message.message + "'");
			}
			else if (message.getCommand().equals("reqclients"))
			{
				System.out.println("sending client info to: ");
				server.sendClientInfo(id);
			}
		}
	}

	public Message receiveFromClient()
	{
		String line;

		try
		{
			line = brinp.readLine() + "\n";
			line += brinp.readLine();

			return new Message(line);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new Message("q\n\n");
		}
	}

	public void sendToClient(String header, String message)
	{
		try
		{
			//System.out.println("sending message to client " + header + " " + message);
			out.writeChars(header + "\n" + message + "\n");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public void sendToClient(String header, String message, int clientID)
	{
		try
		{
			//System.out.println("sending message to client " + header + " " + message);
			//System.out.println("is out null? "+out == null);
			server.clients.get(clientID).out.writeChars(header + "\n" + message + "\n");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
