package dev.Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BasePage{
    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void openCatByIcon() {
        cartButton.click();
    }
}
