package ccc.interaction.global;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dataInteractingAbility.processesNet.ProcessesNet;

public class allBoxLoader extends globalInteractionService {
	private static javax.swing.DefaultComboBoxModel<String> boxBody = new DefaultComboBoxModel<String>();
	private static String optionSelectedValue;
	private static boolean optionSelected;
	
	protected static int boxLoad(ArrayList lists) {
		
		JPanel processPanel = new JPanel();
		String eltitle = "Element : ";
		if(lists.size()>1) {
			eltitle = "All Elements : ";
		}
		processPanel.add(new JLabel(eltitle));
		JComboBox boxLoader = new JComboBox(boxBody);
		processPanel.add(boxLoader);
		
		Set<String> primes = new LinkedHashSet<String>(lists);
		
		lists.clear();
		lists.addAll(primes);
		
		for(int i = 0; i<lists.size(); i++) {
			String[] data = new String[1];
			data[0] = (String) lists.get(i);
			boxBody.addElement(data[0]);
		}
		int actionChosenHandler = JOptionPane.showConfirmDialog(null, processPanel,"Selector",JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

		switch(actionChosenHandler) {
		case JOptionPane.OK_OPTION : optionSelected(actionChosenHandler, boxLoader);}
		
		return actionChosenHandler;
	}
	
	protected static int boxLoad(String[] lists) {
		
		JPanel processPanel = new JPanel();
		String eltitle = "Element : ";
		if(lists.length>1) {
			eltitle = "All Elements : ";
		}
		processPanel.add(new JLabel(eltitle));
		JComboBox boxLoader = new JComboBox(boxBody);
		processPanel.add(boxLoader);
		
		for(int i = 0; i<lists.length; i++) {
			boxBody.addElement(lists[i]);
		}
		int actionChosenHandler = JOptionPane.showConfirmDialog(null, processPanel,"Selector",JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);

		switch(actionChosenHandler) {
		case JOptionPane.OK_OPTION : optionSelected(actionChosenHandler, boxLoader);}
		
		return actionChosenHandler;
	}
	
	private static void optionSelected(int actionChosenHandler, JComboBox boxLoader) {
		optionSelectedValue = String.valueOf(boxLoader.getSelectedItem());
		optionSelected  = true;
	}

}
