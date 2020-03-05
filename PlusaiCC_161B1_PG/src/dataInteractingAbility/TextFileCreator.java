package dataInteractingAbility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ccc.interaction.global.directorySelector;
import ccc.interaction.global.popUpBox;

public class TextFileCreator {
	public static void createTxtFile(String txt) {
		File newTxtFile = new File(directorySelector.DirectorySelector() + "\\" + popUpBox.askStringBox("File Name", "Please Enter File name and Format."));
		try {
			FileWriter fw = new FileWriter(newTxtFile);
			fw.write(txt);
			fw.close();
		} catch (IOException e) {e.printStackTrace();}
		
	}
}
