package ccc.interaction.internalFeatures;

import ccc.mainComponent.UI.alarmPanel;

public class alarmClockBeep{
	
	public static boolean terminator = true;
	
	public alarmClockBeep(String string) {
		alarmPanel p = new alarmPanel(string);
		p.frame.setVisible(true);
		Thread beep = new Thread(new beeper());
		beep.start();
	}
	
	public class beeper implements Runnable {

		@Override
		public void run() {
			
			while(terminator==true) {
				for(int i = 0; i < 3; i++) {
					soundBoard.generateBeepSound(1000, 100, 1);
					
					try {Thread.sleep(5);} 
					catch (InterruptedException e) {e.printStackTrace();}
					
				}
				soundBoard.generateBeepSound(1000, 100, 1);
				try {Thread.sleep(800);} 
				catch (InterruptedException e) {e.printStackTrace();}
			}
		}
		
	}

	public void stopBeep() {
		terminator = false;
	}
}
