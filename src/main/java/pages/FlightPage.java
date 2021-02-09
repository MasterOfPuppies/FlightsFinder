package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FlightPage extends BasePage {
    @FindBy(id = "flight-details")
    private WebElement flightDetailsBox;
    @FindBy(xpath = "//div[@data-testid='itinerary-slice-1']//*[@data-testid='slice-dropdown-arrow']//parent::div")
    private WebElement departureInfoBoxArrow;
    @FindBy(xpath = "//div[@data-testid='itinerary-slice-2']//*[@data-testid='slice-dropdown-arrow']//parent::div")
    private WebElement arrivalInfoBoxArrow;
    @FindBy(xpath = "//div[@data-testid='itinerary-slice-1']//div[contains(@data-testid,'itinerary-segment')]/div[2]/div[2]/div[1]")
    private List<WebElement> departureFlightNumberElements;
    @FindBy(xpath = "//div[@data-testid='itinerary-slice-2']//div[contains(@data-testid,'itinerary-segment')]/div[2]/div[2]/div[1]")
    private List<WebElement> arrivalFlightNumberElements;
    @FindBy(xpath = "//div[@data-testid='floating-bottom-bar-price']/div[2]")
    private WebElement integerPricePortion;
    @FindBy(xpath = "//div[@data-testid='floating-bottom-bar-price']/div[3]")
    private WebElement fractionalPricePortion;
    @FindBy(xpath = "//button[@data-testid='floating-bottom-bar-continue-button']")
    private WebElement continueButton;

    public FlightPage(WebDriver driver) {
        super(driver);
    }

    private void waitPageToBeLoaded() {
        waitForVisibility(continueButton);
    }

    private void printFlightNumbers(List<WebElement> list) {
        for (WebElement webElement : list) {
            System.out.println("  " + webElement.getText() + "\n");
        }
    }

    private String getFlightPrice() {
        return integerPricePortion.getText() + fractionalPricePortion.getText();
    }

    public void printFlightInfo() {
        waitPageToBeLoaded();
        waitAndClick(arrivalInfoBoxArrow);
        waitAndClick(departureInfoBoxArrow);
        waitForVisibilityOfAllElements(departureFlightNumberElements);
        System.out.println("Departure flight(s): \n");
        printFlightNumbers(departureFlightNumberElements);
        System.out.println("Arrival flight(s): \n");
        printFlightNumbers(arrivalFlightNumberElements);
        System.out.println("Flight total trip fare: " + getFlightPrice());
        takeScreenshot();
    }
}
