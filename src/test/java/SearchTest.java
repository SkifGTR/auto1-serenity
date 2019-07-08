import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.SearchSteps;

@RunWith(SerenityRunner.class)
public class SearchTest {

    @Steps
    SearchSteps steps;

    @Managed
    WebDriver driver;

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
