package ccc.mainComponent.regconizeCore;

import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.cmdExecute;
import ccc.interaction.global.directorySelector;
import ccc.interaction.global.fileSelector;
import ccc.interaction.global.globalInteractionService;
import ccc.interaction.global.listSelectorEx;
import ccc.interaction.global.notificationFrame;
import ccc.interaction.global.popUpBox;
import ccc.interaction.global.addons.ENGDB_university_addOn;
import ccc.interaction.global.addons.UniversityDatabase;
import ccc.interaction.global.addons.programsMaskFilter;
import ccc.interaction.global.components.seleniumDriverHelper;
import ccc.interaction.global.database.DateAndTime;
import ccc.interaction.global.webserviceScript.interact.FUNCTION_getAllPicture;
import ccc.interaction.global.webserviceScript.interact.MAIN_HOLDER;
import ccc.interaction.internalFeatures.Base64Encoder;
import ccc.interaction.internalFeatures.Base64EncoderStatic;
import ccc.interaction.internalFeatures.processControl;
import ccc.interaction.internalFeatures.soundBoard;
import ccc.mainComponent.globalID;
import ccc.mainComponent.UI.CWUSplashScreen;
import ccc.mainComponent.UI.Explorer;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.UI.seleniumPanel;
import ccc.mainComponent.UIComponents.cwuLoginHelper;
import ccc.mainComponent.UIComponents.restartApplication;
import ccc.mainComponent.experimental.DEBUG;
import ccc.mainComponent.regconizeCore.languages.engDB;
import dataInteractingAbility.ReadText;
import dataInteractingAbility.TextFileCreator;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;
import dataInteractingAbility.processesNet.ProcessesNet;
import servicePackage.serviceLoader;
import servicePackage.shutdownSequence;

public class commandsList extends wordsTransformer{
	protected static int trap;

