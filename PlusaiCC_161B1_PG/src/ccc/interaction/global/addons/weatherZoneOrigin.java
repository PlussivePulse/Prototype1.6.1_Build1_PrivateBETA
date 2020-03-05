package ccc.interaction.global.addons;

public final class weatherZoneOrigin {
	
	public static String[][] weatherZone = new String[6][7];
	private static int x;
	private static int y;
	
	public static void weatherCValue(String con, int valueID) {
		if(valueID==1) {//seattle
			weatherZone[2][1] = con;
		}else if(valueID==2) {//tacoma
			weatherZone[2][2] = con;
		}else if(valueID==3) {//portland
			weatherZone[5][1] = con;
		}else if(valueID==4) {//ellensburg
			weatherZone[3][3] = con;
		}else if(valueID==5) {//Spokane
			weatherZone[1][6] = con;
		}
	}
	
	public static void weatherCal(int checkRain) {
		
		int targetNum = checkRain;
		int rainNum = 0;
		if(targetNum == 1) {
			x = 2;
			y = 1;
		}else if(targetNum == 4) {
			x = 3;
			y = 3;
		}
		
		/////////////////////////////////////////
		
		for(int i = 0; i<7; i++) {
			for(int j=0; j<x; j++) {
				if(weatherZone[i][j].toLowerCase().contains("rain")){
					rainNum=+2;
				}else if(weatherZone[i][j].toLowerCase().contains("clear")) {
					rainNum=-2;
				}else if(weatherZone[i][j].toLowerCase().contains("cloud")) {
					rainNum=+1;
				}
			}
			
		}
		
		///////////////////////////////////////////
		
		for(int k = 0; k<7; k++) {
			for(int l=y+1; l<=7; l++) {
				if(weatherZone[k][l].toLowerCase().contains("rain")){
					rainNum=+2;
				}else if(weatherZone[k][l].toLowerCase().contains("clear")) {
					rainNum=-2;
				}else if(weatherZone[k][l].toLowerCase().contains("cloud")) {
					rainNum=+1;
				}
			}
		}
		
		////////////////////////////////////////////
		
		for(int n = x; n<=x; n++) {
			for(int s = 0; s<7; s++) {
				
				if(n!=x && s!=y) { //if location != target
					
					if(weatherZone[n][s].toLowerCase().contains("rain")){
						rainNum=+2;
					}else if(weatherZone[n][s].toLowerCase().contains("clear")) {
						rainNum=-2;
					}else if(weatherZone[n][s].toLowerCase().contains("cloud")) {
						rainNum=+1;
					}
					
				}
			}
		}
	}
}
