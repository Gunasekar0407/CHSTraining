package LengthSpecifier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ElementsAction {

	public static boolean validate(WebDriver check,String expected) {
		  String verifyProduct = check.findElement(By.tagName("body")).getText();
	        if(verifyProduct.contains(expected)) {
	            return true;
	        }
	        else {
	            return false;
	}
	}
}
