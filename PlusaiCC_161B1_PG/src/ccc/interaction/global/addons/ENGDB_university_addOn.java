package ccc.interaction.global.addons;

import ccc.mainComponent.regconizeCore.languages.engDB;

public class ENGDB_university_addOn {
	public static final String[] fullnameList = {"Central Washington University"
			,"University of Washington (U-DUB)"
			,"University of Washington (UW-Code)" 
			,"University of Washington (Tacoma)"
			,"Western Washington University"}; //local test
	public static final String[] nameList = {"cwu","udub","uw" ,"udub_tacoma","wwu"}; //local test
	public static String[][] unameList = {{"Central Washington University","cwu"},
			{"University of Washington (U-DUB)","udub"},
			{"University of Washington (UW-Code)","uw"},
			{"University of Washington (Tacoma)","uw"},
			{"Western Washington University","wwu"}};
	public static String[] universityList = {};
	
	public static String main(String arg) {
		arg = checkUniversityName(arg);
		return arg;
		
	}
	
	public static String checkUniversityName(String name) {
		if(!name.contains("(")){
			for(int i =0; i<nameList.length; i++) {
				if(name.equals(nameList[i])) {
					name = "(engDB_addOn-universityName)" + name;
				}
			}
			for(int i =0; i<universityList.length; i++) {
				if(name.equals(universityList[i])) {
					name = "(engDB_addOn-universityName)" + name;
				}
			}
		}
		return name;
	}
}
