package ccc.mainComponent.regconizeCore.languages;
import java.util.ArrayList;

import ccc.interaction.global.popUpBox;
import ccc.interaction.global.addons.ENGDB_university_addOn;
import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.answersList;
import ccc.mainComponent.regconizeCore.unCoreProcessor;
import ccc.mainComponent.regconizeCore.wordsTransformer;

public final class engDB extends wordsTransformer{
	
	private final static String equal1 = "is";
	public final static String pronoun1[] = {"he", "she", "it"}; //is only
	private static int pronounCount = 0;
	public final static String pronoun2[] = {"we", "they" , "those"}; //are only
	private final static String equal2 = "are";
	public static boolean found = false;
	private static String pronounSpecial = "there"; //is supported are okay
	private final static String equal3 = "will";
	private final static String pronounI = "I";
	private final static String pronounUser = "me";
	private final static String equal4 = "had";
	private final static String equal5 = "would";
	private final static String equalS1 = "d";
	
	private final static String article[] = {"a", "an", "the"};
	
	private final static String greeting1 = "hello";
	private final static String greeting2 = "hi";
	
	private final static String testing = "test";
	
	public static boolean wrongSpacing = false;
	public static String wSHandler = "";
	
	//@Deprecated(since="08262019-undocumented-unused")
	private static boolean falseHandler = false;
	
	private static String saveConfig = "";
	private static boolean changeRequired1 = false;
	private static String patchGood[];
	private static String c;
	public static boolean isCommand = false;
	private static boolean customDisableC;
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<Class> classList = new ArrayList<Class>();

