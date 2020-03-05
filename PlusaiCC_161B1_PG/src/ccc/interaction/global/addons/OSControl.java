package ccc.interaction.global.addons;

import ccc.interaction.global.cmdExecute;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.experimental.DEBUG;

public class OSControl {
	public OSControl() {
		Thread osConTick = new Thread() {
			public void run() {
				DEBUG.print("test");
				osControl();
			}
		};
		osConTick.start();
	}
	
	public static boolean terminator = true;
	
	
	public static void osControl() {
		
				while(terminator==true) {
				//	if(mainRunnerCore.noShutdown==true) {
					//	cmdExecute.execute("shutdown -a");
				//	}
					
					try {Thread.sleep(250);} 
					catch (InterruptedException e) {e.printStackTrace();}
				}
	}
}
