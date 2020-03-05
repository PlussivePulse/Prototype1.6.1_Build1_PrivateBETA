package ccc.interaction.internalFeatures;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.imageio.ImageIO;

import ccc.interaction.global.autoNotificationPusher;
import ccc.mainComponent.regconizeCore.prefixTransformer;
import servicePackage.stabilizer;


public final class Base64EncoderStatic {
	
	//private static String type = ".png";
	private static Object c = null;
	private static String filePath = "";
	private static boolean commandRun = true;
	
	/*private static String PictureEncrypt2(String destination) {
		
		File image = new File(destination);
		String imgEcd = "null";
		
		try {
			
			BufferedImage bfImg = ImageIO.read(image);
			BufferedImage bffedImage = new BufferedImage(bfImg.getWidth(), bfImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			java.awt.Graphics2D g = bffedImage.createGraphics();
			g.drawImage(bfImg, 0, 0, null);
			g.dispose();
			
			byte[] imageString;
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

			ImageIO.write(bffedImage,type, byteStream);
			byte[] imageBytes = byteStream.toByteArray();
			
			imageString = Base64.getEncoder().encode(imageBytes);
			
			imgEcd = new String(imageString);
			byteStream.close();
			
		}catch(IOException e) {
			autoNotificationPusher.notPush("ALERT", "", "Picture Encryption Error.", Class.class.getClass(), "AUTO");
		}
		
		
		return imgEcd;
	}*/
	
	private static String PictureEncrypt(String destination) {
		
		File f = new File(destination);
		String code = "";
		FileInputStream fileInputStreamReader = null;
		
		try {
			fileInputStreamReader = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			autoNotificationPusher.notPush("ERROR", "PictureEncryption Software", "FileNotFoundException", Class.class.getClass(), "AUTO");
			if(stabilizer.getASValue()==true) { //AutoStabilizer
				if(f.exists()) {
					autoNotificationPusher.notPush("ERROR", "PictureEncryption Software", "File(existed) Error - Unknown Error.", Class.class.getClass(), "AUTO");
				}else {
					f = new File(destination);
					if(f.exists()) {
						if(f.canWrite()) {
							
						}else {
							autoNotificationPusher.notPush("ERROR", "PictureEncryption Software", "File(existed) Error - Unable to write file Error.", Class.class.getClass(), "AUTO");
						}
					}
				}
			}
		}
		
		byte[] bytes = new byte[(int)f.length()];
		
		try {
			fileInputStreamReader.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			code = new String(Base64.getEncoder().encode(bytes), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			autoNotificationPusher.notPush("ALERT", "", "UnsupportedEncodingException - Picture Encryption Error.", Class.class.getClass(), "AUTO");
		}
		
		return code;
	}
	
	public static void encrypt(String file) {
		if(file==null) {commandRun = false;}
		if(commandRun) //true
		if(file.substring(file.length()-4).toLowerCase().equals(".png")||file.substring(file.length()-4).toLowerCase().equals(".jpg")){
			c = PictureEncrypt(file);
			System.out.print(c);
			filePath = file;
		}
	}
	
	public static String getFile() {
		return (String) String.valueOf(c);
	}
	
	public static BufferedImage DecryptPicture(String decode) {
		byte[] b = null;
		try {
			b = decode.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			autoNotificationPusher.notPush("ALERT", "", "UnsupportedEncodingException - Picture Decryption Error.", Class.class.getClass(), "AUTO");
		}
		b = Base64.getDecoder().decode(b);
		BufferedImage image = null;
		
		ByteArrayInputStream stream = new ByteArrayInputStream(b);
		try {
			image = ImageIO.read(stream);
			stream.close();
		} catch (IOException e) {
			autoNotificationPusher.notPush("ALERT", "", "Picture Decryption Error.", Class.class.getClass(), "AUTO");
		}
		return image;
	}
	
	public String getValue(String dir) {
		if(dir.equals(filePath)) return String.valueOf(c);
		else return null;}
}
