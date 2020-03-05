package ccc.interaction.internalFeatures;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ccc.interaction.internalFeatures.warningBeep.beeper;
import ccc.mainComponent.UI.mainRunnerCore;

public class serviceChecker {
	public boolean terminator = true;
	
	public serviceChecker() {
		Thread statusC = new Thread(new statusC());
		statusC.start();
	}

	public class statusC implements Runnable {

		@Override
		public void run() {
			terminator = true;
			while(terminator==true) {
				if(mouseCursor.checkAlive() == true) {
					mainRunnerCore.lblCursorstatus.setText("Online");
				}else {
					mainRunnerCore.lblCursorstatus.setText("Oflline");
				}
				
				try {Thread.sleep(5000);} 
				catch (InterruptedException e) {e.printStackTrace();}
			}
		}
		
	}

	public void stopBeep() {
		terminator = false;
	}
}