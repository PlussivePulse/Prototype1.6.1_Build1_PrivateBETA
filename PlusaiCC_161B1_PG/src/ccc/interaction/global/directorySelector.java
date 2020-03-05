package ccc.interaction.global;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class directorySelector {
	
	private static String directory;
	private static String targetDirectory;
	
	public static String DirectorySelector() {
		final JFileChooser f;
		f = new JFileChooser();
		f.setCurrentDirectory(new java.io.File("."));
		f.setDialogTitle("Directory Selector");
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setAcceptAllFileFilterUsed(false);
		
		if(f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			File directory = f.getSelectedFile();
			targetDirectory = directory.getAbsolutePath();
		}
		
		if(targetDirectory == null || targetDirectory.equals("")) {
			if (directory.isEmpty()) {
				
			}
			if (directory.trim().isEmpty()) {
				
			}
		}
		String send = targetDirectory;
		
		targetDirectory = "";
		directory = "";

		return send;
	}

}
