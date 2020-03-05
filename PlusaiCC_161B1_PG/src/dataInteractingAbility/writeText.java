package dataInteractingAbility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ccc.mainComponent.experimental.DEBUG;

public class writeText {
	public static void Write(String string, String target) throws IOException {
		FileWriter writer = new FileWriter(target,true);
		writer.write(string);
		writer.close();
	}
	
	public static void addText(String string, String target, String targetWord) throws IOException {
		Path path = Paths.get(target);
		File target2 = new File(target);
	
		
		File text = new File(target2.getParent()+"\\AI6DataTemp.txt");
		DEBUG.print(text.getAbsolutePath());
		Path path2 = Paths.get(text.getParent());
		
		FileWriter writer = new FileWriter(text,true);
		String holder = ReadText.loadAllText(target);
		Files.delete(path);
		createFile.createFile(text.getParent(), "AI6Data", "txt");
		String[] logicSeperator = holder.split("\n");
		for (int i = 0; i<logicSeperator.length; i++) {
			if(targetWord.equals(logicSeperator[i])) {
				writer.write(logicSeperator[i]+"\n");
				writer.write(string+"\n");
			}else {
				writer.write(logicSeperator[i]+"\n");
			}
		}
		writer.close();
		//try {
		//Files.delete(Paths.get(new File(target2.getParent()+"\\AI6DataTemp.txt").getAbsolutePath()));
		//}catch(Exception ex){}
		//Files.move(path2, path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		//Files.delete(path);
	}
	
	public static void update(String target, String toTarget) {
		Path path2 = Paths.get(new File(target).getAbsolutePath());
		Path path = Paths.get(new File(toTarget).getAbsolutePath());
		try {
			Files.move(path2, path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			DEBUG.print("No file needed to be update.");
		}
	}

	public static void upload1() {
		File locate = null;
		try {
			locate = new File(filesCore.getDir());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writeText.update(locate.getParentFile()+"\\AI6CoreFolder\\AI6DataTemp.txt", locate.getParentFile()+"\\AI6CoreFolder\\AI6Data.txt");
	}
	
	public static void removeText(String target, String targetWord) throws IOException {
		Path path = Paths.get(target);
		File target2 = new File(target);
	
		
		File text = new File(target2.getParent()+"\\AI6DataTemp.txt");
		DEBUG.print(text.getAbsolutePath());
		Path path2 = Paths.get(text.getParent());
		
		FileWriter writer = new FileWriter(text,true);
		String holder = ReadText.loadAllText(target);
		Files.delete(path);
		createFile.createFile(text.getParent(), "AI6Data", "txt");
		String[] logicSeperator = holder.split("\n");
		for (int i = 0; i<logicSeperator.length; i++) {
			if(targetWord.equals(logicSeperator[i])) {
				// do nothing
			}else {
				writer.write(logicSeperator[i]+"\n");
			}
		}
		writer.close();
	}
	
}
