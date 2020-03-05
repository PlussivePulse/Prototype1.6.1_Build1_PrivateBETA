package ccc.interaction.global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ccc.mainComponent.experimental.DEBUG;

public class cmdExecute {
	
	private String command = "";
	private static Runtime runTime;
	
	private static Process staticProcess;
	
	public void setCommand (String command) {
		this.command = command;
	}
	
	public static void execute(String command) {
		
		Process process = null;
		runTime = Runtime.getRuntime();
		try {
			process = runTime.exec(command);
		} catch (IOException e) {
			popUpBox.alertError(e.toString(), "Error");
			e.printStackTrace();
		}
		if(process == null) {
		}
		BufferedReader reader = new BufferedReader( new InputStreamReader (process.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader (process.getErrorStream()));
	}
	
	public static String executeCheck(String command) {
		
		Process process = null;
		runTime = Runtime.getRuntime();
		try {
			process = runTime.exec(command);
		} catch (IOException e) {
			popUpBox.alertError(e.toString(), "Error");
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader( new InputStreamReader (process.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader (process.getErrorStream()));
		String returning = "";
		String store = "";
		try {
			while ((returning = reader.readLine()) != null){
				store += returning;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while ((returning = stdError.readLine()) != null){
				store += returning+"";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return store;
	}
	
	
	
	public void customExecute() { //experiment
		
		staticProcess = null;
		runTime = Runtime.getRuntime();
		
		String command = "";
		while (!command.equals("end")) {
			
		}
	}
}
