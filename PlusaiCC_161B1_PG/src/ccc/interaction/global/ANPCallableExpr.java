package ccc.interaction.global;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ccc.interaction.internalFeatures.soundBoard;
import ccc.mainComponent.globalID;
import ccc.mainComponent.experimental.DEBUG;
import servicePackage.stabilizer;

public class ANPCallableExpr extends autoNotificationPusher{
	
	private List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
	private popUpBoxExprt popExpr;
	private soundBoard callableSoundBoard;
	private ExecutorService ex = Executors.newFixedThreadPool(2);
	Thread thread = null;
	
	protected void CallableStore(String control,String title, String text) {

		
		/*if(thread==null) {
			thread = new Thread(new ccc.interaction.global.addons.external_freeTTS());
			thread.start();
		}*/
		
		Callable<Void> sounding = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				callableSoundBoard = new soundBoard();
				callableSoundBoard.readLineDefaultExpr(text);
				return null;
			}
			
		};
		
		taskList.add(sounding);
		if(control.equals("popUpInfo")) {
			Callable<Void> popUpInfo = new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					popExpr = new popUpBoxExprt(text, title, "INFO" , ex, callableSoundBoard);
					return null;
				}
			};
			taskList.add(popUpInfo);}
		
		if(control.equals("popUpWarning")) {
			Callable<Void> popUpWarning = new Callable<Void>() {
				
				@Override
				public Void call() throws Exception {
					popExpr = new popUpBoxExprt(text, title, "WARNING" ,ex, callableSoundBoard);
					return null;
				}
			};
			taskList.add(popUpWarning);}
		
		if(control.equals("popUpWarning1")) {
			Callable<Void> popUpWarning = new Callable<Void>() {
				
				@Override
				public Void call() throws Exception {
					popExpr = new popUpBoxExprt(text, title, "WARNING1" ,ex, callableSoundBoard);
					return null;
				}
			};
			taskList.add(popUpWarning);}
		
		
		if(control.equals("popUpError")) {
			Callable<Void> popUpError = new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					popExpr = new popUpBoxExprt(text, title, "ERROR" ,ex, callableSoundBoard);
					return null;
				}
			};
			
			globalID.errorPopC++;
			taskList.add(popUpError);}
			
		try {
		//	System.out.println("reached");
			ex.invokeAll(taskList);
			if(stabilizer.getAddOnsStable()==true) {
				ex.shutdownNow();
			}else {
				popUpBox.warningBox("Executor Service is not stopping due to stabilizer for add-ons is false.", "Unstable Thread");
			}
		}catch(InterruptedException e){
			
		}
	}
	
	public void stop() {
		ex.shutdownNow();
		//callableSoundBoard.readLineInterrupt();
	}
	
	
}
