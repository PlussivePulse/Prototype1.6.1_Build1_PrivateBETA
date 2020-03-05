package ccc.interaction.internalFeatures;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ccc.mainComponent.UI.alarmPanel;

public class warningBeep{
	
	public boolean terminator = true;
	public static int store = 0;
	
	public warningBeep(int stores) {
		store = stores;
		Thread beep = new Thread(new beeper());
		beep.start();
	}

	public class beeper implements Runnable {

		@Override
		public void run() {
			int i = 1;
			terminator = true;
			while(terminator==true) {
				
					URL sound = this.getClass().getClassLoader().getResource("soundLibraryFiles/warningSound.wav");
					try {
						AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
						Clip clip = AudioSystem.getClip();
						try {Thread.sleep(1000);} 
						catch (InterruptedException e) {e.printStackTrace();}
						clip.open(audio);
						clip.start();
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					}
				
					
				if(i==store){stopBeep();}
				
				try {Thread.sleep(1000);} 
				catch (InterruptedException e) {e.printStackTrace();}
				if(store != -1)	i++;
			}
		}
		
	}

	public void stopBeep() {
		terminator = false;
	}
}
