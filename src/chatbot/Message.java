package chatbot;

/**
 * @author actsha001
 *         <p>
 *         messages:
 *         name:: -> clientSocket tells server what its name is
 *         id:: -> server tells clientSocket its id
 *         quit:: -> clientSocket tells server it is disconnecting
 *         test:: -> /for test messages
 *         message:senderID:receiverID -> for sending message clientSocket to server
 *         message:senderID -> for sending message server to clientSocket
 *         gmessage:senderid:groupID -> sending message to server with the intent of it being sent to a group
 *         sndclients:: -> server sends clientSocket a list of connected clientSocket name ID pairs separated by a ',' and each
 *                         separated by a ':'
 *         reqclients:: -> clientSocket requests list of connected clientSocket names and IDs from server
 *         <p>
 *
 *         idea use the req keyword to request and snd to send:
 *         req/snd::
 *         clients/id
 */
public class Message
{
	public String header;
	public String message;

	public Message(String lines)
	{
		lines = lines.replaceAll(" ", "");
		header = lines.split("\n")[0];
		message = lines.split("\n")[1];
	}

	public String getCommand()
	{
		return header.split(":")[0];
	}

	public String getArg(int argNo)
	{
		return header.split(":")[argNo];
	}

	@Override
	public String toString()
	{
		return "Message{" + "header=" + header + ", message=" + message + '}';
	}
}
