package com.autohero.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("/de/search/")
public class SearchPage extends PageObject {

    @FindBy(css = "[data-qa-selector='filter-year']")
    WebElement filterByYearMenu;

    @FindBy(css = "[name='yearRange.min']")
    WebElementFacade filterByYearOption;

    @FindBy(css = "[name='sort']")
    WebElementFacade filterByDescending;

    @FindAll({@FindBy(xpath = "//li[@data-qa-selector='spec'][1]")})
    List<WebElement> carsYears;

    @FindAll({@FindBy(css = "[data-qa-selector='price']")})
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

    public void waitYearFilterToBeApplied(int year) {
        String yearFilterCssSelector = String.format("ul > [data-qa-selector-value='%d']", year);
        element(By.cssSelector(yearFilterCssSelector)).waitUntilVisible();
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