	protected static void runCommand(String commandCheckString) {
		
		String[] splitter = commandCheckString.split(" ");
		
		if(splitter[0].toLowerCase().equals("exit")) {
			
			mainRunnerCore.displayer.append("\nExiting the program.");
			try {
				TimeUnit.SECONDS.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				 SwingWorker<Void, Void> submitter = new SwingWorker<Void, Void>(){
					protected Void doInBackground() throws Exception{
						globalID.addID(1.0);
						shutdownSequence s = new shutdownSequence();
						s.shutdown("shutdown");
	                 return null;
	                }
	               };
	               submitter.execute();

			
		}else if(splitter[0].toLowerCase().equals("encryptpicture")) {
			Base64Encoder b = new Base64Encoder();
			//Base64EncoderStatic.encrypt(fileSelector.DefaultLoadFile().getAbsolutePath());
			//String output[] = {"systemPrint The encrypted picture is exported as text file."};
			//String output[] = {"systemPrint " + Base64EncoderStatic.getFile()};
			globalID.addID(0.1);
			mainRunnerCore.displayer.append("\n" + globalID.getId() + "[INFO] This will take sometime");
			b.encrypt(fileSelector.DefaultLoadFile().getAbsolutePath());
			TextFileCreator.createTxtFile(b.getFile());
			String output[] = {"systemPrint " + "file has been encrypted."};
			wordsTransformer.output = output;

		}else if(splitter[0].toLowerCase().equals("decryptpicture")) {
			//Base64EncoderStatic.encrypt(fileSelector.DefaultLoadFile().getAbsolutePath());
			//String output[] = {"systemPrint The encrypted picture is exported as text file."};
			//String output[] = {"systemPrint " + Base64EncoderStatic.getFile()};
			globalID.addID(0.1);
			
			//b.DecryptPicture(ReadText.getPlainText());
			String out = ReadText.getPlainText();
			createFile.createFile(out,directorySelector.DirectorySelector() + "\\" + popUpBox.askStringBox("File Name", "Please Enter File name and Format."));
			wordsTransformer.output = output;	
			
		}else if(splitter[0].equals("pc")) {
			//check permission
			if(splitter.length>1) {
				if(splitter[1].equals("shutdown") && splitter.length<3) {
					String output[] = {"systemPrint " + ": shutting down the computer."};
					if(globalInteractionService.getOsVersion().contains("Window")) {
						cmdExecute.execute("shutdown -s -t 60");
					}
					
					wordsTransformer.output = output;
				}else if(splitter[1].equals("shutdown") && splitter.length==3) {
				 
						if(globalInteractionService.getOsVersion().contains("Window")) {
							if(splitter[2].matches("-?\\d+")) {
							cmdExecute.execute("shutdown -s -t "+splitter[2]);
							String output[] = {"systemPrint " + ": shutting down the computer in " + splitter[2] + " seconds."};
							wordsTransformer.output = output;
							unCoreProcessor.customRe = "shutting down the computer in " + splitter[2] + " seconds.";
							}else if(splitter[2].equals("cancel")){
								cmdExecute.execute("shutdown -a");
								unCoreProcessor.customRe = "systemPrint " + ": cancel shutdown process.";
							}
						}
						
			
				}else if (splitter[0].equals("pc") && splitter[1].equals("shutdown") && splitter.length==4){
					String output[] = {"systemPrint " + ": invalid format (pc\\shutdown\\(time or blank))"};
					wordsTransformer.output = output;
				}
				
				else if (splitter[0].equals("pc") && splitter[1].equals("getos")){
					String output[] = {"systemPrint " + globalInteractionService.getOsVersion()};
					wordsTransformer.output = output;
				}
				
				else if (splitter[0].equals("pc") && splitter[1].equals("killtask")){
					if(globalInteractionService.getOsVersion().contains("Window")) {
						processControl.killProcessMenu();
						String output[] = {"systemPrint " + "Opened Processes Terminator Menu."};
						wordsTransformer.output = output;
					}else {
						String output[] = {"systemPrint " + "Unsupport Operating System."};
						wordsTransformer.output = output;
					}
				}

				//Mark : get task manager process
				else if (splitter[0].equals("pc") && splitter[1].equals("gettask")){
					if(globalInteractionService.getOsVersion().contains("Window")) {
						try {
							mainRunnerCore.displayer.append("\nShowing all tasks...");
							globalID.addID(0.1);
							mainRunnerCore.displayer.append("\n"+ ProcessesNet.getProcessPanel());
						} catch (IOException e) {
							e.printStackTrace();
						}
						String output[] = {"systemPrint " + "Loaded all tasks."};
						wordsTransformer.output = output;
					}else {
						String output[] = {"systemPrint " + "Unsupport Operating System."};
						wordsTransformer.output = output;
					}
				}else{
					if(splitter[0].equals("pc")) {
						submitOutput(new String[]{"systemPrint " + "/pc [" + splitter[1] + "] is not valid. Use /help pc for more information."});
					}
				}
		}
			
			if (splitter[0].equals("pc") && splitter.length==1){
			String output[] = {"systemPrint " + ": invalid format (pc/command)"};
			wordsTransformer.output = output;
			}
			
		}else if(splitter[0].equals("help")){
			//load text file and send as output
			String output[] = {"systemPrint " + "this feature is coming."};
			wordsTransformer.output = output;
		}else if(splitter[0].equals("explorer")){
			Explorer.start();
		}
		
		else if(splitter[0].equals("say")){
			String text = "";
			for(int i =1; i<splitter.length; i++) {
				text = text + " " + splitter[i] ;
			}
			soundBoard s = new soundBoard();
			s.readLineDefaultExpr(text);
			notificationFrame.generate(text);
			//String output[] = {"systemPrint " + ": say" + text};
			//wordsTransformer.output = output;
			
		}else if(splitter[0].toLowerCase().equals("apptrapmode")) {
			try {
				if(splitter[1]!=null) {
					if(splitter[1].equals("start")) { 
						//depricated 11/25/2019
					}
				}
			}catch(Exception e){
				System.out.println("apptrapmode normal operation.");
			}
			Thread betaAppTrap = new Thread() {
				public void run() {
					programsMaskFilter.start();
				}
			};
			betaAppTrap.start();
			String output[] = {"systemPrint " + "Apptrap mode initiated."};
			wordsTransformer.output = output;

		}else if(splitter[0].equals("evac")){
			filesCore.moveEmg();
		}else if(splitter[0].equals("date")){
			String output[] = {"systemPrint " + DateAndTime.getDAT()};
			wordsTransformer.output = output;
			
		}else if(splitter[0].equals("restart")){
			new restartApplication();
		}				
		
		///////////////////////////////////////////////////////////////////////////////
		
		//Database load
		
		else if(splitter[0].toLowerCase().equals("dbload") || splitter[0].toLowerCase().equals("open")) {
			submitOutput(new String[]{"systemPrint " + "/dbLoad <variable> <variable> ..."});
	
		
			String commandHolder = "";
			String commandHolders[];
			
			for(int i = 0; i<splitter.length; i++) {
				engDB.resetall();;
				commandHolder += engDB.transform(splitter[i]) + " ";
			}
			
			commandHolders = commandHolder.split(" ");
			DEBUG.print(commandHolder);
			
			if(splitter.length>1) {
				
				if(commandHolders[1].toLowerCase().contains("universityname")) {
					submitOutput(new String[]{"systemPrint " + "/dbLoad " + splitter[1] + " <variable>"});
					
					if(splitter.length>2) {
						
						if(commandHolders[2].contains("roster")) {
							if(UniversityDatabase.checkWord(commandHolders[2]).contains("(weblinkSearch")) {
								UniversityDatabase.university = splitter[1].toLowerCase();
								submitOutput(new String[]{"systemPrint " + "/dbLoad " + splitter[1] + " " + splitter[2] + "<sport>"});
								String getHelp = UniversityDatabase.roasterGetHelp(splitter[1]);
								if(!splitter[1].toString().equals(getHelp)) {
									submitOutput(new String[]{"systemPrint " + "/dbLoad " + splitter[1] + " " + splitter[2] + " " + getHelp});
								}
							}
							
								
								//////////////////////////////////////////////////////////////////////////
							if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {		
								cmdExecute.execute("cmd /c start \"\" "+ UniversityDatabase.checkSport(splitter[3]));
							}else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {
								try {
								cmdExecute.execute("open "+ UniversityDatabase.checkSport(splitter[3]));
								}catch (Exception e){
									e.printStackTrace();
									DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
									seleniumDriverHelper.loadPD("chrome");
									WebDriver driver = new ChromeDriver();
									driver.get(UniversityDatabase.checkSport(splitter[3]));
								}
							}
								submitOutput(new String[]{"systemPrint " + "Database search active."});
						}
							else if(commandHolders[2].contains("canvas")) {
								if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
									cmdExecute.execute("cmd /c start \"\" "+ UniversityDatabase.getCanvas(splitter[1]));
								}else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {
									try {
									cmdExecute.execute("open "+ UniversityDatabase.getCanvas(splitter[1]));
									}catch(Exception e){
										e.printStackTrace();
										DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
										seleniumDriverHelper.loadPD("chrome");
										WebDriver driver = new ChromeDriver();
										driver.get(UniversityDatabase.getCanvas(splitter[1]));
									}
								}
							submitOutput(new String[]{"systemPrint " + "Loading canvas through chrome."});
						}
						
						else if(commandHolders[2].contains("book")) {
							UniversityDatabase.university = splitter[1].toLowerCase();
							String book = "";
							if(splitter.length>=4) {
								book = splitter[3];
								for (int i = 4; i<splitter.length; i++) {
									book += " " + splitter[i];
								}
							}
							if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {		
								cmdExecute.execute("cmd /c start \"\" \""+ UniversityDatabase.checkBooks(book));
							}else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {	
								try {
								cmdExecute.execute("open "+ UniversityDatabase.checkBooks(book));
								}catch (Exception e){
									e.printStackTrace();
									DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
									seleniumDriverHelper.loadPD("chrome");
									WebDriver driver = new ChromeDriver();
									driver.get(UniversityDatabase.checkBooks(book));
								}
							}
							submitOutput(new String[]{"systemPrint " + "Loading book through chrome."});
						
						}else if(commandHolders[2].contains("login")) {
							CWUSplashScreen c = new CWUSplashScreen();
							c.main();
							submitOutput(new String[]{"systemPrint " + "Loading cwu login panel."});
							
						}
						
					}
					
					if(commandHolders.length>3) {
						if(splitter.length >= 5) {
							if(splitter[3].contains("men")||splitter[3].contains("women")) {
								for(int i = 3; i<splitter.length; i++) {
									if(i==3) {
										splitter[3] = splitter[3] + "-"+ splitter[4];
									}else {
										if((i+1 > splitter.length-1) == false) {
											splitter[i] = splitter[i+1];
										}
									}
								}
								
							}
						}
					}
						
				}else if(splitter[1].equals("help")) {

				}else if(splitter[1].toLowerCase().contains("getimage")) {
					SwingWorker<Void, Void> submitter = new SwingWorker<Void, Void>(){
						protected Void doInBackground() throws Exception{
						
							try {
								//MAIN_HOLDER.defaultsetDriver();
							}catch(Exception ex){
								ex.printStackTrace();
							}
							//FUNCTION_getAllPicture gAP = new FUNCTION_getAllPicture();
							seleniumDriverHelper.loadPD("chrome");
							WebDriver driver = new ChromeDriver();
							if(seleniumPanel.panelDisplayer == null) {
								seleniumPanel panel = new seleniumPanel();
							}else if(seleniumPanel.frame.isVisible() == false) {
								seleniumPanel.frame.setVisible(true);
							}
							FUNCTION_getAllPicture.loadImageDefault(driver,splitter[2]);

		                return null;
		               }
		              };
		              submitter.execute();
		              submitOutput(new String[]{"systemPrint " + "Loading selenium driver Function_GetImage."});
				}
				
	
				
			}
		}
		
		/////////////////////////////////////////////////////////////
		
		else if(splitter[0].toLowerCase().equals("search")){
			
		//////////////////////////////////////////////////////////
			
			if(splitter.length>1) {
				String commandHolder = "";
				String commandHolders[] = null;
				
				for(int i = 0; i<splitter.length; i++) {
					engDB.resetall();;
					commandHolder += engDB.transform(splitter[i]) + " ";
				}
				commandHolders = commandHolder.split(" ");
				ArrayList <String> universityList = new ArrayList();
				ArrayList <String> fulluniversityList = new ArrayList();
				for(int i =0; i<ENGDB_university_addOn.unameList.length; i++)
				{
					fulluniversityList.add(ENGDB_university_addOn.unameList[i][0]);
					universityList.add(ENGDB_university_addOn.unameList[i][1]);
					
				}
				
				universityList.add("custom");
				fulluniversityList.add("Custom");
				
				listSelectorEx listS = new listSelectorEx();
			
				////////////////////////////////////////////////////
				
				if(splitter[1].toLowerCase().equals("book")){
					submitOutput(new String[]{"systemPrint " + "Please type in book name."});
					if(splitter.length>2) {

						if(commandHolders[2].toLowerCase().contains("universityname")) {
							UniversityDatabase.university = splitter[2];
							String book = "";
							if(splitter.length>=4) {
								book = splitter[3];
								for (int i = 4; i<splitter.length; i++) {
									book += " " + splitter[i];
								}
							}
							if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
								cmdExecute.execute("cmd /c start \"\" \""+ UniversityDatabase.checkBooks(book)+"\"");
							}else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {
								try {
									cmdExecute.execute("open "+ UniversityDatabase.checkBooks(book));
								}catch(Exception e) {
									e.printStackTrace();
									DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
									seleniumDriverHelper.loadPD("chrome");
									WebDriver driver = new ChromeDriver();
									driver.get(UniversityDatabase.checkBooks(book));
								}
							}
							submitOutput(new String[]{"systemPrint " + "Loading book through default explorer."});
						}else {
						//ArrayList <String> universityList = new ArrayList(Arrays.asList(ENGDB_university_addOn.nameList));

						UniversityDatabase.university = listS.boxLoad("University Selection", "",fulluniversityList , universityList);
						String book = "";
						if(splitter.length>=3) {
							book = splitter[2];
							for (int i = 3; i<splitter.length; i++) {
								book += " " + splitter[i];
							}
						}
						if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
							cmdExecute.execute("cmd /c start \"\" \""+ UniversityDatabase.checkBooks(book)+"\"");
						}else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {
							try {
								cmdExecute.execute("open "+ UniversityDatabase.checkBooks(book));
							}catch(Exception e) {
								e.printStackTrace();
								DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
								seleniumDriverHelper.loadPD("chrome");
								WebDriver driver = new ChromeDriver();
								driver.get(UniversityDatabase.checkBooks(book));
							}
						}
						submitOutput(new String[]{"systemPrint " + "Loading book through default explorer."});
						}
					}
					
					//////////////////////////////////////////////////////////////////////////////////////////////////
					
					
				}else if(splitter[1].toLowerCase().equals("roster")||splitter[2].toLowerCase().equals("roster")){
					boolean found = false;
					if(splitter.length>2) {
						if(commandHolders[1].toLowerCase().contains("universityname")||commandHolders[2].toLowerCase().contains("universityname")) {
							if(splitter[1].toLowerCase().equals("roster")){
								UniversityDatabase.university = splitter[2];
								if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
								cmdExecute.execute("cmd /c start \"\" "+ UniversityDatabase.checkSport(splitter[3]));
								} else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {
									try {
										cmdExecute.execute("open "+ UniversityDatabase.checkSport(splitter[3]));
									}catch(Exception e) {
										e.printStackTrace();
										DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
										seleniumDriverHelper.loadPD("chrome");
										WebDriver driver = new ChromeDriver();
										driver.get(UniversityDatabase.checkSport(splitter[3]));
									}
								}
								found = true;
							}else if(splitter[2].toLowerCase().equals("roster")&&found == false) {
								UniversityDatabase.university = splitter[1];
							}	
							
							if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
							cmdExecute.execute("cmd /c start \"\" "+ UniversityDatabase.checkSport(splitter[3]));
							} else if(globalInteractionService.getOsVersion().toLowerCase().contains("mac")) {
								try {
									cmdExecute.execute("open "+ UniversityDatabase.checkSport(splitter[3]));
								}catch(Exception e) {
									e.printStackTrace();
									DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
									seleniumDriverHelper.loadPD("chrome");
									WebDriver driver = new ChromeDriver();
									driver.get(UniversityDatabase.checkSport(splitter[3]));
								}
							}
							submitOutput(new String[]{"systemPrint " + "Database search active."});
						}else {
							
							UniversityDatabase.university = listS.boxLoad("University Selection", "",fulluniversityList , universityList);
							cmdExecute.execute("cmd /c start \"\" "+ UniversityDatabase.checkSport(splitter[2]));
							submitOutput(new String[]{"systemPrint " + "Database search active."});
						}
						
					}
				}else {
					String text = "";
					if(splitter.length>=2) {
						text = splitter[1];
						for (int i = 2; i<splitter.length; i++) {
							text += "+" + splitter[i];
						}
					}
					if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
						cmdExecute.execute("cmd /c start \"\" \""+ "https://www.google.com/search?q="+text+"\"");	
					}else if(globalInteractionService.getOsVersion().toLowerCase().contains("window")) {
						try {
							cmdExecute.execute("open "+ "https://www.google.com/search?q="+text);	
						}catch(Exception e) {
							e.printStackTrace();
							DEBUG.print("An unknown error occured, the system is now launching selenium driver to resolve the problem.");
							seleniumDriverHelper.loadPD("chrome");
							WebDriver driver = new ChromeDriver();
							driver.get("https://www.google.com/search?q="+text);
						}
					}
				}
			}
			
		}
		else if (splitter[0].equals("iD")){
			if(splitter.length>1) {
				//if(splitter[1].equals("get")) {
					if(splitter.length<3) {
						//String output[] = {"systemPrint " + "/iD get <nameOfVariable>"};
						//wordsTransformer.output = output;
						submitOutput(new String[]{"systemPrint " + "/iD get <nameOfVariable>"});
					}//new version of string submitter
					else {
						//if(splitter.length==3) {
						submitOutput(new String[] {"systemPrint " + commandListsAddOn_iD.check(splitter[1], splitter[2])});
						//}
					}
				//}
			}else if(splitter[1].equals("help")){
				submitOutput(new String[]{"systemPrint " + "/iD <CommandList:get(getValueOfVariable)>"});
			}else {
				String output[] = {"systemPrint " + "/iD <command> <nameOfVariable>"};
				wordsTransformer.output = output;
			}
		
		}else if (splitter[0].equals("emergencyStop")) {
			mainRunnerCore.setTerminator(false);
			popUpBox.warningBox("Emergency Stop has been initiated", "Emergency Protocol", -1);
			//serviceLoader.loadID("emergStop,Emergency Stop has been initiated.");
			
			 SwingWorker<Void, Void> submitter = new SwingWorker<Void, Void>(){
				protected Void doInBackground() throws Exception{
					globalID.addID(1.0);
					shutdownSequence s = new shutdownSequence();
					s.shutdown("emergencyStop");
                return null;
               }
              };
              submitter.execute();
			submitOutput(new String[]{"systemPrint " + "Emergency Stop executed."});
			
		}
		
		
		else {
			DEBUG.print("Command name : " + splitter[0] + " couldn't be found.");
			String output[] = {"systemPrint " + "invalid command. (/help for more information.)"};
			wordsTransformer.output = output;
		}
		prefixTransformer.command = false;
		
	}
	
	public static void submitOutput(String[] string) {wordsTransformer.output = string;}
}
