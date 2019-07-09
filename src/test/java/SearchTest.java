import com.autohero.steps.SearchSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.environment.WebDriverConfiguredEnvironment;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.webdriver.DriverConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SearchTest {

    @Steps
    SearchSteps steps;

    @Managed
    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        DriverConfiguration driverConfiguration =
                WebDriverConfiguredEnvironment.getDriverConfiguration();
        switch (driverConfiguration.getDriverType()) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            case OPERA:
                WebDriverManager.operadriver().setup();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                break;
        }
    }

    @Test
    public void verifySearchByYearAndSortByDescendingPrice() {
        int yearToVerify = 2015;
        steps.open_search_page()
                .click_by_year_filter_menu()
                .select_option_year_filter_menu(yearToVerify)
                .select_sorting_option("Höchster Preis");
        steps.verify_cars_registration_year_is_greater_than(yearToVerify);
        steps.verify_cars_prices_sorted_by_descending();
    }
}
