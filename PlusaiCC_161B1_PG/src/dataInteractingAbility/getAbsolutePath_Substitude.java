package dataInteractingAbility;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public final class getAbsolutePath_Substitude {
	static ArrayList<String> fileDirectory = new ArrayList<String>();
	
	public static ArrayList loadAbsolute(String direction) {
		getsub_PathSoft softPath = new getsub_PathSoft();
		String[][] dataCache = softPath.getList(direction);

		for(int i = 0; i<dataCache[1].length-1; i++) {
			File fileLocator = new File(dataCache[1][i]);
			if(fileLocator.isDirectory()) {addSubDirect(direction + fileLocator.getName());}
			else {
				fileDirectory.add(fileLocator.getAbsolutePath());
			}
		}
		return fileDirectory;
		
	}
	
	public static ArrayList loadAll() {
		File[] paths = File.listRoots();
		
		for(File path:paths) {
			getsub_PathSoft softPath = new getsub_PathSoft();
			String[][] dataCache = softPath.getList(path.getAbsolutePath());

			for(int i = 0; i<dataCache[1].length-1; i++) {
				File fileLocator = new File(dataCache[1][i]);
				if(fileLocator.isDirectory()) {addSubDirect(path.getAbsolutePath() + fileLocator.getName());}
				else {
					fileDirectory.add(fileLocator.getAbsolutePath());
				}
			}
		}
		return fileDirectory;
		
	}
	
	//still broken
	public static String loadAllFinder(String target) {
		File[] paths = File.listRoots();
		boolean stopper = false;
		
		String pathFound = "";
		
		if(stopper = false) {
			
			for(File path:paths) {
				getsub_PathSoft softPath = new getsub_PathSoft();
				String[][] dataCache = softPath.getList(path.getAbsolutePath());
	
				for(int i = 0; i<dataCache[1].length-1; i++) {
					File fileLocator = new File(dataCache[1][i]);
					if(fileLocator.isDirectory()) {addSubDirect(path.getAbsolutePath() + fileLocator.getName());}
					else {
						if(fileLocator.getAbsolutePath().contains(target)) {
							stopper = true;
							pathFound = fileLocator.getAbsolutePath();
						}
					}
				}
			}
		}
		return pathFound;
		
	}
	
	public static void addSubDirect(String direction) {
		File fileLocator = new File(direction);
		if(fileLocator.isDirectory()) {
			File[] holder = fileLocator.listFiles();
			if(holder!=null)
			for(int i =0; i<holder.length; i++) {
				if(holder[i].isDirectory()) {
					addSubDirect("" + holder[i]);
					fileDirectory.add("" + holder[i]);
					System.out.println("" + holder[i]);
				}else {
					fileDirectory.add("" + holder[i]);
					//fileDirectory.add("TEST");
					System.out.println("" + holder[i]);
				}
				
			
			}}
			
		else {//System.out.println(fileLocator.getAbsolutePath());
			fileDirectory.add(fileLocator.getAbsolutePath());
		}
	}
	
	//untested method! Experimental
	public File getThisJarPath() {
		File returning = null;
		try {
			return new File(getAbsolutePath_Substitude.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().toString());
		} catch (URISyntaxException e) {
				
			try {
				File dir = new File(filesCore.findFile(getAbsolutePath_Substitude.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
				returning = dir;
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return returning;
	}
}
