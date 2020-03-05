package ccc.mainComponent.experimental;

public final class DEBUG {
	public static boolean enableDebug = true;
	public static void print(String object) {
		if(enableDebug==true)
		System.out.println(object);
	}

}
