package ccc.interaction.global;

import java.io.File;

import javax.swing.JFileChooser;

import ccc.mainComponent.regconizeCore.prefixTransformer;
import servicePackage.stabilizer;

public class fileSelector {
	
	public static boolean chooseAgain = false;
	public static File DefaultLoadFile() {
		final JFileChooser f = new JFileChooser();
		File selectedFile = null;
		int selected = f.showOpenDialog(null);
		
		try {
			if(selected == JFileChooser.APPROVE_OPTION) {
				selectedFile = f.getSelectedFile();
			}}
		catch(NullPointerException e) {
			
		}
		
		
			if(selectedFile == null && chooseAgain == true) {
				//autoNotificationPusher.notPush("WARNING", "Stabilizer - Unstable Environment Detected", "You have cancelled, FileChooser selected can't be null.", prefixTransformer.class.getClass(), "TRAY");
				popUpBox.alertError("You have cancelled, FileChooser selected can't be null.", "WARNING");
			}
		
		try {
			if(selectedFile == null && chooseAgain == false) {
				if(stabilizer.getASValue()==true) {
					//autoNotificationPusher.notPush("WARNING", "Stabilizer", "The value is null, you might did not chose the file.\nAutomatic handler will take you back to file chooser again.", prefixTransformer.class.getClass(), "AUTO");
					chooseAgain = true;
					DefaultLoadFile();
				}
			}}
		catch(NullPointerException e) {
			
		}
		

		return selectedFile;
	}
	
	public static void deadEnd() {
		
	}
	
	


}
