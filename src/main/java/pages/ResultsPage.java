package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CustomSleeper;

import java.util.List;

public class ResultsPage extends BasePage {

    @FindBy(xpath = "//button[@data-autobot-element='FLIGHTS_LISTINGS_FILTER_STOPS_NONE']")
    private WebElement noneStopsButton;
    @FindBy(xpath = "//button[@data-autobot-element='FLIGHTS_LISTINGS_FILTER_AIRLINES_NONE']")
    private WebElement noneAirlinesButton;
    @FindBy(xpath = "//ul[contains(@class,'fly-search-listings')]")
    private WebElement searchResultsList;
    private String stopsXpathLocator = "//span[@title='%s']";
    private String airlinesXpathLocator = "//span[@title='%s']";
    private String lastflightLocator = "//ul[contains(@class,'Listings__ItineraryList')]/li[last()]";
    private String flightLocator = "//span[contains(text(),'View More Flights')]";

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoResultsPage(String url) {
        goToPage(url);
    }

    private void waitForPageToBeLoaded() {
        waitForVisibility(noneStopsButton);
        waitForVisibility(noneAirlinesButton);
    }

    public void checkStopsOptions(List<String> options) {
        waitAndClick(noneStopsButton);
        checkSearchOptions(stopsXpathLocator, options);
        waitForPageToBeLoaded();
    }

    public void checkAirlinesOptions(List<String> options) {
        waitAndClick(noneAirlinesButton);
        checkSearchOptions(airlinesXpathLocator, options);
        waitForPageToBeLoaded();
    }

    private void checkSearchOptions(String xpathLocator, List<String> options) {
        for (String option : options) {
            waitAndClick(driver.findElement(By.xpath(String.format(xpathLocator, option))));
        }
    }

    private void chooseMostExpensiveFlightWithOptions(List<String> stopsOptions, List<String> airlinesOptions) {
        waitForPageToBeLoaded();
        checkStopsOptions(stopsOptions);
        checkAirlinesOptions(airlinesOptions);
        clickLastFlight();
    }

    public void chooseBothMostExpensiveFlightsWithOptions(List<String> stopsOptions, List<String> airlinesOptions) {
        chooseMostExpensiveFlightWithOptions(stopsOptions, airlinesOptions);
        CustomSleeper.sleep(1000);
        chooseMostExpensiveFlightWithOptions(stopsOptions, airlinesOptions);
        switchDriverToNewTab();
    }

    private void clickLastFlight() {
        while (isElementOnPage(flightLocator)) {
            waitForVisibility(noneStopsButton);
            driver.findElement(By.xpath(flightLocator)).click();
        }

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        if (isElementOnPage(lastflightLocator)) {
            WebElement mostExpensiveChooseButton = searchResultsList.findElement(By.xpath(lastflightLocator));
            waitAndClick(mostExpensiveChooseButton);
        }
    }
}
