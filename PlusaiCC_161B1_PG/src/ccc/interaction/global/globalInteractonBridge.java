package ccc.interaction.global;

//Inner File and User Interface Bridge

public class globalInteractonBridge {
	
	private dataInteractingAbility.getsub_PathSoft gSPS = new dataInteractingAbility.getsub_PathSoft();
	
	public String[][] getDirectory(String directory) {
		String[][] directoryFile = gSPS.getList(directory);
		return directoryFile;
		
	}
}
