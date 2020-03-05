package ccc.interaction.global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class TextReaderOne {
	private ArrayList textList = new ArrayList();
	
	public void loadText(File destinator) {
		File loader = destinator;
		BufferedReader readers = null;
		try {
			readers = new BufferedReader(new FileReader(loader));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		String holder = "";
		try {
			while ((holder = readers.readLine()) != null) {
				System.out.println(holder);
			}
		} catch (IOException e) {e.printStackTrace();}
	}
}
