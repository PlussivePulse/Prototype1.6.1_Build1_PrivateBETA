package servicePackage;

import java.text.NumberFormat;

public class localDeviceServicesStabilizer implements Runnable{
	private Thread lDSStabilizer;
	private StringBuilder stringObject;
	private Runtime runtime;
	NumberFormat formatter = NumberFormat.getInstance();
	private double USAGE_PERCENT;
	private int AVAILABLE_PERCENT;
	private static int FREE_MEMORY;
	private static int ALLOCATED_MEMORY;
	
	public static int MAX_MEMORY;
	
	public localDeviceServicesStabilizer() {
	}
	
	public void run() {
		stringObject = new StringBuilder();
		runtime = Runtime.getRuntime();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		
		MAX_MEMORY = (int) (maxMemory / 1024);
		ALLOCATED_MEMORY = (int) (allocatedMemory/1024);
		FREE_MEMORY = (int) (freeMemory/1024);
		//System.out.println(FREE_MEMORY+(MAX_MEMORY - ALLOCATED_MEMORY) /1024);
		USAGE_PERCENT = (freeMemory/maxMemory);
		AVAILABLE_PERCENT = runtime.availableProcessors();
		System.out.println(AVAILABLE_PERCENT);
	}
	
}
