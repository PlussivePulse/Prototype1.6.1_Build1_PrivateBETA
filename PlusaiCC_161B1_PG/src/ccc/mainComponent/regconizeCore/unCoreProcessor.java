package ccc.mainComponent.regconizeCore;

import java.util.ArrayList;
import java.util.Random;

import ccc.interaction.global.NetworkSurface;
import ccc.interaction.global.database.DateAndTime;
import ccc.mainComponent.Systems;
import ccc.mainComponent.globalID;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.languages.engDB;
import servicePackage.serviceLoader;
import servicePackage.stabilizer;

public final class unCoreProcessor {

	private static String[] i = {};
	private static String currentAnswer = "";
	
	private static String returningAnswer = "";
	public static boolean bounded = true;
	
	public static String customRe = "";
	
	public static String stackingMemory = "";
	
	public static ArrayList<String> contain = new ArrayList<String>();
	public static ArrayList<Boolean>understandStatus = new ArrayList<Boolean>();
	private static boolean proNounExist = false;
	private static boolean possibleAnswer = false;
	private static boolean verbPronounCalculated;
	private static boolean notUnderstandStated = false;
	private static boolean needtoStateNU = true;
	private static String currentStream = "";
	
	public static String getInput (String[] stream) {
		
		for(int i = 0; i<stream.length; i++) {
			currentStream += stream[i];
		}
		i = stream; //copy
		//globalID.addPendingQuestion(); //TEMPORARY! //////////////////////////////////
		
		Systems.infrontTextCustom = ""; //reset memory

		
		if(globalID.getPendingQuestion()>0) {
			currentAnswer = stream[0];
			
			if(currentAnswer.contains("singleInput")) {
				
				if(stream.length>1) {
					returningAnswer = "tryingToUnderstand";
				}
					
				else {returningAnswer = processCore.process();
				}
			}
			
		}else if (globalID.getPendingQuestion()==0){
			try {
				if(stream[0].contains("singleInput")) {
					returningAnswer = "I am not sure I'm understand. (No Question Asked - Got an Answer.)";
				}}catch(ArrayIndexOutOfBoundsException e){
				bounded = false;
				customRe = "something I can't handle yet.";
			}
		}
		
		if(bounded == true) {
			if(stream[0].length()>11) {
				if(stream[0].substring(0,11).equals("systemPrint")) {
					returningAnswer = stream[0];
				}
			}
			if(stream[0].contains("systemExecute")) {
				
			}else if(stream[0].contains("greeting") || stream[0].contains("supportAnswer")) {
				stream[0] = stream[0].replace("(greeting)", "");
				stream[0] = stream[0].replace("(supportAnswer)", "");
				String firstUserInput = stream[0].substring(0,1).toUpperCase() + stream[0].substring(1);
				returningAnswer = firstUserInput; //default answer
				int randomNum = new Random().nextInt(3);
				int randomFUI = new Random().nextInt(3);
				if(randomNum==1) {
					returningAnswer = firstUserInput + ", Ready at your service.";
					needtoStateNU = false;
				}else if(randomNum==2) {
					//good morning
					if (randomFUI == 1) {
						firstUserInput = "";
					}else if (randomFUI == 2){
						firstUserInput += ", ";
					}
					
					String[] dtF = DateAndTime.getDateTimeFormat();
					DEBUG.print(Integer.valueOf(dtF[3])+"");
					if(Integer.valueOf(dtF[3]) >= 0 && Integer.valueOf(dtF[3]) < 6) {
						returningAnswer = firstUserInput + "Good early morning";
					}else if(Integer.valueOf(dtF[3]) >= 6 && Integer.valueOf(dtF[3]) < 12) {
						returningAnswer = firstUserInput + "Good Morning";
					}else if(Integer.valueOf(dtF[3]) >= 12 && Integer.valueOf(dtF[3]) < 15) {
						returningAnswer = firstUserInput + "Good after noon.";
					}else if(Integer.valueOf(dtF[3]) >= 15 && Integer.valueOf(dtF[3]) < 20) {
						returningAnswer = firstUserInput + "Good evening.";
					}else if(Integer.valueOf(dtF[3]) >= 20 && Integer.valueOf(dtF[3]) < 24) {
						returningAnswer = firstUserInput + "Good night.";
					}
				}
				
				if(stream.length>1) { //single greeting
					if(stream[1].contains("multi") || stream[1].contains("timeWord") ) {
						stream[0] = stream[0].replace("(greeting-multi)", "");
						//old support for 2 words
						stream[0] = stream[0].replace("(split-Connect)", " ");
						
						stream[1] = stream[1].replace("(timeWord)", " ");
						customRe = stream[0] + stream[1];
						needtoStateNU = false;
					}else {
						customRe = "something is wrong.";
					}
				}
				

				
				
			}
			
			

			
			if(stream.length>2)
				if(stream[2].contains("supportAnswer")) {
					DEBUG.print("");
					if(stream[0].contains("pronoun")) {
						if(globalID.getPendingQuestion()==0) {
							returningAnswer = "Good!";
						}else if(globalID.getPendingQuestion()>0) {
							returningAnswer = "Good!";
						}
					}
				}
			
			///////////////////////////////////////////////////
			//single command
			///////////////////////////////////////////////////
			checkTest(stream);
			
			 if(stream[0].contains("say") && !stream[0].contains("/")) {
					returningAnswer = "User uses sound command";
				}
			
		}
		
		if(stream.length>0)//patch error 1.5.0 pre5-2
		if(stream[0].contains("pronoun")) {
			if(stream[0].contains("pronoun1")){
				contain.add(stream[0]);
				proNounExist = true;
				possibleAnswer = true;
			}
			if(stream[0].contains("pronoun2")){
				contain.add(stream[0]);
				proNounExist = true;
				possibleAnswer = true;
			}
			if(stream[0].contains("pronounI")){
				contain.add(stream[0]);
				proNounExist = true;
				possibleAnswer = true;
			}
			
			if(proNounExist == true && stream[1].contains("verb")) {
				verbPronounCalculated = true;
				if(stream.length>=3) {
					int targetDelete = (stream[2].indexOf(")"));
					stream[2] = stream[2].substring(targetDelete+1);
					if(stream[1].contains("is")
							||stream[1].contains("am")
							||stream[1].contains("are")
							||stream[1].contains("am")
							||stream[1].contains("was")
							||stream[1].contains("were")) {
						if(stream.length<4) {
							DEBUG.print(stream[2]);
						}
					}
				}
			}
		}
		
		//RequestCommand Exception
		if(stream.length>0) //patch error 1.5.0 pre5-2
		if(stream[0].toLowerCase().contains("request")) {
			if(stream[0].toLowerCase().contains("verb")) {
				if(stream[1].contains("(pronoun")) {
					if(stream[1].contains("(pronoun3)")){
						if(stream.length<3) { //Example : give me, set me, do me
							returningAnswer = "What do you wanted?";
							String iTC = "";
							 	for(int i= 0; i<stream.length; i++)
							 	{
							 	if(!iTC.equals("")) {
							 		iTC += " ";
							 	}
							 	iTC += stream[i];	
							 	
							 	}
							Systems.infrontTextCustom = iTC;
						}else {
							checkTest(stream); //check for command test.
							if(stream[2].contains("(request")) {
								if(stream[2].toLowerCase().contains("assistant")) {
									Systems s = new Systems();
									reset();
									//s.submitString("/"+stream[2].substring(stream[2].indexOf(")")+1));
									returningAnswer = "[SYSTEMRequest]:"+"/"+stream[2].substring(stream[2].indexOf(")")+1);


									DEBUG.print(returningAnswer);
								}
							}
						}
						
					}
					
				}else if(stream[1].contains("(request")) { //Example give give
					returningAnswer = "Hmm... something is wrong here. (2 requests verb)";
				}
			}
		}
		if(stream[0].contains("(request")) {
			if(stream[1].toLowerCase().contains("universityname")) {
				//serviceLoader.SystemLoad().resetAll();
				//wordsTransformer.softReset();
				//engDB.resetall();
				//reset();
				String wholeStream = serviceLoader.SystemLoad().oldText;
				returningAnswer = serviceLoader.SystemLoad().submitString("/" + wholeStream);
			
			}
				
		}
		//patch non-reset variable make returningAnswer not updating
		if(!customRe.equals("")) {
			returningAnswer = customRe;
			customRe = "";
		}
		
		
		//Existed in PrefixTransformer but not filtered.
		//Filtered Version
		
		try {
			if(stream.length!=1)
				for(int i=0; i<stream.length; i++) {
					if(understandStatus.get(i) == true) {
						stackingMemory += stream[i];
						needtoStateNU = false;
					}else {
						needtoStateNU = true;
						if(notUnderstandStated == false&&needtoStateNU == true) {
							returningAnswer = "I can't understand \""+stream[i]+"\"";
							for(int j=1; j<stream.length; j++) {
								if(understandStatus.get(j) == false) {
									returningAnswer += "," + stream[j];
								}
							}
							returningAnswer += ".";
							
							notUnderstandStated = true;
						}
					}
				}
		}catch(IndexOutOfBoundsException ex) {
			DEBUG.print("Automatic Stabilized unstable environment in unCoreProcessor. [InternalBug-01]");
		}
			
		/////////////////////////////////////////////
		
		//Example : How ... ...
		
		if(stream[0].toLowerCase().contains("(question")){
			returningAnswer = "TEST";
			//////////////////////////////////////////////////////
			
			//Simple : How are you?
			if(stream[0].contains("-")) {
				if(stream[0].split("-")[1].toLowerCase().contains("condition"))
				if(stream[1].toLowerCase().contains("(equal")) {
					returningAnswer = "you?";
					if(stream[2].contains("(self")){
						returningAnswer = "I am good";
					}
				}
				
			}
			////////////////////////////////////////////////////////
		}
		
		if(unCoreProcessor.bounded == false) { //when system can't understand bound=false;
			if(notUnderstandStated == false) {
				returningAnswer = "I don't understand that.";
				Systems.setSystemExecuted(true); //patch internal error 01
			}
			
		}else {
			if(prefixTransformer.command==true) {
				returningAnswer = "Couldn't execute the following command." + "\n" + currentStream;
			}
		}
		
		
		DEBUG.print(stackingMemory);
	
		//reset
		reset();
		
		
		return "[Plusai] : " + returningAnswer;
	}

	private static void checkTest(String[] stream) {
		//if(stream.length==1) { 
		for(int i= 0; i<stream.length; i++)
			if(stream[i].contains("testing")) {
				if(mainRunnerCore.displayer!=null) {
					mainRunnerCore.displayer.append("\n" + globalID.getId() + "System : Stabilizer Status : " + stabilizer.getStabilizerValue());
					globalID.addID(0.1);
					mainRunnerCore.displayer.append("\n" + globalID.getId() + "System : Network Surface - Network connection = " + NetworkSurface.isConnected());
					globalID.addID(0.1);
				}
				returningAnswer = "Test Test!";
			}
		
	//	}
		
	}
	
	public static void reset() {
		proNounExist = false;
		contain = new ArrayList<String>();
		possibleAnswer = false;
	}
	public static void FORCEreset() {
		proNounExist = false;
		contain = new ArrayList<String>();
		possibleAnswer = false;
		understandStatus = new ArrayList<Boolean>();
	}
	
	
}
