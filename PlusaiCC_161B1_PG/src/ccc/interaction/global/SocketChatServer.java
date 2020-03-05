package ccc.interaction.global;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SocketChatServer {

	private JFrame frame;
	ArrayList clientOutputStream;
	ArrayList<String> users;
	private JTextArea serverChatArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SocketChatServer window = new SocketChatServer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SocketChatServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		serverChatArea = new JTextArea();
		serverChatArea.setBounds(10, 100, 664, 290);
		frame.getContentPane().add(serverChatArea);
	}

	public class ClientHandler implements Runnable {

		BufferedReader reader;
		Socket socket;
		PrintWriter client;
		
		public ClientHandler(Socket clientSocket, PrintWriter user) {
			client = user;
			try {
				socket = clientSocket;
				InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
				reader = new BufferedReader(isReader);
			}catch(Exception e) {
				popUpBox.alertError("Unknown Error", "Exception Error");
			}
		}
		@Override
		public void run() {
			String message;
			String[] recieved;
			try {
				while((message = reader.readLine()) != null) {
					serverChatArea.append("Recieved : " + message + "\n");
					recieved = message.split(":");
					
					for(String stackMessage:recieved) {
						serverChatArea.append(stackMessage + "\n");
					}
					
					if(recieved[2].equals("Connect")) {
						announce(recieved[0] + ":" + recieved[1] + ":Chat");
						addUser(recieved[0]);
					}else if(recieved[2].equals("Disconnect")) {
						announce(recieved[0] + ":has disconnected." + ":Chat");
						removeUser(recieved[0]);
					}else if(recieved[2].equals("Chat")) {
						announce(message);
					}else {
						popUpBox.alertError("Unknown Error - IfElse Statement no match condition.", "Unable to push message : ");
					}
					
				}
			}catch(Exception e) {
				serverChatArea.append("Lost Connection.");
				clientOutputStream.remove(client);
			}
			
		}
		private void addUser(String name) {
			String message;
			users.add(name);
			String[] tempList = new String[(users.size())];
			users.toArray(tempList);
			
			for(String holder:tempList) {
				message = (holder + " : :Connect");
				announce(message);
			}
			announce("Server : :Done");
		}
		
		private void removeUser(String name) {
			String message;
			users.remove(name);
			String[] tempList = new String[(users.size())];
			users.toArray(tempList);
			
			for(String holder:tempList) {
				message = (holder + " : :Connect");
				announce(message);
			}
			announce("Server : :Done");
		}
		
		private void announce(String string) {
			Iterator t = clientOutputStream.iterator();
			while(t.hasNext()) {
				try {
					PrintWriter writer = (PrintWriter) t.next();
					writer.println(string);
					serverChatArea.append("Sending: " + string + "\n");
					writer.flush();
					serverChatArea.setCaretPosition(serverChatArea.getDocument().getLength());
				}catch(Exception e){
					popUpBox.alertError("Unable to announce", "AddUser - Announce Pipeline error");
				}
			}
			
		}
		
	}
}
