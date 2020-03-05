package ccc.interaction.global.addons;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import ccc.interaction.internalFeatures.soundBoard;

public class external_freeTTS extends soundBoard{
	
	private String text = "";
	@SuppressWarnings("static-access")
	public void readText(String text) {
		if(this.text.equals(text)) {
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			
			VoiceManager voiceCore = VoiceManager.getInstance();
			com.sun.speech.freetts.Voice voice = voiceCore.getInstance().getVoice("kevin16");
			//com.sun.speech.freetts.Voice voice = voiceCore.getInstance().getVoice("mbrola_us1");
			
			voice.allocate();
			voice.setPitch(160);
			voice.setRate(150);
			voice.speak(this.text);
		}
	}
	
	public void setText(String text) {
		this.text = text;
	}

	
}
