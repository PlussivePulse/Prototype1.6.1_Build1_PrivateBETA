package internalSecurity;

public class staticAccessIS{
	
	public static void emergencyStop() {
		emergencyStop.oncePop();
	}

	public static void emergencyStop(String string) {
		emergencyStop.oncePop(string);
		
	}
}
