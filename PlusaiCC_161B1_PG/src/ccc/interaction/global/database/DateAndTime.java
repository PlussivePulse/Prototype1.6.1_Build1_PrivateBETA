package ccc.interaction.global.database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.popUpBox;
import servicePackage.stabilizer;

public class DateAndTime {
	private static DateTimeFormatter dat;
	private static DateTimeFormatter DAF;
	private static LocalDateTime localDat;
	private final static String defDatPattern = "yyyy/MM/dd HH:mm:ss";
	
	public static int day;
	public static int month;
	public static int year;
	public static int hour;
	public static int min;
	public static int sec;
	
	public static void setDate(int days,int months, int years, int hours, int mins , int secs) {
		day = days;
		month = months;
		year = years;
		hour = hours;
		min = mins;
		sec = secs;
	}
	
	public final boolean requireStabilizer = false;
	
	public static void setLocalDaT() {
		localDat = LocalDateTime.now();
		
	}
	
	public static void setPattern(String pattern) { //suggest an edit to improve code style
		
		if(pattern.equals("")) {
			if(stabilizer.getASValue() == true) {
				pattern = defDatPattern;
				//popUpBox.infoBox("Default Date And Time pattern has been used instead of empty String value.", this.getClass(), this.getClass().getMethods() + "Auto Stabilizer");}
			autoNotificationPusher.notPush("INFO", "Auto Stabilizer", "Default Date And Time pattern has been used instead of empty String value.", DateAndTime.class,"AUTO");}
			else{
				//popUpBox.warningBox("DateAndTime Pattern setting is empty. \nThis may results in an error when execute above from format.", "Warning : Highly Possible Code Exception Error.");
			//autoNotificationPusher.notPush("WARNING", "Warning : Highly Possible Code Exception Error.", "DateAndTime Pattern setting is empty. \nThis may results in an error when execute above from format.", DateAndTime.class,"TRAY");
			}}
		if(stabilizer.getASValue() == true) {
			if(localDat == null) {
				setLocalDaT();
				//popUpBox.infoBox("Handled possible null Object temporal requireNonNull in DateTimeFormatter.",this.getClass(), stabilizer.getS1());
			autoNotificationPusher.notPush("INFO", stabilizer.getS1(), "Handled possible null Object temporal requireNonNull in DateTimeFormatter.", DateAndTime.class,"AUTO");
			}
		}
		
		dat = DateTimeFormatter.ofPattern(pattern);
	}
	
	public static String getDAT() {
		return dat.format(localDat);
		//return day+":"+month+":"+year+":"+hour+":"+min+":"+sec;
	}
	
	public static boolean datNull() {
		if(dat==null) {
			return true;
		}
		return false;
	}
	
	public static String[] getDateTimeFormat() {
		String data = getDAT();
		data = data.replaceAll("/", ":");
		data = data.replaceAll(" ", ":");
		String dF[] = data.split(":");
		return dF;
	}
}

