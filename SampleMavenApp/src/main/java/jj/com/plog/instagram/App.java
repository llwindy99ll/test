package jj.com.plog.instagram;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Hello world!
 *
 */

public class App {

    private WebDriver driver;
    
    public static void main(String[] args) {
    	App main = new App();
    	String result;
    	ChromeDriverContext chromeDr = new ChromeDriverContext();
    	try {
			chromeDr.setupChromeDriver();
			result = main.getDate(chromeDr.getDriver());
			System.out.println("result"+result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    public String getDate(WebDriver driver) {
        this.driver = driver;
        WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[2]/div[2]/a/time"));
        String realdate = element.getAttribute("title");

        return realdate;
    }
}
