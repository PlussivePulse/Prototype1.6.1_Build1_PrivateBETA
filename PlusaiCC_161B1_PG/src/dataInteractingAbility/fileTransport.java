package dataInteractingAbility;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

public class fileTransport{
	
	public void transferDir(String dir1, String dir2) {
		File srcDir = new File(dir1);
		File destDir = new File(dir2);
		
		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	 static void transferBase(String dir) {
		 
		File srcDir = null;
		
		try {srcDir = new File(filesCore.getDir());
		System.out.println(filesCore.getDir());} 
		catch (URISyntaxException e1) {e1.printStackTrace();}
		
		File destDir = new File(dir+"\\EvacuateFolder");
		
		File getData = null;
		
		try {
			getData = new File(srcDir.getParentFile() +"\\AI6Data.txt");}
		catch (Exception ex) {
			ex.printStackTrace();}
		
		///////////////////////////////////
		
		try {FileUtils.copyDirectory(getData, destDir);} 
		catch (IOException e) {
			e.printStackTrace();} 
		catch (NullPointerException ex) {
			ex.printStackTrace();}
		catch (Exception ex) {}
		
		try {FileUtils.copyDirectory(srcDir.getParentFile(), destDir);} 
		catch (IOException e) {
			e.printStackTrace();} 
		catch (NullPointerException ex) {
			ex.printStackTrace();}
		
		//srcDir.delete();
	}
}