	public static String startCorrection(String i) {
		if(customDisableC != true) {
			if(isCommand == false) {
				int position = i.indexOf("(");
				if(i.contains("(autoTransform)")) {
					saveConfig = "(autoTransform)";
					changeRequired1 = true;
					i = i.replace("(autoTransform)", "");
				}
				
				//patch good morning
				if(i.contains("(split)")) {
					DEBUG.print(i);
					i = i.replace("(split)", " ");
					patchGood = i.split(" ");
				}
				
				c = "internalError-startingCorrection-null";
				String isCheck = ""; //2 letter
				String isCheck2 = ""; //3 letter
				String isCheck3 = ""; //4 letter
				String isCheck4 = ""; //5 letter
				String isCheck5 = ""; //1 letter
				
				//patch good morning
				if(!i.contains(" ")) {
		
					if(i.length()>=2)
						isCheck = i.substring(i.length()-2).toLowerCase();
					if(i.length()>=3)
						isCheck2 = i.substring(i.length()-3).toLowerCase();
					if(i.length()>=4)
						isCheck3 = i.substring(i.length()-4).toLowerCase();
					if(i.length()>=5)
						isCheck4 = i.substring(i.length()-5).toLowerCase();
					if(i.length()>=12) {
						isCheck5 = i.substring(i.length()-12);
						DEBUG.print(isCheck5);
						
					}
				}
				
		
				
				if(isCheck.equals(equal1)||isCheck2.equals(equal2)) {
					/////////////////
					if(found == false)
					while(engDB.pronounCount < pronoun1.length) {
						if(i.substring(0, i.length()-2).toLowerCase().equals(pronoun1[pronounCount])||
								i.substring(0, i.length()-3).toLowerCase().equals(pronoun1[pronounCount])||
								i.substring(0, i.length()-2).toLowerCase().equals(pronounSpecial)||
								i.substring(0, i.length()-3).toLowerCase().equals(pronounSpecial)){
		
							if(isCheck.equals(equal1)) {c = i.substring(0, i.length()-2) + "(split)" + i.substring(i.length()-2);}
							if(isCheck2.equals(equal2)) {c = i.substring(0, i.length()-3) + "(splitCorrection)" + equal1;}
							
							if(i.substring(0, i.length()-2).toLowerCase().equals(pronounSpecial) && isCheck.equals(equal1)) {
								c = i.substring(0, i.length()-2) + "(split)" + equal1;
							}else if(i.substring(0, i.length()-3).toLowerCase().equals(pronounSpecial)) {
								c = i.substring(0, i.length()-3) + "(split)" + equal1;
							}
							found=true;
						}else {
						}
						pronounCount++;
					}
						
					resetpc();
					/////////////////
					
					if(found == false)
					while(engDB.pronounCount < pronoun2.length) {
						if(i.substring(0, i.length()-2).toLowerCase().equals(pronoun2[pronounCount])||i.substring(0, i.length()-3).toLowerCase().equals(pronoun2[pronounCount])) {
								if(i.substring(0, i.length()-2).toLowerCase().equals(pronoun2[pronounCount])) {c = i.substring(0, i.length()-2) + "(splitCorrection)" + equal2;}
								if(i.substring(0, i.length()-3).toLowerCase().equals(pronoun2[pronounCount])) {c = i.substring(0, i.length()-3) + "(split)" + equal2;}
							found=true;
						}
						pronounCount++;
					}
					resetpc();
					/////////////////
					
				}
				
				if(isCheck3.equals(equal3)) { //check [WILL]
					if(found==false) {
						c = i.substring(0, i.length()-4) + "(split)" + equal3;
						if(i.substring(0,1).equals(pronounI)) {
							if(changeRequired1 == true) {
								c= c.replace("(split)", saveConfig);
								found=true;
							}
						}
					}
				}else if(isCheck2.equals(equal4)) { //check [HAD]
					if(found==false) {
						c = i.substring(0, i.length()-3) + "(split)" + equal4;
						if(i.substring(0,1).equals(pronounI)) {
							if(changeRequired1 == true) {
								c= c.replace("(split)", saveConfig);
								found=true;
							}
						}
					}
					
				}else if(isCheck4.equals(equal5)) { //check [Would]
					if(found==false) {
						c = i.substring(0, i.length()-5) + "(split)" + equal5;
							if(changeRequired1 == true) {
								c= c.replace("(split)", saveConfig);
								found=true;
							}
					}
					
				}else if(isCheck5.equals("[STRING=\"D\"]")) { //check [Would]
					if(found==false) {
						c = i.replace("[", " (reqCheck+autoCorrected)[");
						c = c.replace("D", "d");
						found=true;
					}
				}else if(isCheck5.equals("[STRING=\"d\"]")) { //check [Would]
					if(found==false) {
						c = i.replace("[", " (reqCheck)[");
						found=true;
					}
				}
				
				
				//patch "shes"
				if(found==false) {
					String req = "(reqCheck)";
					if(i.length()>=2)
						if(i.toLowerCase().equals("shes")) {
							if(i.substring(3,4).equals("S")) {
								req = "(reqCheck+autoCorrected)";
								i = i.substring(0, 3) + i.substring(3,4).toLowerCase();
							}
							c = i.substring(0,3) + " " + req + "[STRING=\"" + i.substring(3,4)+ "\"]";
							if(i.subSequence(0, 1).equals("s")) {
								c = "(autoCap)S" + c.substring(1);
							}
							found=true;
						}
				}
				
				//patch hes
				if(found==false) {
					String req = "(reqCheck)";
					String specialStore = "h";
					if(i.length()>=2)
						if(i.toLowerCase().equals("hes")) {
							if(i.substring(0,1).equals("h")) {
								specialStore = "(autoCap)H";
							}
							if(specialStore.equals("h")) {
								c = "(reqCheck)"+ i.substring(0);
							}else {
								c = "(reqCheck),"+ specialStore +i.substring(1);
							}
							found=true;
						}
				}		
				
				//patch normal words
				if(found==false)
					if(i.length()>=2)
					for(int co = 0; co<4; co++)
						if(i.toLowerCase().equals(answersList.shortPronounStart[co])) {
							char testing = i.charAt(0);
							if(Character.isUpperCase(testing)) {
								c=i;
							}else {
								c=i.substring(0,1).toUpperCase() + i.substring(1);
							}
							
							found=true;
						}
				
				//patch normal words
				/*if(found==false)
					if(i.length()>=2)
					for(int co = 0; co<i.length(); co++)
						if(i.toLowerCase().equals(pronoun1[co])) {
							c=i;
							found=true;
						}*/
				
				//hello word
				if(isCheck4.toLowerCase().equals(greeting1)){
					c = "(greeting)" + isCheck4;
					found=true;
				}
				
				//hi word
				if(isCheck.toLowerCase().equals(greeting2)){
					c = "(greeting)" + isCheck;
					found=true;
				}
				
				//patch good morning
				if(patchGood != null) {
					if(patchGood[0].toLowerCase().equals("good")) {
						String save = patchGood[0];
						patchGood[0] = "(greeting-multi)" + save;
						if(patchGood[1].toLowerCase().equals("morning")
								||patchGood[1].toLowerCase().equals("night")
								||patchGood[1].toLowerCase().equals("evening")) {
							c = patchGood[0] + "(split-Connect)"+ patchGood[1];
							found=true;
						}
						found=true;
					}
					
						
				}
				
				//if (patchGood != null && patchGood.length>1){	}
				
				//patch /command when forgot "/"
				if(i.toLowerCase().equals("encryptpicture")
				||i.toLowerCase().equals("pc")
				||i.toLowerCase().equals("iD")) {
					c = "[Command]" + i;
					found=true;
					customDisableC = true;
				}
				
				//patch I am
				if(i.equals("I'm") || i.equals("Iam")) {
					wrongSpacing = true;
					c = "(pronoun)I (verb)am";
					wSHandler += "I(split)am";
					found=true;
				}
				
				//patch single good
				if(i.toLowerCase().equals("good")) {
					c = "(supportAnswer)" +i;
					if(wrongSpacing == true) {
						wSHandler += " " + i;
					}
					found=true;
				}
				
				if(i.toLowerCase().equals("morning") || i.toLowerCase().equals("evening")) {
					c = "(timeWord)" +i;
					if(wrongSpacing == true) {
						wSHandler += " " + i;
					}
					found=true;
					
				}
				
				if(i.toLowerCase().equals("shutdown")) {
					c = "(Command)" +i;
					if(wrongSpacing == true) {
						wSHandler += " " + i;
						found=true;
					}
				}else if(i.toLowerCase().equals("dbload")) {
					c = "(Command)" +i;
					if(wrongSpacing == true) {
						wSHandler += " " + i;
						found=true;
					}
				}else if(i.toLowerCase().equals("open")) {
					c = "(Command)" +i;
					if(wrongSpacing == true) {
						wSHandler += " " + i;
						found=true;
					}
				}
				
				if(i.toLowerCase().equals(testing)) {
					c = "(testing)" +i;
					if(wrongSpacing == true) {
						wSHandler += " " + i;
					}
					found=true; //patch error no true boolean 1.5
				}
				
				for(int t=0; t<pronoun2.length; t++) {
					if(i.toLowerCase().equals(pronoun2[t])) {
						c = "(pronoun2)" +i;
						found = true;
					}
				}
				
				for(int t=0; t<pronoun2.length; t++) {
					if(i.toLowerCase().equals(pronoun2[t])) {
						c = "(pronoun2)" +i;
						found = true;
					}
				}
				
				//Custom equals section
				
					//verb:are
					if(i.toLowerCase().equals(equal2)) {
						c = "(verb)" +i;
						found = true;
					}
					
					//pronoun:me
					if(i.toLowerCase().equals(pronounUser)) {
						c = "(pronoun3)" +i;
						found = true;
					}
					
					if(i.toLowerCase().equals("you")) {
						c = "(self)" +i;
						found = true;
					}
					if(i.toLowerCase().equals("you?")) {
						c = "(self)(question)" +i;
						found = true;
					}
					
	///////////////////////////////////////////////////////////
	//Custom Section
	///////////////////////////////////////////////////////////
				
		//1.exception command
					
			//1.give = run command to target
					if(i.toLowerCase().equals("give")){
						c = "(requestVerb)" + i;
						found = true;
					}
			//2.set = run command to set something
					if(i.toLowerCase().equals("set")){
						c = "(requestSetting)" + i;
						found = true;
					}
			//3.get = run command to get something
					if(i.toLowerCase().equals("get")){
						c = "(requestGlobalObj)" + i;
						found = true;
					}
			//4.help = run command to request help
					if(i.toLowerCase().equals("help")){
						c = "(requestAssistant)" + i;
						found = true;
					}
			//5.open = run command to open
					if(i.toLowerCase().equals("open")){
						c = "(requestGlobalObj)" + i;
						found = true;
					}
		//2.exception word
			//1.word:article={a,an,the}
					if(i.toLowerCase().equals("a")){
						c = "(ignoreCase-Connect)" + i;
						found = true;
					}else if(i.toLowerCase().equals("an")){
						c = "(ignoreCase-Connect)" + i;
						found = true;
					}else if(i.toLowerCase().equals("the")){
						c = "(ignoreCase-Connect)" + i;
						found = true;
					}

			//1.word:article={is,are}
					if(i.toLowerCase().equals("are")){
						c = "(equal-Connect)" + i;
						found = true;
					}else if(i.toLowerCase().equals("is")){
						c = "(equal-Connect)" + i;
						found = true;
					}
		//3.question word
			//1.how:asking request
					if(i.toLowerCase().equals("how")){
						c = "(question-Condition)" + i;
						found = true;
					}
					
					
	///////////////////////////////////////////////////////////
		//a letter
		//b letter 
					if(i.toLowerCase().equals("book")){
						c = "(noun)" + i;
						found = true;
					}	
		//c letter
					if(i.toLowerCase().equals("canvas")){
						c = "(resource)" + i;
						found = true;
					}	
		//r letter
					if(i.toLowerCase().equals("roster")){
						c = "(noun)" + i;
						found = true;
					}	
	///////////////////////////////////////////////////////////
					
					//addOn for some words
					
					if(!c.contains("(")) {
						c = ENGDB_university_addOn.checkUniversityName(i);//university name
						if(c.contains("(")) {
							found = true;
						}else {
							found = false;
							DEBUG.print("engDB addOn system activated.");
						}
					}
					/////////////////////////////////////////////////
				if(c.equals("internalError-startingCorrection-null")) {c = c+"(valueOfText)"+i;}
				wordsTransformer.calculated = true;
			}
			//
		}
		
		if(engDB.found==false) {
			unCoreProcessor.understandStatus.add(false);
			DEBUG.print("English database couldn't find a match word");
		}else {
			unCoreProcessor.understandStatus.add(true);
		}
		
		DEBUG.print(found+"");
		return c ;
	}
	
