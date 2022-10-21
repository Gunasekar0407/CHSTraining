package LanguageChanger;

import java.util.Properties;

public class VerifyDifferentLanguage {
	static Properties properties;

	public static String differentLanguages(String language) {
		LandToWebPage landToWebPage = new LandToWebPage();
		switch (language) {
		case "English":
			landToWebPage.setIndiaWebsite(properties.getProperty("English"));
			return landToWebPage.getIndiaWebsite();
		case "Japanese":
			landToWebPage.setJapanWebsite(properties.getProperty("Japanese"));
			return landToWebPage.getJapanWebsite();
		case "Chinese":
			landToWebPage.setChinaWebsite(properties.getProperty("Chinese"));
			return landToWebPage.getChinaWebsite();
		}

		return null;

	}
}
