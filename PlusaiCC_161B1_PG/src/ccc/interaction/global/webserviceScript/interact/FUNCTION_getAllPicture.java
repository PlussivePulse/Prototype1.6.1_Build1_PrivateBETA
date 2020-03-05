package ccc.interaction.global.webserviceScript.interact;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import ccc.interaction.global.popUpBox;
import ccc.interaction.global.components.internalFileBridge;
import ccc.interaction.global.components.seleniumDriverHelper;
import ccc.interaction.global.database.DateAndTime;
import ccc.mainComponent.UI.seleniumPanel;
import ccc.mainComponent.experimental.DEBUG;
import dataInteractingAbility.createFile;
import dataInteractingAbility.filesCore;

public class FUNCTION_getAllPicture {
	
	//private static WebDriver driver;
	
	public static ArrayList<String> webLink = new ArrayList<String>();
	public static String siteLink = "";
	private static File savetarget;
	
	@Deprecated
	public static ArrayList<String> getURL(String link) {
		siteLink = link;
		//MAIN_HOLDER.defaultsetDriver();
		seleniumDriverHelper.publicDriver.get(link);
		
		seleniumDriverHelper.publicDriver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
		seleniumDriverHelper.publicDriver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		
		//seleniumPanel.submit(seleniumPanel.panelDisplayer,"Scanning all image in " + link,Color.black);
		List<WebElement> listImage = seleniumDriverHelper.publicDriver.findElements(By.tagName("img"));
		seleniumPanel.submit(seleniumPanel.panelDisplayer,"Selenium WebElement - Total Image : " + listImage.size(),Color.black);
		for(int i = 0; i<listImage.size(); i++) {
			if(!(listImage.get(i).getAttribute("src").equals("")) && !(listImage.get(i).getAttribute("src") == null)) {
				String string;
				if(listImage.get(i).getAttribute("src").contains(".png")) {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.pink);
				}else if(listImage.get(i).getAttribute("src").contains(".jpg")) {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.blue);
				}else if(listImage.get(i).getAttribute("src").contains(".JPG")) {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.GRAY);
				}else {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.orange);
				}
				
				//System.out.println(string = listImage.get(i).getAttribute("src"));

				webLink.add(listImage.get(i).getAttribute("src"));
				
			}
		}
		seleniumDriverHelper.publicDriver.close();
		return webLink;
	}
	
	public static ArrayList<String> getURL(WebDriver driver, String link) {
		siteLink = link;
		//MAIN_HOLDER.defaultsetDriver();
		driver.get(link);
		
		driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		
		if(seleniumPanel.panelDisplayer == null) {
			seleniumPanel panel = new seleniumPanel();
		}else {
			seleniumPanel.frame.setVisible(true);
		}
		
		//seleniumPanel.submit(seleniumPanel.panelDisplayer,"Scanning all image in " + link,Color.black);
		List<WebElement> listImage = driver.findElements(By.tagName("img"));
		seleniumPanel.submit(seleniumPanel.panelDisplayer,"Selenium WebElement - Total Image : " + listImage.size(),Color.black);
		for(int i = 0; i<listImage.size(); i++) {
			if(!(listImage.get(i).getAttribute("src").equals("")) && !(listImage.get(i).getAttribute("src") == null)) {
				String string;
				if(listImage.get(i).getAttribute("src").contains(".png")) {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.pink);
				}else if(listImage.get(i).getAttribute("src").contains(".jpg")) {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.blue);
				}else if(listImage.get(i).getAttribute("src").contains(".JPG")) {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.GRAY);
				}else {
					string = listImage.get(i).getAttribute("src");
					seleniumPanel.submit(seleniumPanel.panelDisplayer,string,Color.orange);
				}
				
				//System.out.println(string = listImage.get(i).getAttribute("src"));

				webLink.add(listImage.get(i).getAttribute("src"));
				
			}
		}
	//	driver.close();
		return webLink;
	}
	
	public static void loadImage(ArrayList<String> url) {}
	
	@Deprecated
	public static void loadImageDefault(String url) {
		DEBUG.print("Getting Url.");
		getURL(url);
		DEBUG.print("Preparing directory.");
		linkPrep();
		DEBUG.print("Downloading Image.");
		downloadImage();
	}
	
	public static void loadImageDefault(WebDriver driver, String url) {
		DEBUG.print("Getting Url.");
		getURL(driver, url);
		DEBUG.print("Preparing directory.");
		linkPrep();
		DEBUG.print("Downloading Image.");
		downloadImage();
	}
	
	private static void downloadImage() {
		for(int i=0; i<webLink.size(); i++) {
			try {
				URL url = new URL(webLink.get(i));
				BufferedImage img = ImageIO.read(url);
				//String imgName = webLink.get(i).toString();
				//imgName = imgName.replace("/", "");
				//imgName = imgName.replace(":", "");
				//imgName = imgName.substring(0,imgName.length()-4);
				//imgName = imgName.replace(".", "");
				String imgName[] = webLink.get(i).toString().split("/");
				String imageName = imgName[imgName.length-1].substring(0,imgName[imgName.length-1].length()-4);
				String fileType = imgName[imgName.length-1].substring(imgName[imgName.length-1].length()-3);
				DEBUG.print(imageName);
				DEBUG.print(fileType);
				File file = new File(savetarget.getAbsolutePath()+"\\"+imageName+"."+fileType);
				ImageIO.write(img,fileType,file);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
	}

	public static void linkPrep() {
		String[] spliter = siteLink.split("/");
		String siteName = "";
		for(String string : spliter) {
			if(string.contains(".com")) {
				siteName = string.replace("www.", "");
				siteName = siteName.replace("https:", "");
				siteName = siteName.substring(0,siteName.length()-4);
			}
		}
		
		String dat = DateAndTime.getDAT();
		dat = dat.replaceAll("/", "");
		dat = dat.replaceAll(":", "");
		dat = dat.replaceAll(" ", "");
		
		String folderName = siteName+dat;
		System.out.println(folderName);
		File folderCreation = null;
		try {folderCreation = new File(filesCore.getDir());}
		catch (URISyntaxException e) {e.printStackTrace();}
		savetarget = new File(folderCreation.getParent()+"\\" + internalFileBridge.mainFolderName  + "\\Downloads\\" + folderName);
		if(savetarget.exists()) {
			popUpBox.alertError("File already existed.", "File Create Failed :");
		}else {
			DEBUG.print("DEBUG : Creating file download directory command triggered.");
			createFile.createFolder(savetarget.getParent(), folderName);
		}
	}

}
