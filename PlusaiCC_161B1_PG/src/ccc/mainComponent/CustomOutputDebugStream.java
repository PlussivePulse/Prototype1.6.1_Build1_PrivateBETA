package ccc.mainComponent;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CustomOutputDebugStream extends OutputStream {

	private JTextArea DebugArea;
	
	public CustomOutputDebugStream (JTextArea debugArea) {
		this.DebugArea = debugArea;
	}
	
	@Override
	public void write(int b) throws IOException {
		DebugArea.append(String.valueOf((char)b));
		DebugArea.setCaretPosition(DebugArea.getDocument().getLength());

	}

}
