package ccc.interaction.global;

import java.util.concurrent.ExecutorService;

import javax.swing.JOptionPane;

import ccc.interaction.internalFeatures.soundBoard;
import ccc.interaction.internalFeatures.warningBeep;

public class popUpBoxExprt extends globalInteractionService{

	private warningBeep w;

	public popUpBoxExprt(String warningMessage, String warnTitle, String type, ExecutorService ex, soundBoard callableSoundBoard) {
		javax.swing.JOptionPane noStaticPane = new javax.swing.JOptionPane();
		int typing = JOptionPane.INFORMATION_MESSAGE;
		if(type.equals("INFO")) {
			typing = JOptionPane.INFORMATION_MESSAGE;
		}else if(type.equals("WARNING")) {
			typing = JOptionPane.WARNING_MESSAGE;
		}else if(type.equals("ERROR")||(type.contentEquals("ALERT"))) {
			//soundBoard.playBeepDefault(1); //cause non working code.
			typing = JOptionPane.ERROR_MESSAGE;
		}else if(type.equals("WARNING1")) {
			//soundBoard.playBeepDefault(1); //cause non working code.
			typing = JOptionPane.WARNING_MESSAGE;
			w = new warningBeep(-1);

		}
		
		int test = noStaticPane.showOptionDialog(noStaticPane,warningMessage, warnTitle, 
				JOptionPane.DEFAULT_OPTION, typing, null, null, null);
		noStaticPane.setVisible(true);
		w.terminator = false;
		if(test == 0 || test == -1) { //attempt to stop reading but failed (Unstable)
			ex.shutdownNow();
			//callableSoundBoard.readLineInterrupt();
		}
	}
	
}
