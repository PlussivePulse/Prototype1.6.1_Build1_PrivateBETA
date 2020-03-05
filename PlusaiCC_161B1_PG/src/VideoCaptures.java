import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import ccc.interaction.global.components.internalFileBridge;
import dataInteractingAbility.filesCore;

import org.opencv.highgui.HighGui;

public class VideoCaptures {
    public static void main (String args[]){
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		
		File target = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName + "\\" + "openCV_Driver");
		System.load(target.getAbsolutePath()+"\\" + Core.NATIVE_LIBRARY_NAME + ".dll");
    	VideoCapture camera = new VideoCapture(0);
    	
    	if(!camera.isOpened()){
    		System.out.println("Error");
    	}
    	else {
    		Mat frame = new Mat();
    	    while(true){
    	    	if (camera.read(frame)){
    	    		System.out.println("Frame Obtained");
    	    		System.out.println("Captured Frame Width " + 
    	    		frame.width() + " Height " + frame.height());
    	    		Imgcodecs.imwrite("C:\\Users\\Pakin H Kajon\\Desktop\\BestFit\\test.png", frame);    		
    	    		System.out.println("OK");
    	    		break;
    	    	}
    	    }	
    	}
    }
}