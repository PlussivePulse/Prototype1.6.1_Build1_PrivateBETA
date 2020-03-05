package ccc.interaction.internalFeatures;

import java.util.ArrayList;

import ccc.interaction.global.addons.weatherThread;
import ccc.interaction.internalFeatures.alarmClockBeep.beeper;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.experimental.DEBUG;

public class refresherService implements Runnable{
	
		private boolean terminator = true;

		@Override
		public void run() {
			while(terminator) {
				DEBUG.print("refresherService refreshing...");
				moonPhaseUpdater.setMoonPhase();
				if(weatherThread.weatherCon.equals("Clear (Night)")) {
					  mainRunnerCore.weatherM.setBounds(100, 11, 218, 218);
					  mainRunnerCore.moonPhase.setBounds(156, 12, 218, 218);
					  moonPhaseUpdater.confirmImage(1);
				}else {
					  mainRunnerCore.moonPhase.setBounds(202, 11, 218, 218);
					  mainRunnerCore.weatherM.setBounds(10, 11, 218, 218);
					  //mainRunnerCore.weatherM.setBounds(10, 11, 218, 218);
					  moonPhaseUpdater.confirmImage(2);
					  //moonPhaseUpdater.resetImage();
				}
				
				moonPhaseUpdater.setPhaseDRemaining();
					try {
						Thread.sleep(600000); //10 MINUTES REFRESH
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		

}

