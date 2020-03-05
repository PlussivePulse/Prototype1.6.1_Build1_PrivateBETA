package ccc.interaction.global;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class notificationFrame extends globalInteractionService{
	
	private static javax.swing.JFrame nFrame;
	protected static boolean activation = true;

	protected static void enable() {
		globalInteractionService.FrameAC = true;
	}
	protected notificationFrame() {
		activation = true;
	}
	
	private static String[] sizeCalculator(String input) {
		String textSplit[] = input.split("\n");
		String[] frameData = new String[textSplit.length+3];
		int longestTextGroup = 0;
		for(int i = 2; i<frameData.length-1; i++) {
			int wordLength = textSplit[i-2].length();
			if(longestTextGroup<wordLength) {longestTextGroup=wordLength;}
			frameData[i] = textSplit[i-2];
		}
		frameData[0] = Integer.toString((int) (longestTextGroup*(12.5*1.00)));
		frameData[1] = Integer.toString((int) textSplit.length*(10*4));
		frameData[frameData.length-1] = Integer.toString((int)(longestTextGroup));
		return frameData;
	}
	
	private static String groupText(String[] text) {
		String totalText = text[2];
		for(int i = 2; i<text.length-2; i++) {
			totalText = totalText + " <br/> " + text[i+1];
		}
		return totalText;
	}
	
	public static void generate(String label) {
		
		if(label.equals("")) label = "emptyLabelDefaultGenerate";
		nFrame = new javax.swing.JFrame("notificationFrame");
		nFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nFrame.setLayout(new java.awt.BorderLayout());
		String[] data = sizeCalculator(label);
		label = groupText(data);
		int width = (int) Integer.parseInt(data[0]);
		int height = (int) Integer.parseInt(data[1]);
		if(data.length > 4) {label = "<html>" + label + "</html>";}
		javax.swing.JLabel labelData = new javax.swing.JLabel(label);
		//labelData.setHorizontalAlignment(JLabel.CENTER);
		nFrame.add(labelData);
		nFrame.setUndecorated(true);
		nFrame.setResizable(false);
		nFrame.setSize(width,height);
		System.out.println("DEBUG");
		if(activation==true) {
			nFrame.setVisible(true);
			nFrame.toFront();
		}

		Timer localTimer = new Timer(2000, new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {nFrame.dispose();}});
			localTimer.setRepeats(false);
			
			nFrame.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseExited(java.awt.event.MouseEvent e) {localTimer.restart();}
				@Override
				public void mouseEntered(java.awt.event.MouseEvent e) {localTimer.stop();}
		});
	}

	protected void emergencyStopScreenCT() {
		JFrame eFrame = new javax.swing.JFrame("emergencyFrame");
		eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eFrame.setLayout(new java.awt.BorderLayout());
		javax.swing.JLabel labelData = new javax.swing.JLabel("<html><font color = 'white'>Security System has detected unautorize code modification in this software.</html>", SwingConstants.CENTER);
		eFrame.setUndecorated(true);
		eFrame.add(labelData, SwingConstants.CENTER);
		eFrame.setResizable(false);
		eFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		eFrame.getContentPane().setBackground(Color.black);
		eFrame.setVisible(true);
		eFrame.toFront();

	}
	
	protected void custom_emergencyStopScreenCT(String input) {
		JFrame eFrame = new javax.swing.JFrame("emergencyFrame");
		eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eFrame.setLayout(new java.awt.BorderLayout());
		javax.swing.JLabel labelData = new javax.swing.JLabel("<html><font color = 'white'>" + input + "</html>", SwingConstants.CENTER);
		eFrame.setUndecorated(true);
		eFrame.add(labelData, SwingConstants.CENTER);
		eFrame.setResizable(false);
		eFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		eFrame.getContentPane().setBackground(Color.black);
		eFrame.setVisible(true);
		eFrame.toFront();

	}
}
