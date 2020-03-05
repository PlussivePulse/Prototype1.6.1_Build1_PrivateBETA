package dataInteractingAbility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ccc.interaction.global.directorySelector;
import ccc.interaction.global.fileSelector;
import ccc.interaction.global.popUpBox;
import ccc.mainComponent.UIComponents.restartApplication;
import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.AI6DATAUpdater;
import ccc.mainComponent.regconizeCore.AI6DataHolder;


public class ReadText {
	private static boolean setEmptyDelete = true;
	private static boolean founded = false;
	public static String getPlainText() {
		File chooser = new File(fileSelector.DefaultLoadFile().getAbsolutePath());
		String text = "";
		
		try {
			text = new String(Files.readAllBytes(Paths.get(chooser.getAbsolutePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return text;
	}
	
	
	public static String[][] defaultloadsettingFormat(String data, String splitData) {
		String[][] holder = new String[data.split("\n").length][2];
		String[] holderA = (data.split("\n"));
		String[] holderB;
		for(int i = 0; i<holder.length; i++) {
			if(!holderA[i].contains("]")) {
				holderB = (holderA[i].split(splitData));
			}else {
				String tempSave = splitData;
				splitData = "\n";
				holderB = (holderA[i].split(splitData));
				splitData = tempSave;
			}
			holder [i][0] = holderB[0];
			if(holderB.length==2) {
				holder [i][1] = holderB[1];
			}
		}
		return holder;
	}
	
	public static String loadAllText(String location) {
		File file = new File(location);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String st;
		String allText = "";
		
		try {
			while ((st = br.readLine()) != null) {
				if(setEmptyDelete == true) {
					if(!st.equals("")) {
						allText += st+"\n";
					}else {
						//none
					}
				}else {
					allText += st+"\n";
				}

				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allText;
	}
}
