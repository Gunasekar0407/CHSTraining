package LanguageChanger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;

public class LoginToLink {
	WebDriver webDriver;
	Properties properties;
	String userWorkingDirectory;
	String verifyTextString;

	public String webPageSetup() {

		userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "LanguageChanger" + pathSeparator + "WebPage.Properties";
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
		} catch (FileNotFoundException exception1) {
			exception1.printStackTrace();
		}
		properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		String lang = properties.getProperty("language");
		return lang;
	}

	public String setLink(String language) {
		LandToWebPage landToWebPage = new LandToWebPage();
		if (language.contains("English")) {
			landToWebPage.setIndiaWebsite(properties.getProperty("English"));
			return landToWebPage.getIndiaWebsite();

		} else if (language.contains("Japanese")) {
			landToWebPage.setJapanWebsite(properties.getProperty("Japanese"));
			return landToWebPage.getJapanWebsite();
		} else if (language.contains("Chinese")) {
			landToWebPage.setChinaWebsite(properties.getProperty("Chinese"));
			return landToWebPage.getChinaWebsite();
		} else {
			System.out.println("Language Not Found");
			return "Language not found";
		}
	//	VerifyDifferentLanguage.differentLanguages(language);
		
	}

	public void checkWebPage(String link, String language) {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.get(link);
//		if (language.contains("English")) {
//			verifyTextString = "All";
//		} else if (language.contains("Japanese")) {
//			verifyTextString = "すべて";
//		} else if (language.contains("Chinese")) {
//			verifyTextString = "全部";
//		} else {
//			System.out.println("Language Not Found");
//		}
		ChangeOfLanguage.change(link, language);
		WebElement cheking = webDriver.findElement(By.xpath(properties.getProperty("ValidatingText")));
		boolean check = ElementActionS.validate(cheking, language);
		Assert.assertTrue("Verified Language successfully", check);
		if (check == true) {
			System.out.println("Verified the Language successfully");
		} else if (check == false) {
			System.out.println("Verification failed");
		}
		webDriver.close();
	}
}
