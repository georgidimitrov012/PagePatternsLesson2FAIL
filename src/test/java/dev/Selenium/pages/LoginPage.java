package dev.Selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;


public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement logInButton;

    @FindBy(css = "h3")
    private WebElement errorButton;

    @FindBy(className = "error-message-container")
    private WebElement errorContainer;

    @FindBy(className = "error-message-container")
    private List<WebElement>listOfErrorContainer;

    @FindBy(className = "error-button")
    private WebElement errorXButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUserName(String username) {
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogInButton() {
        logInButton.click();
    }

    public String getErrorMessage() {
         return errorContainer.getText();
    }

    public void checkErrorBox() {
        String errorText = errorButton.getText();
        Assert.assertEquals(errorText, "Epic sadface: Username and password do not match any user in this service");

        String errorContainerColor = errorContainer.getCssValue("background-color");
        Assert.assertEquals(errorContainerColor, "rgba(226, 35, 26, 1)");

        errorXButton.click();
    }

    public boolean checkErrorContainerIsPresent() {
        if (listOfErrorContainer.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void clearUserNameField() {
        usernameInput.clear();
    }

    public void clearPasswordField() {
        passwordInput.clear();
    }

    public void logInAs(String userName, String password) {
        setUserName(userName);
        setPassword(password);
        clickLogInButton();
    }
}
