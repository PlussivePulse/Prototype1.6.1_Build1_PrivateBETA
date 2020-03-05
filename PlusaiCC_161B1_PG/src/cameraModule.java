

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.opencv.core.Core;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import ccc.interaction.global.components.internalFileBridge;
import ccc.mainComponent.UI.mainRunnerCore;
import dataInteractingAbility.filesCore;

public class cameraModule extends JFrame{
	private static FFmpegFrameRecorder recorder = null;
	private static FrameGrabber grabber = null;
	private static final int WEBCAM_DEVICE_INDEX = 0;
	private static final int frameWidth = 0;
	private static final int frameHeight = 0;
	private static final int frameRate = 30;
	private static final int frameGOP = 60;
	private Catcher catcher;
	private boolean runnable = true;
	//public JPanel canvas;
	
	public cameraModule() {
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		
		File target = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName + "\\" + "openCV_Driver");
		System.load(target.getAbsolutePath()+"\\" + Core.NATIVE_LIBRARY_NAME + ".dll");
		System.load(target.getAbsolutePath()+"\\" + "opencv_ffmpeg410_64" + ".dll");
		//System.load(target.getAbsolutePath()+"\\" + "lib\\libopenblas.lib");
    	//System.loadLibrary("opencv_ffmpeg300_64");
		grabber = new OpenCVFrameGrabber("");
		catcher = new Catcher();
	}
	
	public static void main(String args[]) {
		new cameraModule();
	}
	
	class Catcher implements Runnable {



		@Override
		public void run() {
			synchronized (this) {
				try {
					grabber.setImageWidth(frameWidth);
					grabber.setImageHeight(frameHeight);
					grabber.start();
					recorder = new FFmpegFrameRecorder("output.mp4",frameWidth+100,frameHeight+100);
					recorder.setInterleaved(true);
					recorder.setFormat("mp4");
					recorder.setFrameRate(frameRate);
					recorder.setGopSize(frameGOP);
					
					recorder.start();
					
					////////////////////////////////////////////////
					
					Frame capturedFrame = null;
					Java2DFrameConverter paint = new Java2DFrameConverter();
					long startTime = System.currentTimeMillis();
					long frame = 0;
					while((capturedFrame = grabber.grab()) != null && runnable == true) {
						BufferedImage b = paint.getBufferedImage(capturedFrame,1);
						Graphics g = mainRunnerCore.cameraSP.getGraphics();
						g.drawImage(b, 0, 0, frameWidth, frameHeight, 0, 0, b.getWidth(), b.getHeight(),null);
						recorder.record(capturedFrame);
						frame++;
						long waitMillis = 1000*frame/frameRate - (System.currentTimeMillis() - startTime);
						while (waitMillis <=0 ) {
							recorder.record(capturedFrame);
							frame++;
							waitMillis = 1000*frame/frameRate - (System.currentTimeMillis() - startTime);
						}
						Thread.sleep(waitMillis);
					}
				
				}catch(FrameGrabber.Exception ex){
					
				} catch (Exception e) {
				
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
	}


}

