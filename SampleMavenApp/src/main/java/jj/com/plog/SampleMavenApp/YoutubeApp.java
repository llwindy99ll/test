package jj.com.plog.SampleMavenApp;

/**
 * Hello world!
 *
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class YoutubeApp {
    public static final String WEB_DRIVER_PATH = System.getProperty("user.dir")+"\\chromedriver_win32\\chromedriver.exe";
    
    public static void main(String[] args) {
        

        // WebDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        
        
        
        // WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함
        
        // WebDriver 객체 생성
        ChromeDriver driver = new ChromeDriver( options );
        

        // 웹페이지 요청
        driver.get("https://www.youtube.com/results?search_query=여행");
        
        // 웹페이지에서 글제목 가져오기
        //WebElement page2_title = driver.findElementByXPath("//*[@id=\"video-title\"]");
        
        List<WebElement> contents = driver.findElementsByXPath("//*[@id=\"video-title\"]");
        
        for(WebElement el : contents) {
            if( el != null  ) {
                System.out.println( el.getText() );            
            }
        	
        }
        

        
        // 웹페이지 소스 출력
        //System.out.println( driver.getPageSource() );
        
        // 탭 종료
        driver.close();

        
        
        // 5초 후에 WebDriver 종료
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // WebDriver 종료
            driver.quit();
        }
    }
    
    public static void runSelenium(String URL) throws Exception {
        // 1. WebDriver 경로 설정
//        Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",WEB_DRIVER_PATH);
        
        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");          // 최대크기로
        options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        
        // 3. WebDriver 객체 생성
        ChromeDriver driver = new ChromeDriver( options );
        
        // 4. 웹페이지 요청
        driver.get(URL);
        
        // 5. HTML 저장.
        
    
        // 6. 트윗 목록 Block 조회, 로드될 때까지 최대 30초간 대기
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement parent = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section[aria-labelledby*=\"accessible-list\"]")));
        
        // 7. 트윗 콘텐츠 조회
        List<WebElement> contents = parent.findElements(By.cssSelector("div.css-1dbjc4n.r-my5ep6.r-qklmqi.r-1adg3ll"));
        System.out.println( "조회된 콘텐츠 수 : "+contents.size() );
        
        if( contents.size() > 0 ) {
            // 8. 트윗 상세 내용 탐색
            for(WebElement content : contents ) {
                try {
                    String username = content.findElement(By.cssSelector("span > span.css-901oao.css-16my406.r-1qd0xha.r-ad9z0x.r-bcqeeo.r-qvutc0")).getText();
                    String id = content.findElement(By.cssSelector("span.css-901oao.css-16my406.r-1qd0xha.r-ad9z0x.r-bcqeeo.r-qvutc0")).getText();
                    String text = content.findElement(By.cssSelector("div.css-901oao.r-hkyrab.r-1qd0xha.r-a023e6.r-16dba41.r-ad9z0x.r-bcqeeo.r-bnwqim.r-qvutc0")).getText();
                    
                    System.out.println( "========================" );
                    System.out.println( username+" "+id );
                    System.out.println( text );
                    System.out.println( "========================" );
                } catch ( NoSuchElementException e ) {
                    // pass
                }
            }
        }
               
        // WebDriver 종료
        driver.quit();
    }
    
}
