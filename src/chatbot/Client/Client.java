package chatbot.Client;

import chatbot.Message;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author laptop
 */
public class Client
{

	Socket socket;
	DataInputStream in;
	BufferedReader brinp = null;

	DataOutputStream out;

	public String name;
	int ID;

	// Maps ID to name
	public Map<Integer, String> knownClients;
	public boolean recievedClients;

	private Thread serverListener;

	public Client(String name)
	{
		knownClients = new HashMap<>();
		this.name = name;
		this.recievedClients = false;

		try
		{
			socket = new Socket("localhost", 1234);
			in = new DataInputStream(socket.getInputStream());
			brinp = new BufferedReader(new InputStreamReader(in));

			out = new DataOutputStream(socket.getOutputStream());

			sendToServer("name", name);
		}
		catch (IOException ex)
		{
		}

		serverListener = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while(true)
				{
					Message message = receiveFromServer();
					System.out.println("received message " + message);
					if (message.getCommand().equals("id"))
					{
						ID = Integer.parseInt(message.message);
						System.out.println("CLIENT " + name + " has been given id " + ID);
					}
					else if (message.getCommand().equals("test"))
					{
						System.out.println(message);
					}
					else if (message.getCommand().equals("message"))
					{
						System.out.println("message from " + message.getArg(1) + ": " + message.message);
					}
					else if (message.getCommand().equals("sndclients"))
					{
						setKnownClients(message.message);
						recievedClients = true;
					}
				}
			}
		});

		// Requesting initial info
		sendToServer("reqclients::", " ");
	}

	/**
	 * Makes the clientSocket start listening for incoming messages
	 */
	public void startListening()
	{
		/*
		 This is not in the constructor because starting a thread in the constructor is:
		 giving out a reference to your object before it is fully constructed.
		*/
		serverListener.start();
	}

	public void sendToServer(String header, String message)
	{
		try
		{
			out.writeChars(header + "\n" + message + "\n");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private Message receiveFromServer()
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

	private void setKnownClients(String clients)
	{
		knownClients.clear();
		String[] clientsInfo = clients.split(":");
		for (String client : clientsInfo)
		{
			knownClients.put(Integer.parseInt(client.split(",")[0]), client.split(",")[1]);
		}
	}

	void closeClient()
	{
		try
		{
			socket.close();
			out.close();
			in.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

}
