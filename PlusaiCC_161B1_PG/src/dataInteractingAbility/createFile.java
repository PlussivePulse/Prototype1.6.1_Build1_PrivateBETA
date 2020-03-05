package dataInteractingAbility;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.popUpBox;
import ccc.interaction.internalFeatures.Base64EncoderStatic;
import ccc.mainComponent.experimental.DEBUG;
import servicePackage.stabilizer;

public final class createFile {
	
	private static String directionFile;
	
	public static void createFile(String name, String direction) {
		System.out.println("TEST");

		if(!filesCore.checkFileExist(name)) {

			if(name.length()>4)
	
			
			CreatePicture(name, direction);
			directionFile = direction;
			
			}
	}
	
	private static void CreatePicture(String name,String direction) {
		
		if(!filesCore.checkFileExist(direction)); //protect fileDulplicate
		File newFile = new File(direction);
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeFile(newFile, direction, name);
	}
	
	private static void writeFile(File file, String direction, String name) {
		
		BufferedImage image = Base64EncoderStatic.DecryptPicture(name);
		try {
			ImageIO.write(image, direction.substring(direction.length()-3), new File(direction));
		} catch (IOException e) {

		}
	}
	
	public static void createFolder(String uri,String folderName) {
		try {
			if(!new File(uri+"\\"+folderName).exists()) {
				Files.createDirectories(Paths.get(uri+"\\"+folderName));
			}else {
				//DEBUG.print("File Directory Existed.");
				//popUpBox.alertError("File Directory Existed.", "FilesCore error :");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createFile(String destination,String name, String format) throws IOException {
		File file = new File(destination +"\\" +  name + "." + format);
		file.createNewFile();
	}
	
	
}
