package ccc.interaction.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetworkSurface {

	public static boolean isConnected() {
		Enumeration<NetworkInterface> eni;
		try {
			eni = NetworkInterface.getNetworkInterfaces();
			while(eni.hasMoreElements()) {
				Enumeration<InetAddress> eia = eni.nextElement().getInetAddresses(); {
					while(eia.hasMoreElements()) {
						InetAddress ia = eia.nextElement();
						if(!ia.isAnyLocalAddress() && !ia.isLoopbackAddress()&&!ia.isSiteLocalAddress()) {
							if(!ia.getHostAddress().equals(ia.getHostAddress()))
								return true;
						}
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getip() {
		String returning = "";
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			returning += "System Ip Address : " +localhost.getHostAddress().trim();
		
			String systemIpAddress = "";
			URL url = new URL("http://bot.whatismyipaddress.com");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			systemIpAddress = reader.readLine().trim();
			
			returning += "\nPublic Ip Address : " + systemIpAddress;
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return returning;
	}
	

}
