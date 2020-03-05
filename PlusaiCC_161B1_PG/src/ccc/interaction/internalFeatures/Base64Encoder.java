package ccc.interaction.internalFeatures;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.imageio.ImageIO;

import ccc.interaction.global.autoNotificationPusher;
import ccc.mainComponent.regconizeCore.prefixTransformer;
import servicePackage.stabilizer;


public class Base64Encoder {
	
	private String type;
	private Object c = null;
	private String filePath = "";
	
	private String PictureEncrypt(String destination) {
		
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
	
	public void encrypt(String file) {
		if(file.substring(file.length()-4).toLowerCase().equals(".png")){
			c = PictureEncrypt(file);
			filePath = file;
		}
	}
	
	public BufferedImage DecryptPicture(String decode) {
		byte[] b = decode.getBytes();
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
	
	public void UploadCodeBypass(String txt) {
		c = txt;
	}
	
	public String getValue(String dir) {
		if(dir.equals(filePath)) return String.valueOf(c);
		else return null;}
	
	public String getFile() {
		return (String) String.valueOf(c);
	}
}


