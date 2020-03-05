package servicePackage;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.FileUtils;

import ccc.interaction.global.popUpBox;
import ccc.interaction.global.addons.wSDotCamDownloader;
import ccc.interaction.global.components.internalFileBridge;
import ccc.interaction.global.database.DateAndTime;
import ccc.interaction.internalFeatures.alarmClock;
import ccc.mainComponent.Systems;
import ccc.mainComponent.globalID;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.UI.mainRunnerCore.checkMU;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;
import dataInteractingAbility.writeText;

public class shutdownSequence {
	public static Color customColor;
	
	public void shutdown(String caseEX) {
		Thread s = new Thread() {
			private boolean processC = true;
			private int i = 0;
			@Override
			public void run() {
				while(processC) {
					for(;i<6; i++) {
						if(i==0) {
							mainRunnerCore.resetLoadBar(6);
							mainRunnerCore.setTabbedPane(2);
							if(caseEX.equals("shutdown")) {
								customColor = new Color(18,95,135);
								mainRunnerCore.displayer.setForeground(Color.white);
							}else if(caseEX.equals("emergencyStop")) {
								customColor = new Color(0,0,0);
								mainRunnerCore.displayer.setForeground(Color.white);
							}
							if(customColor != null) {
								mainRunnerCore.displayer.setBackground(customColor);
							}
							mainRunnerCore.addBarLoad("System : Shutdown sequence initiated.", 0.1);
						}else if(i==1){
						
						}else if(i==2){
							mainRunnerCore.addBarLoad("System : Stopping all module and application.", 0.1);
							mainRunnerCore.setTerminator(false);
							mainRunnerCore.addBarLoad("System : Completed stopping all module and application.", 0.1);
						}else if(i==3){
							mainRunnerCore.addBarLoad("System : cleaning up start up files.", 0.1);
							try {
								FileUtils.cleanDirectory(new File(new File(filesCore.currentLocation).getParent()+"\\AI6CoreFolder\\seleniumDriver"));
								Files.delete(Paths.get(new File(filesCore.currentLocation).getParent()+"\\AI6CoreFolder\\seleniumDriver"));
								FileUtils.cleanDirectory(new File(new File(filesCore.currentLocation).getParent()+"\\AI6CoreFolder\\openCV_Driver"));
								Files.delete(Paths.get(new File(filesCore.currentLocation).getParent()+"\\AI6CoreFolder\\openCV_Driver"));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							mainRunnerCore.addBarLoad("System : cleaned all start up files.", 0.1);
						}else if(i==4) {
							mainRunnerCore.addBarLoad("System : Completed! - Shutting down...\n", 0.1);
							try {
								Thread.sleep(750);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}else if(i==5) {
							mainRunnerCore.frmAiProject.dispose();
							System.exit(0);

						}
						try {
							Thread.sleep(750);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					processC = false;
				}
			}
		};
		s.start();

	}
	
}
