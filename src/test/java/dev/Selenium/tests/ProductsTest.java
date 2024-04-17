package dev.Selenium.tests;

import dev.Selenium.base.MainTest;
import dev.Selenium.pages.CheckOutPage;
import dev.Selenium.pages.LoginPage;
import dev.Selenium.pages.ProductsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ProductsTest extends MainTest {
    LoginPage loginPage;
    ProductsPage productsPage;
    CheckOutPage checkOutPage;

    @BeforeMethod
    public void logInBefore() {
        loginPage = new LoginPage(driver);
        loginPage.logInAs("standard_user", "secret_sauce");
    }

    @Test
    public void fullFlow() {
        productsPage = new ProductsPage(driver);

        productsPage.clickItem();
        productsPage.clickCart();

        checkOutPage = new CheckOutPage(driver);
        assertTrue(checkOutPage.isItemAdded());

        /*Работи правилно*/
        /*SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(checkOutPage.isItemAdded());
        softAssert.assertAll();*/
        checkOutPage.clickRemove();

        assertFalse(checkOutPage.isItemAdded());
    }

    @Test
    public void  canOpenCart() {
        productsPage = new ProductsPage(driver);
        productsPage.header().openCatByIcon();
    }
}
