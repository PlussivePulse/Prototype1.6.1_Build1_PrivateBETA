package ccc.interaction.internalFeatures;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.face.FaceRecognizer;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.QRCodeDetector;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import ccc.interaction.global.popUpBox;
import ccc.interaction.global.components.internalFileBridge;
import ccc.interaction.global.database.DateAndTime;
import ccc.mainComponent.globalID;
import ccc.mainComponent.UI.mainRunnerCore;
import ccc.mainComponent.experimental.DEBUG;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;
import servicePackage.shutdownSequence;

public class cameraFrame extends JFrame{
	
	private static boolean camTerminator;
	private static VideoCapture mainCamera = null;
	private static VideoCapture capture = null;
	private Mat frame;
	private MatOfByte m;
	public int saveSwtich;
	public Graphics g;
	public VideoWriter videoWriter;
	
	public Mat temp1;
	public Mat diff_Mat;
	
	public CascadeClassifier faceDetector;
	public MatOfRect faceDetections;
	
	public void setting() {
		
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		
		File target = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName + "\\" + "openCV_Driver");
		System.load(target.getAbsolutePath()+"\\" + Core.NATIVE_LIBRARY_NAME + ".dll");

		frame = new Mat();
		m = new MatOfByte();
		faceDetector = new CascadeClassifier(new String(target.getAbsolutePath()).replace("\\", "/") + "/haarcascade_frontalface_alt.xml");
		faceDetections = new MatOfRect();
		qrReader = new QRCodeDetector();
		qrBox = new MatOfPoint();
		//train();
	}
	
	public FrameRunner camera = null;
	private Mat image;
	private LBPHFaceRecognizer faceRecognizer;
	public String saveQRLink;
	public static boolean faceDetectionOption = true;
	public QRCodeDetector qrReader;
	public MatOfPoint qrBox;
	public Mat temp_frame;
	public ArrayList<Rect> diffArray;

	
	public void start(int cam) {
		if (camTerminator == true) {
			stop();
		}
		saveSwtich = cam;
		setting();
		mainCamera = new VideoCapture(cam);
		//mainCamera.set(Videoio.CAP_PROP_FRAME_WIDTH, Videoio.CAP_PROP_FRAME_WIDTH);
		//mainCamera.set(Videoio.CAP_PROP_FRAME_HEIGHT, Videoio.CAP_PROP_FRAME_HEIGHT);
		//mainCamera.set(Videoio.CAP_PROP_FRAME_WIDTH, 1920);
		//mainCamera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 1280);
		camera = new FrameRunner();
		Thread thread = new Thread(camera);
		thread.setDaemon(true);		
		camTerminator = true;
		thread.start();
	}
	
	public static void stop() {
		camTerminator = false;
		mainCamera.release();
	}
	
	public void swap() {
		if(saveSwtich == 0) {
			start(1);
		}else {
			start(0);
		}
	}
	
	////////////////////////////////////////////////////////////
	public void videoWrite(Mat mat) {
		if(videoWriter.isOpened()==false) {
			videoWriter.release();
		}
		videoWriter.write(mat);
	}
	
	////////////////////////////////////////////////////////////
	
	/*public void train() {
		ArrayList <Mat> sourceImages = new ArrayList<>();
		List<String> namesIndexList = new ArrayList<>();
		List<Integer>namesIntList = new ArrayList<>();
		
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		
		File target = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName  + "\\Database\\FaceRegcognize");
		DEBUG.print(target.getAbsolutePath());
		try {
			Files.list(Path.of(target.getAbsolutePath())).forEach(file -> {
			    String filename = file.getFileName().toString();
			    if (filename.contains("-") && filename.endsWith("jpg")){
			        String personName = filename.substring(0, filename.indexOf("-")); // e.g. edd from edd-1.jpg
			        if (!namesIndexList.contains(personName)){
			            namesIndexList.add(personName);
			        }
			        image = Imgcodecs.imread(file.toString());
			        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY); // Convert image to grayscale or it won't work with the face learner
			        sourceImages.add(image);
			        namesIntList.add(namesIndexList.indexOf(personName));
			    }
			});
			faceRecognizer = LBPHFaceRecognizer.create();
			MatOfInt matOfInt = new MatOfInt();
			matOfInt.fromList(namesIntList);
			faceRecognizer.train(sourceImages, matOfInt);
		} catch (IOException e) {
			e.printStackTrace();
		}//credit azquo training code

	
	}*/
	
	public void capture() {
    	if(!mainCamera.isOpened()){
    	}
    	else {
    		//train();
			this.stop();
			capture = new VideoCapture(saveSwtich);
			capture.set(Videoio.CAP_PROP_FRAME_WIDTH, mainRunnerCore.screenSize.getWidth());
			capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, mainRunnerCore.screenSize.getHeight());
			this.start(saveSwtich);
			
    	    while(true){
    	    	
    	    	String data = DateAndTime.getDAT();
				data = data.replaceAll("/", "");
				data = data.replaceAll(":", "");
				data = data.replaceAll(" ", "");
				
				String MyPhoto = System.getProperty("user.home");
				capture.set(Videoio.CAP_PROP_FPS, 60);
    	    	if (capture.read(frame)){
    	    		String destination = MyPhoto + "\\Pictures\\" + data + ".png"; 	
    	    		Imgcodecs.imwrite(destination, frame); 
   				 SwingWorker<Void, Void> submitter = new SwingWorker<Void, Void>(){
						protected Void doInBackground() throws Exception{
							URL sound = this.getClass().getClassLoader().getResource("soundLibraryFiles/cameraShuuter.wav");
							try {
								AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
								Clip clip = AudioSystem.getClip();
								try {Thread.sleep(1000);} 
								catch (InterruptedException e) {e.printStackTrace();}
								clip.open(audio);
								clip.start();
							} catch (UnsupportedAudioFileException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} catch (LineUnavailableException e) {
								e.printStackTrace();
							} catch(Exception ex) {
								capture();
								
							}
		                 return null;
		                }
		               };
		               submitter.execute();
		    			capture.release();
    	    		break;

    	    	}

    	    }

    	}
    	this.start(saveSwtich);
	}
	
	class FrameRunner implements Runnable{

		



		private int picID;
		private int sessionCheck = 0;
		@Override
		public void run() {
				while(camTerminator) {
					if(mainCamera.grab()) {
						try {
							
							mainCamera.retrieve(frame);
							g = mainRunnerCore.cameraSP.getGraphics();
							
							if(faceDetectionOption == true) {
								faceDetector.detectMultiScale(frame, faceDetections);
								
								for(Rect rect : faceDetections.toArray()) {
									Imgproc.rectangle(frame, new Point(rect.x,rect.y), new Point (rect.x + rect.width, rect.y + rect.height), new Scalar(255,204,102));
								}
							}
							
								String link = qrReader.detectAndDecode(frame);
								saveQRLink = link;
		
								
								if(link.equals("")) {
									//saveQRLink = "";
								}else {
									//saveQRLink = ",QR-Code Scan = " +link;
									if(!link.equals(saveQRLink)) {
										//show dialog box
									}
								}
/////////////////////////////////////////////////////////////////////////////////////////////////////
							/*
								temp1 = new Mat(frame.size(), CvType.CV_8UC1);
								Imgproc.cvtColor(frame, temp1, Imgproc.COLOR_BGR2GRAY);
								Imgproc.GaussianBlur(temp1, temp1, new Size(3,3), 0);
								
								if(sessionCheck==0) {
									diff_Mat = new Mat(temp1.size(), CvType.CV_8UC1);
									temp_frame = new Mat(temp1.size(), CvType.CV_8UC1);
									diff_Mat = temp1.clone();
								}
								
								if(sessionCheck==1) {
									Core.subtract(temp1, temp_frame, diff_Mat);
									Imgproc.adaptiveThreshold(diff_Mat, diff_Mat, 255,
											Imgproc.ADAPTIVE_THRESH_MEAN_C,
											Imgproc.THRESH_BINARY_INV,
											5, 2);
									diffArray = moveDetection(diff_Mat);
									
									if(diffArray.size() > 0) {
										Iterator<Rect> it = diffArray.iterator();
										while(it.hasNext()) {
											Rect moveBox = it.next();
											Imgproc.rectangle(frame, moveBox, new Scalar(0,255,0), 1);
										}
									}
							}
								
								sessionCheck = 1;
								
								
								
								/////////////////////////////////////////////////////////////////////
								
								temp_frame = temp1.clone();
								
								*/
								
							Imgcodecs.imencode(".bmp", frame, m);

							Image im = ImageIO.read(new ByteArrayInputStream(m.toArray()));
							BufferedImage buff = (BufferedImage) im;
						
							if(saveSwtich==0) {
								g.drawImage(buff,mainRunnerCore.cameraSP.getWidth(),0,0, mainRunnerCore.cameraSP.getHeight()+75, 0, 0, buff.getWidth(), buff.getHeight(), null);
							}else {
								g.drawImage(buff,0,0,mainRunnerCore.cameraSP.getWidth(), mainRunnerCore.cameraSP.getHeight()+75, 0, 0, buff.getWidth(), buff.getHeight(), null);
							}
							
							mainRunnerCore.lblFDAmount.setText("Face Detection : " + faceDetections.toArray().length);
							if(camTerminator==false) {
								g.dispose();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}catch (Exception ex) {
							ex.printStackTrace();
						}
						
					}
				}
				
			}

	}
	
}
