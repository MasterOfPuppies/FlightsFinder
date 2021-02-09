import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void initWebDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").replace("\\", "/") + "//src//test//resources//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void endMethod() {
        driver.close();
    }
}
