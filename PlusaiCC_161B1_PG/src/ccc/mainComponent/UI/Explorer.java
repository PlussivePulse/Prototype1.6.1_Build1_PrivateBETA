package ccc.mainComponent.UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public final class Explorer extends JFrame{

	public Explorer() throws HeadlessException{
		super();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Explorer");
		setLayout(new BorderLayout());
		add(getSplitPane(), "Center");
		
	}

	private JSplitPane getSplitPane() {
		JTreePanelTree leftPane = getJTreeContainer();
		FILEexplorer rightpane = getPanelContainer();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		return splitPane;
	}
	
	private FILEexplorer getPanelContainer() {
		return new FILEexplorer();
	}
	
	private JTreePanelTree getJTreeContainer() {
		JTreePanelTree tree = new JTreePanelTree();
		return tree;
	}
	
	public static void start() {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				Explorer explr = new Explorer();
				explr.setVisible(true);
				explr.pack();
			}
		});
	}
}
