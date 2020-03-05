package dataInteractingAbility.processesNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.Line;

public class ProcessesNet {

	public static ArrayList getProcess() throws IOException {
		String stringLine;
		ArrayList taskStore = new ArrayList();
		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\"+"taskList.exe");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
		while((stringLine = input.readLine()) != null){
			taskStore.add(stringLine);
			//System.out.println(stringLine);
		}
		
		ArrayList arrayFiltered = filter(taskStore);
		return arrayFiltered;
	}
	
	public static String getProcessPanel() throws IOException {
		String stringLine;
		String result = null;
		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\"+"taskList.exe");
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
	
		while((stringLine = input.readLine()) != null){
			if(result == null) {
				result = stringLine;
			}else {
				result += "\n" + stringLine;
			}
			
		}

		return result;
	}
	
	public static ArrayList filter(ArrayList arrays) {
		ArrayList filtered = new ArrayList();
		for(int i = 0; i<arrays.size(); i++) {
			String[] store = new String[1];
			store[0] = (String) arrays.get(i);
			String newstore = store[0];
			String filterStore[] = store[0].split(" +");
			for(int j = 0; j<filterStore.length; j++) {
				if(filterStore[j].contains(".exe")) {
					filtered.add(filterStore[j]);
				}
			}

		}
		
		return filtered;
	}
}
