package ccc.mainComponent;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketCore extends Thread{
	public ObjectOutputStream COMoutput;
	public ObjectInputStream COMinput;
	public Socket COMSock;
	public static int comPort = 255;
	public ServerSocket COMserver;
	public static String getlocalIP() {
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String send = ip.getHostAddress();
		return send;
	}
	
	
}

		
		
	

