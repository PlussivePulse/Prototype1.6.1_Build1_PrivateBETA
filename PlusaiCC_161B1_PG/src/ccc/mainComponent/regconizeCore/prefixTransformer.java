package ccc.mainComponent.regconizeCore;

import ccc.mainComponent.regconizeCore.languages.engDB;

public final class prefixTransformer extends wordsTransformer{
	
		private static String storeString = "";
		public static boolean command = false;
		
		public static String translate(String streamFirst) {
			String check = "null";
			if(streamFirst.length()>1) { //not null check
				if(streamFirst.substring(0,1).equals("/")) { //command
					streamFirst = streamFirst.replace("/", "[Command]");
					
					storeString = streamFirst.substring(0);
					storeString = storeString.toLowerCase();
					command = true;
	
				}else {
				engDB.resetall(); //patch array false word from array.
				check = engDB.transform(streamFirst);
				
				}
				resetStatic();
			}
			
			if(check.contains("internalError")) {
				//if(check.contains("null"))
					//autoNotificationPusher.notPush("ALERT", "", "Incompatible text handle, Unable to handle input. \nContact Program creator for bug fixed.", prefixTransformer.class.getClass(), "AUTO");
					//mainRunnerCore.displayer.append("Incompatible text handle, Unable to handle input. \nContact Program creator for bug fixed.");
			}else if(command == true) {
				check = streamFirst;
			}
			
	
		return check;
			
		}
		
		private static void resetStatic() {
			storeString = "";
		}
	}

