package ccc.mainComponent.UI;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class serverModule {

	JFrame frmServerVersion;
	private JTextArea serverDisplayer;
	private ArrayList clientOutputStreams;
	private JTextField serverIPDisplay;
	private ArrayList users;
	public static boolean terminator = true;
	private JTextField portIP;
	private ServerSocket serverSocket;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverModule window = new serverModule();
					window.frmServerVersion.setVisible(true);
					window.frmServerVersion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public serverModule() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServerVersion = new JFrame();
		frmServerVersion.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				try {
					if(serverSocket != null)
					serverSocket.close();
				} catch (IOException e1) {
				}

				
			}
		});
		frmServerVersion.setTitle("Server Version 1.1");
		frmServerVersion.setBounds(100, 100, 450, 500);
		//frmServerVersion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServerVersion.getContentPane().setLayout(null);
		
		JLabel lblServerPanel = new JLabel("Server Panel : Version 1.1\r\n");
		lblServerPanel.setBounds(252, 436, 172, 14);
		frmServerVersion.getContentPane().add(lblServerPanel);
		
		JTabbedPane serverPane = new JTabbedPane(JTabbedPane.TOP);
		serverPane.setBounds(10, 101, 414, 324);
		frmServerVersion.getContentPane().add(serverPane);
		
		JPanel MainPage = new JPanel();
		serverPane.addTab("Main", null, MainPage, null);
		MainPage.setLayout(null);
		
		serverDisplayer = new JTextArea();
		serverDisplayer.setEditable(false);
		serverDisplayer.setBounds(0, 0, 414, 315);
		MainPage.add(serverDisplayer);
		
		JPanel Setting = new JPanel();
		serverPane.addTab("Control Panel", null, Setting, null);
		Setting.setLayout(null);
		
		JPanel updateList = new JPanel();
		serverPane.addTab("Update List", null, updateList, null);
		updateList.setLayout(null);
		
		JLabel lblUpdate = new JLabel("Update 1.1 :");
		lblUpdate.setBounds(10, 11, 167, 14);
		updateList.add(lblUpdate);
		
		JLabel lblUpdatedCode = new JLabel("- Updated Code that allow force kick.");
		lblUpdatedCode.setBounds(20, 31, 212, 14);
		updateList.add(lblUpdatedCode);
		
		JLabel lbladdedPortId = new JLabel("+ Added Port ID display.");
		lbladdedPortId.setBounds(20, 46, 263, 14);
		updateList.add(lbladdedPortId);
		
		JLabel lblIpAddress = new JLabel("IP Address : ");
		lblIpAddress.setBounds(10, 18, 136, 14);
		frmServerVersion.getContentPane().add(lblIpAddress);
		
		serverIPDisplay = new JTextField();
		serverIPDisplay.setEditable(true);
		serverIPDisplay.setBounds(92, 15, 128, 20);
		frmServerVersion.getContentPane().add(serverIPDisplay);
		serverIPDisplay.setColumns(10);
		try {
			serverIPDisplay.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton btnStart = new JButton("Start Server");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread start = new Thread(new ServerModule());
				start.start();
				serverDisplayer.append("Starting Server... \n");
			}
		});
		btnStart.setBounds(252, 14, 172, 23);
		frmServerVersion.getContentPane().add(btnStart);
		
		portIP = new JTextField();
		portIP.setEditable(false);
		portIP.setBounds(92, 43, 128, 20);
		frmServerVersion.getContentPane().add(portIP);
		portIP.setColumns(10);
		
		JLabel lblPortId = new JLabel("Port ID :");
		lblPortId.setBounds(10, 46, 136, 14);
		frmServerVersion.getContentPane().add(lblPortId);
	}
	
	public class ServerModule implements Runnable{

		


		private int maxPort = 100;
		private String ipAddress;

		@Override
		public void run() {
			clientOutputStreams = new ArrayList();
			users = new ArrayList();
			
			try
			{
				try{
					ipAddress = serverIPDisplay.getText();
					serverSocket = new ServerSocket(Integer.valueOf(mainRunnerCore.serverCOMipPortConfig.getText()),maxPort,InetAddress.getByName(ipAddress));
					portIP.setText(mainRunnerCore.serverCOMipPortConfig.getText());
				}catch(Exception ex) {
					serverSocket = new ServerSocket(0001);
					portIP.setText("0001");
				}
			
				
				while(terminator == true){
					Socket clientSock = serverSocket.accept();
					PrintWriter sWriter = new PrintWriter(clientSock.getOutputStream());
					clientOutputStreams.add(sWriter);
					
					Thread serverListener = new Thread(new ClientHandler(clientSock, sWriter));
					serverListener.start();
					serverDisplayer.append("Server established connection. \n");
				}
			}catch (Exception ex) {
				serverDisplayer.append("Server couldn't start. Port may already be in used.\n");
			}
		}
		
	}
	
	public class ClientHandler implements Runnable{
		
		BufferedReader cHReader;
		Socket cHSocket;
		PrintWriter chClient;
		
		public ClientHandler (Socket clientSocket, PrintWriter user) {
			chClient = user;
			try {
				cHSocket = clientSocket;
				InputStreamReader reader = new InputStreamReader(cHSocket.getInputStream());
				cHReader = new BufferedReader(reader);
			}catch(Exception ex) {
				
			}
		}

		@Override
		public void run() {
			String message;
			String[] data;
			
			try {
				while ((message = cHReader.readLine()) != null) {
					data = message.split(":");
					serverDisplayer.append("Recieved : " + data[1] + "Code : " + data[2] + "\n");
					
					for (String text:data) {
						serverDisplayer.append(text);
					}
					serverDisplayer.append("\n");
					
					if(data[2].equals("Connect")) {
						announce(data[0] + " : " + data[1] + " :Chat");
						userAdd(data[0]);
					}else if(data[2].equals("Disconnect")) {
						announce(data[0] + ": has disconnected." + ":" + "Chat");
						userRemove(data[0]);
					}else if(data[2].equals("Chat")) {
						announce(message);
					}else if(data[2].equals("Command")) {
						if((mainRunnerCore.chckbxAllowCommand).isSelected() == true) {
							announce(message);
						}else {
							announce("Server remote command is disabled.");
						}
						
					}else if(data[2].equals("ForceDisconect")) {
						if(data[0].contains("admin"))
						announce(data[0] + " : " + data[1] + " :ForceDisconect");
					}
					else {
						serverDisplayer.append("Error - Unexpected condition found. Security alert!" + "\n");
					}
				}
			}catch(Exception ex) {
				serverDisplayer.append("Lost a Connection" + "\n");
				ex.printStackTrace();
				clientOutputStreams.remove(chClient);
			}
			
		}

		private void userAdd(String data) {
			String message;
			String name = data;
			users.add(name);
			String[] tempList = new String[(users.size())];
			users.toArray(tempList);
			
			for (String text:tempList) {
				//message = (text + " : :Connect");
				//announce(message);
			}
			//announce("Server : :Done");
			
		}

		private void userRemove(String string) {

		}

		private void announce(String message) {
			Iterator iteratorObj = clientOutputStreams.iterator();
			
			while (iteratorObj.hasNext()) {
				try {
					PrintWriter writer = (PrintWriter) iteratorObj.next();
					writer.println(message);
					//serverDisplayer.append("[Server] Sending : " + message + "\n");
					writer.flush();
					serverDisplayer.setCaretPosition(serverDisplayer.getDocument().getLength());
			}catch (Exception ex){
				serverDisplayer.append("[Server] Error : Couldn't send requested the message" + "\n");
			}
				
			}
		}
	}
}

