package ccc.mainComponent.regconizeCore;

import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.languages.engDB;

public class wordsTransformer {
	
	static java.util.ArrayList<String> stringArray = new java.util.ArrayList<String>();
	
	public static boolean calculated = false;
	public static String[] output = {};

	private static int splitCorrectionCount;
	public static String[] Transform(String input) {
		String inputs[] = input.split("-");
		if(inputs.length==2) {
			input = inputs[1];
		}
	
		String array = input.trim().replaceAll(" +", " ");
		String arrays[] = array.split(" ");
		String firstBlock = "";
		String splitDevider[] = {null};
		String multiHandler = "";
		
		//patch bug no calculated
		String reseter[] = {};
		output = reseter;
		calculated = false;
		
		/*if(arrays.length==1 && calculated == false) {
			firstBlock = prefixTransformer.translate(arrays[0]);
		}else if(arrays.length==2 && calculated == false){
			firstBlock = prefixTransformer.translate(arrays[0] + "(split)" + arrays[1]);
		}else if(calculated == false){
			firstBlock = prefixTransformer.translate(arrays[0]);
		}*/
		
		if(!firstBlock.contains("/"))
		if(arrays.length==1 && calculated == false) {
			firstBlock = prefixTransformer.translate(arrays[0]);
		}else if(arrays.length>1 && calculated == false) {
			for(int i = 0; i<arrays.length; i++) {
				if(i >= 1) {
					multiHandler += " ";
				}
				multiHandler += prefixTransformer.translate(arrays[i]);
				engDB.isCommand = true;
				//DEBUG.print("Per multiHandler Value : " + multiHandler);
			}
			//multiHandler = multiHandler.replace(" ", "(split)");
			DEBUG.print("Final multiHandler Value : " + multiHandler);
		}else {
			if(arrays.length==1 && calculated == false) {
				firstBlock = prefixTransformer.translate(arrays[0]);
			}else if(arrays.length==2 && calculated == false){
				firstBlock = prefixTransformer.translate(arrays[0] + "(split)" + arrays[1]);
			}else if(calculated == false){
				firstBlock = prefixTransformer.translate(arrays[0]);
			}
		}
		
		
		
		
		if(firstBlock.contains("[Command]")
			||multiHandler.contains("[Command]")) {
			prefixTransformer.command = true;
			DEBUG.print("COMMAND FOUNDED");
		}
		
		if(multiHandler.contains(" ")) {
			splitDevider = multiHandler.split(" ");
			splitCorrectionCount = splitDevider.length-1;
		}else {
			splitDevider[0] = input;
		}
		
		

		String firstCheck2 = answersList.pronounScan(splitDevider[0]);
		DEBUG.print(firstCheck2);
		if(firstCheck2.contains("singleInput")||firstBlock.contains("singleInput")) {
			if(arrays.length==1) {
				stringArray.add(firstCheck2);
				output = stringArray.toArray(new String[0]);
			}else if(arrays.length >1) {
				arrays[0] = firstCheck2;
				output = arrays;
				
			}
		}else if(prefixTransformer.command == true) {
			String commandCheckString = firstBlock.replace("[Command]", "");
			if(arrays.length>1) {
				//commandCheckString = input.replace("/", ""); //patch imageloader
				commandCheckString = input.substring(1);
			}
			commandsList.runCommand(commandCheckString);
		}else if(firstBlock.contains("(greeting")) {
			String outputStream[] = {firstBlock};
			unCoreProcessor.bounded = true;
			output = outputStream;
		}else if(firstBlock.contains("(testing")) {
			String outputStream[] = {firstBlock};
			unCoreProcessor.bounded = true;
			output = outputStream;
		}else if(multiHandler.contains("(supportAnswer")) {
			unCoreProcessor.bounded = true;
			output = splitDevider;
		}else if(multiHandler.contains("(request")) {
			unCoreProcessor.bounded = true;
			output = splitDevider;
		}else if(multiHandler.contains("(question")) {
			unCoreProcessor.bounded = true;
			output = splitDevider;
		}else if(multiHandler.contains("(ignore")) {
			unCoreProcessor.bounded = true;
			output = splitDevider;
		}else if(multiHandler.contains("(equal")) {
			unCoreProcessor.bounded = true;
			output = splitDevider;
		}else if(multiHandler.contains("(engDB_addOn-")) {
			unCoreProcessor.bounded = true;
			output = splitDevider;
			
		////////////////////////////////////////////////////
			
			//Pronoun
			
		}else if(multiHandler.contains("(self")) {
			unCoreProcessor.bounded = true;
			output = splitDevider;
		}
		
		////////////////////////////////////////////////////
		else {
			unCoreProcessor.bounded = false;
			output = splitDevider;
		}
		
		
		return output;
	}
	
	public static void softReset() {
		calculated = false;
	}
}
