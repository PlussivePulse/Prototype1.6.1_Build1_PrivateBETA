package ccc.mainComponent.UIComponents;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.internalFeatures.soundBoard;
import ccc.mainComponent.UI.mainRunnerCore;
import servicePackage.stabilizer;

public class restartApplication {
	
	public restartApplication(){
		Thread restart = new Thread(new restart());
		restart.start();
	}
	
	public class restart implements Runnable {

		private boolean terminator = true;
		String javaBin = null;
		File currentJar = null;
		private boolean restartAttempted = false;

		@Override
		public void run() {
			//while(terminator ==true) {
			for(int i = 0; i<=4; i++) {
					if(i==0) {
						soundBoard.muted = false;
						Color background = new Color(255,169,81);
						mainRunnerCore.displayer.setBackground(background);
						mainRunnerCore.displayer.setForeground(Color.white);
						stabilizer.setStabilizer(false);
						autoNotificationPusher.notPush("WARNING", "Attention!", "System is restarting", Class.class, "AUTO");
						mainRunnerCore.displayer.append("\nRestarting in progress... (0/3)");
					}else if(i==1) {
						mainRunnerCore.displayer.append("\nRestarting in progress... (1/3)");
						   javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
						   currentJar= null;
						try {
							currentJar = new File(restartApplication.class.getProtectionDomain().getCodeSource().getLocation().toURI());
						} catch (URISyntaxException e) {
							e.printStackTrace();
						}
					}else if(i==2) {
						/* is it a jar file? */
						   if(!currentJar.getName().endsWith(".jar")) {
							   mainRunnerCore.displayer.append("\nRestarting failed.");
						     return;
						   }else {
							   mainRunnerCore.displayer.append("\nRestarting in progress... (2/3)");
						   }
					}else if(i==3) {
						   /* Build command: java -jar application.jar */
						   final ArrayList<String> command = new ArrayList<String>();
						   command.add(javaBin);
						   command.add("-jar");
						   command.add(currentJar.getPath());
			
						   final ProcessBuilder builder = new ProcessBuilder(command);
						   try {
							builder.start();
						} catch (IOException e) {
							e.printStackTrace();
						}
						   mainRunnerCore.displayer.append("\nRestarting in progress... (3/3)");
					}else if(i==4) {
						System.exit(0);
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
  
			// }
		}
			
	}
}	 
