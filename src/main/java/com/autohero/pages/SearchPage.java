package com.autohero.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.autohero.com/de/search/")
public class SearchPage extends PageObject {

    @FindBy(css = "[data-qa-selector='filter-year']")
    WebElement filterByYearMenu;

    @FindBy(xpath = "[@name='yearRange.min']")
    WebElementFacade filterByYearOption;

    @FindBy(xpath = "//select[@name='sort']")
    WebElementFacade filterByDescending;

    @FindBy(xpath = "//button[contains(@class,'filterButton___3dGDs')]")
    WebElementFacade searchFilterSelectedOptions;

    @FindAll({@FindBy(xpath = "//li[@class='specItem___2gMHn'][1]"),})
    List<WebElement> carsYears;

    @FindAll({@FindBy(xpath = "//div[@data-qa-selector='price']"),})
    List<WebElement> carsPrices;

    public void clickByYearFilterMenu() {
        filterByYearMenu.click();
    }

    public void selectYearFilterByOption(int year) {
        filterByYearMenu.click();
        filterByYearOption.selectByVisibleText(String.valueOf(year));
    }

    public void selectByDescending(String option) {
        filterByDescending.waitUntilVisible();
        filterByDescending.selectByVisibleText(option);
    }

    public void waitFilterToBeApplied() {
        searchFilterSelectedOptions.waitUntilVisible();
    }

    public List<Integer> getProductionYearList() {
        return carsYears.stream()
                .map(WebElement::getText)
                .map(s -> s.replaceFirst("\\D\\s\\d+\\/", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getCarPrice() {
        return carsPrices.stream()
                .map(WebElement::getText)
                .map(s -> s.replaceFirst("\\s€", ""))
                .map(s -> s.replaceAll("\\.", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
