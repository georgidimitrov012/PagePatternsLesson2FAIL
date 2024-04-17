package dev.Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addItemButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void clickItem() {
        waitForElementToBeVisible(addItemButton);
        addItemButton.click();
    }

    public void clickCart() {
        cartButton.click();
    }

    public HeaderComponent header() {
        return new HeaderComponent(driver);
    }

}
