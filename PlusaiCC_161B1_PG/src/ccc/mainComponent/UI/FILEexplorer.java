package ccc.mainComponent.UI;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class FILEexplorer implements TreeSelectionListener {
	private JPanel mainPanel = new JPanel();
	private JLabel info;
	private JTree treeObj;
	private File[] directory;
	
	public FILEexplorer() {
		super();
		initialise();
	}

	public void initialise() {
		info = new JLabel("Explorer Panel");
		mainPanel.add(info);
		
	}
	
	public JPanel getFilePanel() {
		return mainPanel;
	}

	@Override
	public void valueChanged(TreeSelectionEvent tsl) {
		if(tsl.getNewLeadSelectionPath() != null) {
			info.setText(tsl.getNewLeadSelectionPath().getLastPathComponent().toString());
		}
		
	}
	
}
