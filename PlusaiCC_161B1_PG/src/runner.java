import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.directorySelector;
import ccc.interaction.global.fileSelector;
import ccc.interaction.global.popUpBox;
import ccc.interaction.internalFeatures.Base64Encoder;
import ccc.interaction.internalFeatures.Base64EncoderStatic;
import ccc.mainComponent.UI.Explorer;
import ccc.mainComponent.UI.ProfileLoader;
import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.prefixTransformer;
import ccc.mainComponent.regconizeCore.unCoreProcessor;
import ccc.mainComponent.regconizeCore.wordsTransformer;
import ccc.mainComponent.regconizeCore.languages.engDB;
import dataInteractingAbility.ReadText;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;
import dataInteractingAbility.processesNet.ProcessesNet;
import servicePackage.stabilizer;

public class runner {
	
	public static void main(String[] args) throws IOException {
		engDB e = new engDB();
		/*System.out.println(e.startCorrection("Thoseis"));
		System.out.println(e.startCorrection("Thoseare"));
		System.out.println(e.startCorrection("Itis"));
		System.out.println(e.startCorrection("Itare"));
		System.out.println(e.startCorrection("Heare"));
		System.out.println(e.startCorrection("Sheis"));
		System.out.println(e.startCorrection("Theywill"));
		System.out.println(e.startCorrection("Ihad"));
		
		stabilizer.startlDSS();
		System.out.println(prefixTransformer.translate("thoseare"));
		System.out.println(prefixTransformer.translate("thoseada"));
		
		DEBUG.print(e.transform("I're"));
		System.out.println(e.startCorrection("I're"));*/
		//System.out.println(e.startCorrection("I'd"));
		//wordsTransformer.Transform("heis test   test");
		//wordsTransformer.Transform("Them");
		//unCoreProcessor.getInput(wordsTransformer.Transform("Them"));
		//autoNotificationPusher.notPush("INFO", "[ADMIN-BYPASS] Successfully Calculated", "ID = 4, Marine Corps and Marine Corps Reserve (36%) or\nID = 5, Navy and Navy Reserve (21%) or\nID = 1, Air Force and Air Force Reserve (43%) [USER_MENTIONED]", prefixTransformer.class.getClass(), "AUTO");
		//Explorer.start();
		
		//Base64EncoderStatic.encrypt(fileSelector.DefaultLoadFile().getAbsolutePath());
		// String en = Base64EncoderStatic.getFile();
		// String test = popUpBox.askStringBox("File Name", "Please Enter File name and Format.");
		//createFile.createFile(en,directorySelector.DirectorySelector() + "\\" + popUpBox.askStringBox("File Name", "Please Enter File name and Format."));
		 //createFile.createFile(en, "C:\\Users\\UnitS1\\Desktop\\drawing21.png");
		 //System.out.print(directorySelector.DirectorySelector() + "\\" + popUpBox.askStringBox("File Name", "Please Enter File name and Format."));

		// System.out.println(en);
		//Image image = Base64EncoderStatic.DecryptPicture(en);
		//ccc.mainComponent.UIComponents.ProfileLoader.image = image;
		
		//ProfileLoader file = new ProfileLoader();
		//file.initialize();
		 
		 //filesCore.readText("‪C:\\Users\\UnitS1\\Desktop\\testFile.txt");
		//filesCore.getAllHidden();
		//filesCore.moveEmg();
		
		//ReadText.loadAI6Data();
		
	}

}
