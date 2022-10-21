package LanguageChanger;

import org.openqa.selenium.WebElement;

public class ElementActionS {
	static String verifyLanguage;

	public static boolean validate(WebElement expected, String language) {

		String verifyProduct = expected.getText();
		if (language.contains("English")) {
			verifyLanguage = "All";
		} else if (language.contains("Japanese")) {
			verifyLanguage = "すべて";
		} else if (language.contains("Chinese")) {
			verifyLanguage = "全部";
		} else {
			System.out.println("Language Not Found");
		}

		if (verifyProduct.contains(verifyLanguage)) {
			return true;
		} else {
			return false;
		}
	}
}
