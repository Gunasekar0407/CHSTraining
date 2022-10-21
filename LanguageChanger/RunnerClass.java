package LanguageChanger;

public class RunnerClass {
	public static void main(String[] args) {
		LoginToLink loginToLink = new LoginToLink();
		String dummyString=loginToLink.webPageSetup();
		System.out.println(dummyString);
		loginToLink.checkWebPage(loginToLink.setLink(loginToLink.webPageSetup()), loginToLink.webPageSetup());
		System.out.println("End");
	} 
	 
}
