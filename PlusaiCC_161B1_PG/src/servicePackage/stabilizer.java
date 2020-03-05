package servicePackage;

import ccc.interaction.global.autoNotificationPusher;
import ccc.interaction.internalFeatures.soundBoard;

public final class stabilizer {
	
	private static boolean stabilizer = true;
	private static boolean autoStabilizer = true;
	private final static String txtStabilizer = "Stabilizer System";
	private static boolean addOnsStabilizer = true; //prevent unstable add on from running.
	
	public static void setStabilizer(boolean value) {
		stabilizer = value;
		//announce voth
		if(soundBoard.muted==false)
		autoNotificationPusher.notPush("WARNING", "Stabilizer Alert :", "Stabilizer : " + value, Class.class.getClass(), "AUTO");
	}
	
	protected void setAS(boolean Value) {
		autoStabilizer = Value;
	}
	
	public static String getS1() {
		return txtStabilizer;
	}
	
	public static boolean getStabilizerValue() {
		return stabilizer;
	}
	
	public static boolean getASValue() {
		return autoStabilizer;
	}
	
	public static boolean getAddOnsStable() {
		return addOnsStabilizer;
	}
	
	public static void startlDSS() {
		Thread internal_lDSS = new Thread(new localDeviceServicesStabilizer(), "");
		internal_lDSS.start();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
