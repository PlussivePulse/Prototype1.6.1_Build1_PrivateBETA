package ccc.interaction.internalFeatures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.global.cmdExecute;
import dataInteractingAbility.processesNet.ProcessesNet;

public class processControl {
	private static String processTargetName;
	private static boolean processSelected = false;
	private static javax.swing.DefaultComboBoxModel<String> boxBody = new DefaultComboBoxModel<String>();
	
	public static String boxLoad() {
		ArrayList processList = null;
		try {
			processList = ProcessesNet.getProcess();
		} catch (IOException e) {
			autoNotificationPusher.notPush("ERROR", "", "IOException", Class.class.getClass(), "AUTO");
			e.printStackTrace();
		}
		
		JPanel processPanel = new JPanel();
		processPanel.add(new JLabel("Running Process : "));
		JComboBox boxLoader = new JComboBox(boxBody);
		processPanel.add(boxLoader);
		
		Set<String> primes = new LinkedHashSet<String>(processList);
		
		processList.clear();
		processList.addAll(primes);
		
		for(int i = 0; i<processList.size(); i++) {
			String[] data = new String[1];
			data[0] = (String) processList.get(i);
			boxBody.addElement(data[0]);
		}
		int actionChosenHandler = JOptionPane.showConfirmDialog(null, processPanel,"Task List Selection",JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

		switch(actionChosenHandler) {
		case JOptionPane.OK_OPTION : optionSelected(actionChosenHandler, boxLoader);}
		
		return processTargetName;
	}

	private static void optionSelected(int actionChosenHandler, JComboBox boxLoader) {
		processTargetName = String.valueOf(boxLoader.getSelectedItem());
		processSelected  = true;
		//System.out.println(processTargetName);
		
	}
	
	public static void killProcessMenu() {
		boxLoad();
		cmdExecute.execute("taskkill /F /IM "+ processTargetName);
	}
}
