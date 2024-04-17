package dev.Selenium.tests;

import dev.Selenium.base.MainTest;
import dev.Selenium.pages.CheckOutPage;
import dev.Selenium.pages.LoginPage;
import dev.Selenium.pages.ProductsPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class LogInTest extends MainTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    CheckOutPage checkOutPage;

    @Test
    public void testSuccessfulLogin() {
        loginPage = new LoginPage(driver);
        loginPage.setUserName("standard_user");
        loginPage.setPassword("secret_sauce");

        loginPage.clickLogInButton();
        productsPage = new ProductsPage(driver);

        assertEquals(productsPage.getPageTitle(), "Products");

    }

    @Test
    public void negativeLogIn() {
        SoftAssert soft = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("standard_user1");
        loginPage.setPassword("secret_sauce1");
        loginPage.clickLogInButton();

        soft.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");

        loginPage.checkErrorBox();
        assertFalse(loginPage.checkErrorContainerIsPresent());
        loginPage.clearUserNameField();
        loginPage.clearPasswordField();


        /*Тук поради някаква причина пак ми слага UserName от първия кейс ????*/
        /*loginPage.setUserName("");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogInButton();

        soft.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username is required");

        loginPage.checkErrorBox();
        assertFalse(loginPage.checkErrorContainerIsPresent());*/


        soft.assertAll();

    }
}