	private static void resetpc() {
		engDB.pronounCount = 0;
	}
	
	public static void resetall() {
		engDB.pronounCount = 0;
		found = false;
		saveConfig = "";
		changeRequired1 = false;
		falseHandler = false;
		isCommand = false;
		c = "";
	}
	
	public static void softReset() {
		c = "";
	}
	
	public static String transform(String input) {
		if(input.contains("'")) {
			int indexChange = input.indexOf("'");
			int fullLength = input.length();
			String checkTarget = input.substring(indexChange);
			
			if(checkTarget.length()>2 && checkTarget.substring(1, indexChange+1).equals("ll")) {
				input = input.substring(0,indexChange) + "(autoTransform)will";
			}else if(checkTarget.length()>2 && checkTarget.substring(1, indexChange+1).equals("re")) {
				input = input.substring(0,indexChange) + "(autoTransform)are";
			}else if(checkTarget.length()>1 && checkTarget.substring(1, indexChange).equals("d")) {
				input = input.substring(0,indexChange) + "(autoTransform)[STRING=\"d\"]";
			}else if(checkTarget.length()>1 && checkTarget.substring(1, indexChange).equals("D")) {
				input = input.substring(0,indexChange) + "(autoTransform)[STRING=\"D\"]";

			}
			

		}else {
			//DEBUG.print("Normal keyword detected : " + input);
		}
		
		//DEBUG.print(input);
		input = startCorrection(input);
		return input;
	}
}
