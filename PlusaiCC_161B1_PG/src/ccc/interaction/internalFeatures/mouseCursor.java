package ccc.interaction.internalFeatures;

import java.awt.Cursor;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ccc.interaction.global.popUpBox;
import ccc.interaction.internalFeatures.warningBeep.beeper;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.experimental.DEBUG;

public class mouseCursor {
	public boolean terminator = true;
	public Cursor c;
	public static String store = "default";
	public static Thread mc;
	
	public mouseCursor() {
		mc = new Thread(new mc());
		mc.start();
	}

	public class mc implements Runnable {

		@Override
		public void run() {
			terminator = true;
			while(terminator==true) {
				if(store.equals("hand")) {
					c = new Cursor(Cursor.HAND_CURSOR);
					mainRunnerCore.frmAiProject.setCursor(c);
				}
				
				if(store.equals("wait")) {
					c = new Cursor(Cursor.WAIT_CURSOR);
					mainRunnerCore.frmAiProject.setCursor(c);
				}
				
				if(store.equals("default")) {
					c = new Cursor(Cursor.DEFAULT_CURSOR);
					mainRunnerCore.frmAiProject.setCursor(c);
				}
				
				try {Thread.sleep(250);} 
				catch (InterruptedException e) {e.printStackTrace();}
			}
		}
		
	}
	
	public static boolean checkAlive() {
		return mc.isAlive();
	}
}
