package ccc.interaction.global.addons;

//A6 experimental addon for autoNotificationPusher

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public final class ANPQuestionHolder extends ccc.interaction.global.popUpBox{
	//private static JDialog holder;
	
	
	public String questionYN(String question, String title) {
		String value = "";
	
		JOptionPane holder = new JOptionPane();
		//holder.setDefaultLookAndFeelDecorated(true);
		
		@SuppressWarnings("static-access")
		int askHolder = holder.showConfirmDialog(null,  question, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(askHolder == JOptionPane.NO_OPTION) {
			value =  "NO";
		}else if(askHolder == JOptionPane.YES_OPTION) {
			value = "YES";}
		return value;
	}
	
	public static String questionInput(String question, String title) {
		JFrame frame = new JFrame();
		String returned = JOptionPane.showInputDialog(frame, question, title);
		return returned;
	}
}
