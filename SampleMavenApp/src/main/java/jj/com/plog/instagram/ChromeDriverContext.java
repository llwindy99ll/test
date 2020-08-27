package jj.com.plog.instagram;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.*;

public class ChromeDriverContext {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(ChromeDriverContext.class);
    public static final String WEB_DRIVER_PATH = System.getProperty("user.dir")+"\\chromedriver_win32\\chromedriver.exe";
    
    public WebDriver getDriver() {
        return driver;
    }

    public WebDriver setupChromeDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1366,768");
        options.addArguments("--headless");
        options.setProxy(null);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("pageLoadStrategy", "none");

        try {
            /*
             *
             * @ params
             * option : headless
             *
             */
            driver = new ChromeDriver(capabilities);
        } catch (Exception e) {
            logger.error("### [driver error] msg: {}, cause: {}", e.getMessage(), e.getCause());
        }

        return driver;
    }
}