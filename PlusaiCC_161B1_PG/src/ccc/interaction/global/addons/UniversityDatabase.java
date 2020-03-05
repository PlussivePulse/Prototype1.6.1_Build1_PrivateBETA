package ccc.interaction.global.addons;

import ccc.interaction.global.popUpBox;
import ccc.interaction.global.database.DateAndTime;

public class UniversityDatabase {
	
	public static String university = "";
	
	/*public static void checkWordAddOn(String addOn) {
		if(addOn.equals("cwu")) {
			addOn = "(addOn)";
		}
	}
	public static void getStream(String[] stream) {
		if(stream[1].toLowerCase().equals("cwu")) {
			if(stream[2].toLowerCase().equals("cwu")) {
				
			}
		}
	}*/
	
	public static String checkWord(String string) {
		if(string.contains("roster")) {
			string = "(weblinkSearch)" + string;
		}
		return string;
	}

	public static String checkSport(String sport) {
		
		String webLink = "";
		String webLinkSuf = "";
		String sportName = "";
		
		/////////////////////////////////////////////////////
		
		if(university.equals("cwu")) {
			webLink = "http://wildcatsports.com/sports/";
			webLinkSuf = "/roster";
		}
		
		else if(university.equals("uw")||university.equals("udub")) {
			webLink = "http://gohuskies.com/roster.aspx?path=";
			webLinkSuf = sport;
		}
		
		/////////////////////////////////////////////////////
		
		if(sport.equals("track")||sport.toLowerCase().equals("track-and-field")) {
			sportName = "track-and-field";
		}
		
		if(sport.toLowerCase().equals("xc")||sport.toLowerCase().equals("cross-country")) {
			sportName = "cross-country";
			if(university.equals("uw")||university.equals("udub")) { //university of washington patch
				webLinkSuf = "cross";
			}
		}
		
		if(sport.toLowerCase().equals("menbasketball")||sport.toLowerCase().equals("men-basketball")) {
			sportName = "men-basketball";
		}
		
		if(sport.equals("football")) {
			sportName = "football";
		}
		
		if(sport.toLowerCase().equals("wrestling")) {
			webLink = "https://www.google.com/search?q=";
			if(university.equals("cwu")) {
				sportName = "cwu+wrestling";
				webLinkSuf = "";
			}else if(university.equals("uw")||university.equals("udub")) {
				webLink = "https://ncwa.net/teams/";
				webLinkSuf = "uw";
			}
			popUpBox.warningBox("Couldn't find sport:" + sport + "in the database.", "University Database : ");
		}
		
		/////////////////////////////////////////////////////	
		if(university.equals("cwu")) {
			sport = webLink + "" + sportName + "" + webLinkSuf;
		}else if(university.equals("uw")||university.equals("udub")) {
			sport = webLink + webLinkSuf;
		}
		
		return sport;
	}
	
	public static String checkBooks(String book) {
		String bookLink = "";
		String http = "";
		String httpUniv = "";
		String frontFormat = "";
		String search = "";
		String tab = "&tab=default_tab";
		String vid = "&vid="+university.toUpperCase();
		String available = "";
		String peer = "";
		String offset = "&offset=";
		String offsetValue = "0";
		
		frontFormat = "/primo-explore/search?query=any,contains,";
		
		if(university.equals("cwu")) {
			http = "https://searchlib.";
			httpUniv = university + ".edu";
		}
		
		if(university.equals("uw")||university.equals("udub")) {
			http = "https://alliance-primo.hosted.exlibrisgroup.com";
			//http = "https://searchlib."; //alternative by tempering vid
			//httpUniv = "washington" + ".edu"; //alternative by tempering vid
			//httpUniv = "cwu" + ".edu"; //alternative by tempering vid
			vid = "&vid=UW";
		}
		
		if(university.equals("wwu")) {
			http = "https://onesearch.library.";
			httpUniv = university + ".edu";
		}
		
		if(university.equals("custom")) {
			http = "https://www.google.com/search?q=";
			university = popUpBox.askStringBox("Insert universiy name or id", "");
			httpUniv = university + "+library+";
			frontFormat = "";
		}
		
		
		

		String[] splitter = book.split(" ");
		for (int i = 0; i < splitter.length; i++) {
			if(!splitter[i].contains("=")) {
				if(i==0) {
					search = splitter[0];
				}else {
					search += "%20" + splitter[i];
				}
			}else {
				String[] split2 = splitter[i].split("=");
				if(split2[0].equals("offset")) {
					offsetValue = split2[1] + "";
				}else if(split2[0].equals("reviewed")||split2[0].equals("peered")) {
					if(split2[1].equals("true")) {
						peer = "&mfacet=tlevel,include,peer_reviewed,1";
					}
				}else if (split2[0].equals("available")) {
					if(split2[1].equals("true")) {
						available = "&mfacet=tlevel,include,available$$I" + university.toUpperCase() + ",1";
					}
				}
			}
		}
		bookLink = http + httpUniv + frontFormat + search + tab + vid + available + peer + offset + offsetValue;
		return bookLink;
	}
	
	public static String getCanvas(String canvas) {
		
		String webLink = "";
		String webLinkSuf = "";
		
		if(canvas.equals("udub")) {
			canvas = "uw";
		}
			webLink = "canvas";
			webLinkSuf = canvas + ".edu";
		
		canvas = webLink + "." + webLinkSuf;
		return canvas;
	}
	
	
	public static String roasterGetHelp(String university) {
		String getHelp = university;
		if(university.toLowerCase().equals("uw")||university.toLowerCase().equals("udub")) {
			getHelp = "< dawg sports: cross(cross_country),track-and-field,men-basketball,football,etc. [replace blankspace with \"_\"] >";
		}
		if(university.toLowerCase().equals("cwu")) {
			getHelp = "< wildcats sports: xc(cross_country),track-and-field,men-basketball,football,etc. [replace blankspace with \"_\"] >";
		}
		return getHelp;
	}
}