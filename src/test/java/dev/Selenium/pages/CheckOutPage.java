package dev.Selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CheckOutPage extends BasePage {
    @FindBy(name = "remove-sauce-labs-backpack")
    private WebElement removeSpecificItem;

    @FindBy(css = ".cart_button")
    private List<WebElement> listOfRemoveButtons;

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }

    public void clickRemove() {
        removeSpecificItem.click();
    }

    public boolean isItemAdded() {
        if (listOfRemoveButtons.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
