package dev.Selenium.steps;

import dev.Selenium.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage();

    @Given("the user is logged in with username {string} and password {string}")
    public void the_user_is_logged_in_with_username_and_password(String username, String password) {
        loginPage.loginAs(username, password);
    }


    @Given("the user set username {string}")
    public void theUserSetUsername(String username) {
        loginPage.setUsername(username);
    }

    @Given("the user set password {string}")
    public void theUserSetPassword(String password) {
        loginPage.setPassword(password);

    }


    @When("the user click Login button")
    public void theUserClickLoginButton() {
        loginPage.clickLoginButton();
    }
}
