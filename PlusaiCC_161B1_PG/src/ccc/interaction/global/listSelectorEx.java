package ccc.interaction.global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class listSelectorEx {
	private javax.swing.DefaultComboBoxModel<String> boxBody = new DefaultComboBoxModel<String>();
	public ArrayList<String> list = new ArrayList<String>();
	public int selection = 0;
	
	public String boxLoad(String name, String boxName, String[] lists, String[] storeLists) {
		ArrayList<String> holder1 = new ArrayList<String>(Arrays.asList(lists));
		ArrayList<String> holder2 = new ArrayList<String>(Arrays.asList(storeLists));
		return boxLoad(name,boxName,holder1,holder2);
	}
	public String boxLoad(String name, String boxName, ArrayList lists, ArrayList storeLists) {
		
			list = storeLists;
			
			JPanel listsPanel = new JPanel();
			listsPanel.add(new JLabel(boxName));
			JComboBox boxLoader = new JComboBox(boxBody);
			listsPanel.add(boxLoader);
			
			Set<String> primes = new LinkedHashSet<String>(lists);
			lists.clear();
			lists.addAll(primes);
			
			for(int i = 0; i<lists.size(); i++) {
				String[] data = new String[1];
				data[0] = (String) lists.get(i);
				boxBody.addElement(data[0]);
			}
			
			int actionChosenHandler = JOptionPane.showConfirmDialog(null, listsPanel,name,JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
			switch(actionChosenHandler) {
			case JOptionPane.OK_OPTION : optionSelected(actionChosenHandler, boxLoader);}
		String selectioned = list.get(selection);
		return selectioned;
		
	}

	private void optionSelected(int actionChosenHandler, JComboBox boxLoader) {
		selection = boxLoader.getSelectedIndex();
		
	}
}