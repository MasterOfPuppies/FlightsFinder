package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
    }

    protected void waitAndClick(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    protected void selectElementFromDropdown(WebElement webElement, String option) {
        Select select = new Select(webElement);
        select.selectByVisibleText(option);
    }

    protected void typeAndSelect(WebElement dropDownElement, String text) {
        dropDownElement.sendKeys(text);
        By searchableValue = By.xpath(String.format("//div[contains(text(),'%s')]", text));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchableValue));
        WebElement elementForSelection = driver.findElement(searchableValue);
        waitAndClick(elementForSelection);
    }

    protected void waitForVisibility(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForVisibilityOfAllElements(List<WebElement> list) {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    protected void goToPage(String url) {
        driver.get(url);
    }

    protected boolean isElementOnPage(String locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void switchDriverToNewTab() {
        driver.close();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    protected void takeScreenshot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir").replace("\\\\", "/") + "//src//test//resources//screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
