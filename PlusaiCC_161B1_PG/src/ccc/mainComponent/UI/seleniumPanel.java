package ccc.mainComponent.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class seleniumPanel extends JFrame{

	public static JFrame frame;
	private static JScrollPane scrollPane;
	protected static Dimension sd;
	public static JTextPane panelDisplayer;
	public static boolean initialized = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the application.
	 */
	public seleniumPanel() {
		initialize();
		frame.setVisible(true);
		initialized = true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				sd = Toolkit.getDefaultToolkit().getScreenSize();
			}
		});
		frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				boolean isMaximized = isMaximized(e.getNewState());
				boolean wasMaximized = isMaximized(e.getOldState());
				
				if(isMaximized && !wasMaximized) {
					//maximized
					resizeToFull();
				}
			}

			private boolean isMaximized(int State) {
				return (State & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;
			}
		});
		frame.setBounds(100, 100, 720, 440);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane mainTab = new JTabbedPane(JTabbedPane.TOP);
		mainTab.setBounds(10, 11, 568, 383);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(mainTab, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		mainTab.addTab("Panel", null, panel, null);
		panel.setLayout(new BorderLayout());
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 563, 355);
		panel.add(scrollPane);
		
		panelDisplayer = new JTextPane();
		scrollPane.setViewportView(panelDisplayer);
	}

	protected void resizeToFull() {
		scrollPane.setBounds(10, 11, (int) sd.getWidth()-10, (int) sd.getWidth()-11);
		
	}
	
	public static void submit(JTextPane pane, String string, Color color) {
		//credit : nIcE cOw
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
	
		int carret = pane.getDocument().getLength();
		pane.setCaretPosition(carret);
		pane.setCharacterAttributes(aset, false);
		pane.replaceSelection(string + "\n");
	}
}
