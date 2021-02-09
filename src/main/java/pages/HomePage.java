package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CustomSleeper;

public class HomePage extends BasePage {

    @FindBy(id = "search-tab-flights")
    private WebElement flightsTabLink;
    @FindBy(xpath = "//input[@id='trip-type-round-trip']/..")
    private WebElement roundTripRadio;
    @FindBy(id = "flight-departure-airport0")
    private WebElement departingFromInput;
    @FindBy(id = "flight-arrival-airport0")
    private WebElement goingToInput;
    @FindBy(id = "typeahead-list")
    private WebElement aheadList;
    @FindBy(xpath = "//button[@aria-label='Show April 2021']")
    private WebElement showNextMonthButton;
    @FindBy(id = "flight-date-range")
    private WebElement flightDateRangeInput;
    private String dayButtonXpathLocator = "//div[@aria-label='%s']";
    @FindBy(xpath = "//div[contains(@class,'CalendarCard__Card')]")
    private WebElement calendarFrame;
    @FindBy(xpath = "//button[contains(@class,'CalendarCard__RightArrow')]")
    private WebElement calendarRightArrowButton;
    @FindBy(id = "traveler-selection-readonly-input")
    private WebElement travelerSelectButton;
    @FindBy(id = "traveler-selection-done-button")
    private WebElement travelerSelectDoneButton;
    @FindBy(id = "cabin-class-select")
    private WebElement cabinClassSelect;
    @FindBy(xpath = "//button[contains(text(),'Find Your Flight')]")
    private WebElement findFlightButton;
    @FindBy(id = "flight-departure-airport0-dropdown-item-0")
    private WebElement firstDropdownElement;

    private final static String HOME_PAGE_URL = "http://www.priceline.com/";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickFlightsTab() {
        goToPage(HOME_PAGE_URL);
        waitAndClick(flightsTabLink);
    }

    private void clickTravelerSelect() {
        waitAndClick(travelerSelectButton);
    }

    public void clickFindFlight() {
        waitAndClick(findFlightButton);
        switchDriverToNewTab();
    }

    public void selectCabinClass(String cabinClass) {
        selectElementFromDropdown(cabinClassSelect, cabinClass);
    }

    public void selectTraveler() {
        clickTravelerSelect();
        waitAndClick(travelerSelectDoneButton);
    }

    public void clickRoundTripRadio() {
        waitAndClick(roundTripRadio);
    }

    public void fillDepartingField(String departure) {
        typeAndSelect(departingFromInput, departure);
    }

    public void fillGoingToField(String goingTo) {
        typeAndSelect(goingToInput, goingTo);
    }

    private void clickNextMonthArrow() {
        waitAndClick(calendarRightArrowButton);
        CustomSleeper.sleep(500);
    }

    private void clickFlightDateRange() {
        waitAndClick(flightDateRangeInput);
    }

    public void chooseDate(String dateFrom, String dateTo) {
        clickFlightDateRange();
        clickNextMonthArrow();
        waitAndClick(getDayButtonElement(dateFrom));
        waitAndClick(getDayButtonElement(dateTo));
    }

    private WebElement getDayButtonElement(String date) {
        return calendarFrame.findElement(By.xpath(String.format(dayButtonXpathLocator, date)));
    }
}
