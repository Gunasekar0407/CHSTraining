package LengthSpecifier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LanguageChanger {
	WebDriver webDriver;
	Properties properties;
	String userWorkingDirectory;

	@BeforeClass
	public void webPageSetup() {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		userWorkingDirectory = System.getProperty("user.dir");
		String pathSeparator = System.getProperty("file.separator");
		String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java"
				+ pathSeparator + "LengthSpecifier" + pathSeparator + "WebPage.Properties";
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
		webDriver.get(properties.getProperty(properties.getProperty("Language")));
	}

	@Test
	void checkWebPage() {
		// String validatingText = properties.getProperty("ValidatingText");
		// String validatingText = "Select your address";
		 String validatingText = "すべて";
		//String validatingText = "すべてすべてすべて";
		boolean check = ElementsAction.validate(webDriver, validatingText);
		if (check == true) {
			System.out.println("Verified the Language of webpage successfully");
		} else if (check == false) {
			System.out.println("Language Verification failed");
		}
	}
}
