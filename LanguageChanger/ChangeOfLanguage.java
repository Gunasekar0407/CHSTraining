package LanguageChanger;


public class ChangeOfLanguage {
	static String verifyTextString;

	public static String change(String link, String language) {
		switch (language) {
		case "English":
			verifyTextString = "All";
			
		case "Japanese":
			verifyTextString = "すべて";
		case "Chinese":
			verifyTextString = "全部";
			break;
		}
		return verifyTextString;
	}
}
