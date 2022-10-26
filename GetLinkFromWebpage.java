package Tasks;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class GetLinkFromWebpage {
    WebDriver webDriver;
    Properties properties;
    Scanner getData = new Scanner(System.in);
    String userWorkingDirectory;
    XSSFWorkbook myWorkbook;
    @BeforeClass
    void webPageSetup() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        userWorkingDirectory = System.getProperty("user.dir");
        String pathSeparator = System.getProperty("file.separator");
        String filePath = userWorkingDirectory + pathSeparator + "src" + pathSeparator + "main" + pathSeparator + "java" + pathSeparator + "Tasks" + pathSeparator + "webPage.Properties";
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        }
        catch (FileNotFoundException exception1) {
            exception1.printStackTrace();
        }
        properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        webDriver.get(properties.getProperty("link"));
    }

    @Test(priority = 0)
    void checkWebPage() {
        String validatingText = properties.getProperty("validatingText");
        boolean check = ElementsAction.validate(webDriver, validatingText);
        if(check == true) {
            System.out.println("Verified sucessfully that open web is same as actual landing page");
        }
        else if(check == false) {
            System.out.println("Verification failed");
        }
    }

    @Test(priority = 1)
    void getLink() throws IOException {
        List<String> allElements = new ArrayList<String>();
        List<WebElement> body =  webDriver.findElements(By.tagName(properties.getProperty("webLinks")));
        for(WebElement name : body) {
            String text = name.getAttribute("href");
            allElements.add(text);
        }
        List<WebElement> body1 =  webDriver.findElements(By.xpath(properties.getProperty("webLinksPath")));
        for(WebElement name1 : body1) {
            String text1 = name1.getAttribute("href");
            allElements.add(text1);
        }

        System.out.println("Enter Your excel File Name:");
        String excelfileName = getData.next();
        String newpath = userWorkingDirectory + File.separator + excelfileName;
        File file = new File(newpath);
        FileInputStream fileInputStream = new FileInputStream(file);
        myWorkbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet xssfSheet = myWorkbook.createSheet("ll");

        XSSFRow nRow;
        int index = 0;
        for (String a : allElements) {
            nRow = xssfSheet.createRow(index);
            nRow.createCell(0).setCellValue(a);
            index++;
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        myWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("XLsheet was writed Successfully");
    }

    @AfterClass
    void close() {
        webDriver.close();
    }

}

