import pages.FlightPage;
import pages.HomePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ResultsPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFlightTest extends TestBase {

    private HomePage homePage;
    private ResultsPage resultsPage;
    private FlightPage flightPage;
    private static final String DATE_FROM = "April 20, 2021";
    private static final String DATE_TO = "April 25, 2021";
    private static final String DEPARTURE_FROM = "San Francisco, CA - San Francisco Intl Airport (SFO)";
    private static final String GOING_TO = "New York City, NY - All Airports (NYC)";
    private static final String CABIN_CLASS = "Economy";
    private static List<String> STOPS_OPTIONS = new ArrayList<>(Arrays.asList("up to 1 stop", "nonstop"));
    private static List<String> AIRLINES_OPTIONS = new ArrayList<>(Arrays.asList("American Airlines", "Delta Air Lines"));
    private static final String RESULTS_PAGE_URL = "https://www.priceline.com/m/fly/search/SFO-NYC-20210420/NYC-SFO-20210425/?cabin-class=ECO&no-date-search=false&num-adults=1&sbsroute=slice1&search-type=1111&vrid=51138d2e01dd6d61600f31b4d7260709";

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        resultsPage = new ResultsPage(driver);
        flightPage = new FlightPage(driver);
    }

    @Test(description = "Full test which can not be passed because of captcha.")
    public void searchFlightTest() {
        homePage.clickFlightsTab();
        homePage.clickRoundTripRadio();
        homePage.fillDepartingField(DEPARTURE_FROM);
        homePage.fillGoingToField(GOING_TO);
        homePage.chooseDate(DATE_FROM, DATE_TO);
        homePage.selectTraveler();
        homePage.selectCabinClass(CABIN_CLASS);
        homePage.clickFindFlight();
        resultsPage.chooseBothMostExpensiveFlightsWithOptions(STOPS_OPTIONS, AIRLINES_OPTIONS);
        flightPage.printFlightInfo();
    }

    @Test(description = "Executable from results screen test which can be run after passing the captcha.")
    public void searchFlightTestFromResultsScreen() {
        resultsPage.gotoResultsPage(RESULTS_PAGE_URL);
        resultsPage.chooseBothMostExpensiveFlightsWithOptions(STOPS_OPTIONS, AIRLINES_OPTIONS);
        flightPage.printFlightInfo();
    }
}
