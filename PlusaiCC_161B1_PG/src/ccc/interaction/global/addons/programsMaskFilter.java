package ccc.interaction.global.addons;

import java.io.IOException;
import java.util.ArrayList;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.popUpBox;
import ccc.interaction.internalFeatures.processControl;
import dataInteractingAbility.processesNet.ProcessesNet;

public class programsMaskFilter implements Runnable{
	private static boolean terminator;
	private static boolean waiting;
	
	public static void main(String args) {
		start();
	}
	
	public programsMaskFilter() {
		start();
	}
	
	public static void start() {
		Thread mask = new Thread() {
			
			private boolean founded;
			private String startoption = "yes"; //default
			

			public void run() {
				
				String getChoice = processControl.boxLoad();
				
				while(terminator == false) {
					
					ArrayList processList = null;
					try {
						processList = ProcessesNet.getProcess();
					} catch (IOException e) {
						autoNotificationPusher.notPush("ERROR", "", "IOException", Class.class.getClass(), "AUTO");
						e.printStackTrace();
					}
					
					if(processList!=null) {
						for(int i = 0; i<processList.size(); i ++) {
							if(processList.get(i).toString().contains(getChoice) && founded != true && waiting == false) {
								founded = true;
								startoption = popUpBox.yesnocancel("Start Now?");
								System.out.println(startoption);
								if(startoption.equals("yes")) {
									
								}else {
									founded = false;
								}
								//popUpBox.infoBox("FOUNDED", Class.class, "TEST");
							}
						}
					}else {
						popUpBox.alertError("Internal Error : processList = null", "processMaskFilter - processNet error.");
					}
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(startoption.equals("no")) {
						waiting = true;
					}
					
					founded = false;
				}
			}
		};
		mask.run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
