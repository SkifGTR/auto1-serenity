package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.SearchPage;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchSteps extends ScenarioSteps {

    SearchPage page;

    @Step
    public SearchSteps open_search_page() {
        page.open();
        return this;
    }

    @Step
    public SearchSteps click_by_year_filter_menu() {
        page.clickByYearFilterMenu();
        return this;
    }

    @Step
    public SearchSteps select_option_year_filter_menu(int year) {
        page.selectYearFilterByOption(year);
        page.waitFilterToBeApplied();
        return this;
    }

    @Step
    public SearchSteps select_sorting_option(String option) {
        page.selectByDescending(option);
        return this;
    }

    @Step
    public void verify_cars_registration_year_is_greater_than(int year) {
        assertThat(page.getProductionYearList()).allMatch(i -> i > year);
    }

    @Step
    public void verify_cars_prices_sorted_by_descending() {
        assertThat(page.getCarPrice()).isSortedAccordingTo(Comparator.reverseOrder());
    }
}
