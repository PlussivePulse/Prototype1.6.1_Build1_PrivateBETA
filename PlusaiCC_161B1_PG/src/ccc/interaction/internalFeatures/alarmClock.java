package ccc.interaction.internalFeatures;

import java.util.ArrayList;

import javax.swing.SwingWorker;

import ccc.interaction.global.popUpBox;
import ccc.interaction.global.database.DateAndTime;
import ccc.mainComponent.UI.alarmPanel;
import ccc.mainComponent.experimental.DEBUG;

public class alarmClock implements Runnable{
	
	public static ArrayList<String> alarm = new ArrayList<String>();
	private static boolean terminator = true;
	private static boolean defaultFormat;
	private soundBoard s = new soundBoard();

		@Override
		public void run() {
			
			DEBUG.print("alarmClock BETA activated.");
			while(terminator ==true) {

				for(int j = 0; j<alarm.size(); j++) {
					
					String[] checkFormat = alarm.get(j).split(":");
					//DEBUG.print(alarm.get(0));
					int day = Integer.valueOf(checkFormat[1]);
					int month = Integer.valueOf(checkFormat[0]);
					int year = Integer.valueOf(checkFormat[2]);

					String hour = checkFormat[3];
					String min = checkFormat[4];
					String sec = checkFormat[5];
					//System.out.println(hour);
					String repeat = checkFormat[6];
					String alarmName = checkFormat[7];

					//String alarmed = checkFormat[6];
					//08:25:2000:08:25:00:repeat:not
					
					//patch 2!=02
					if(Integer.valueOf(hour)<10) {
						hour = 0+hour;
					}
					if(Integer.valueOf(min)<10) {
						min = 0+min;
					}
					if(Integer.valueOf(sec)<10) {
						sec = 0+sec;
					}
					

					
					String data = DateAndTime.getDAT();
					data = data.replaceAll("/", ":");
					data = data.replaceAll(" ", ":");
					String dF[] = data.split(":");
					
					if(Integer.valueOf(dF[2])<10) {
						dF[2] = dF[2].substring(1);
					}
					if(Integer.valueOf(dF[1])<10) {
						dF[1] = dF[1].substring(1);
					}
					
					//DEBUG.print(dF[0] + "/" + dF[1] + "/" + dF[2]);
					//DEBUG.print(year + "/" + month + "/" + day);
					
					
					if(Integer.valueOf(dF[0])>1000) {
						defaultFormat = true;
					}
					
					
					if(defaultFormat == true) {
						if(checkFormat[6].toString().equals("noRepeat")) {
							if(dF[0].toString().equals(year+"")) {
								if(dF[1].toString().equals(month+"")) {
									if(dF[2].toString().equals(day+"")) {
										if(dF[3].toString().equals(hour+"")) {
											if(dF[4].toString().equals(min+"")) {
												if(dF[5].toString().equals(sec+"")) {
													boolean runned = false;
													if(runned == false) {
														SwingWorker<Void, Void> textReader = new SwingWorker<Void, Void>()
											            {
											             protected Void doInBackground() throws Exception
											             {
											            	 //popUpBox.alertError("DEBUG", "DEBUG");
											            	 new alarmClockBeep(alarmName);
											              return null;
											             }
											            };
											            textReader.execute();
											            runned = true;
													}
												}
											}
										}
									}
								}
							}
							
						}else if(checkFormat[6].toString().equals("Repeat")){
						if(dF[3].toString().equals(hour+"")) {
							if(dF[4].toString().equals(min+"")) {
								if(dF[5].toString().equals(sec+"")) {
									if(checkFormat.length>7) {
										if(checkFormat[7].toString().equals("wake")){
											SwingWorker<Void, Void> textReader = new SwingWorker<Void, Void>()  {
												protected Void doInBackground() throws Exception{
													new alarmClockBeep(alarmName);
											        return null;}};
											textReader.execute();
										}
									}else{
										SwingWorker<Void, Void> textReader = new SwingWorker<Void, Void>()  {
											protected Void doInBackground() throws Exception{
												new alarmClockBeep(alarmName);
										        return null;}};
										textReader.execute();
									}
										
									}
								}
							}
						}
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		

	public static ArrayList<String> getAlarm() {
		return alarm;
	}

	public static void setAlarm(String alarm) {
		DEBUG.print("DEBUG : Alarm added");
		alarmClock.alarm.add(alarm);
	}

}
