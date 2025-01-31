package screens;

import io.qameta.allure.Step;
import utils.Actions.MobileActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartScreen {
    WebDriver driver;
    MobileActions mobileActions;

    private final By totalAmount = By.id("com.androidsample.generalstore:id/totalAmountLbl");

    private By productsImage(int index) {
        return By.xpath("(//*[@resource-id='com.androidsample.generalstore:id/productImage'])[" + index + "]");
    }

    private By productsPrice(int index) {
        return By.xpath("(//*[@resource-id='com.androidsample.generalstore:id/productPrice'])[" + index + "]");
    }


    public CartScreen(WebDriver driver, MobileActions mobileActions) {
        this.driver = driver;
        this.mobileActions = mobileActions;
    }

    @Step("Then I should see the two products displayed in the cart")
    public CartScreen isProductsImagesDisplayed() {
        Assert.assertTrue(driver.findElement(productsImage(1)).isDisplayed());
        Assert.assertTrue(driver.findElement(productsImage(2)).isDisplayed());
        return this;
    }

    @Step("And I should verify that the total amount equals the sum of the two products prices")
    public CartScreen validateTotalAmountForProducts() {
        String firstProduct = driver.findElement(productsPrice(1)).getText().replace("$", "").trim();
        String secondProduct = driver.findElement(productsPrice(2)).getText().replace("$", "").trim();
        float firstProductPrice = Float.parseFloat(firstProduct);
        float secondProductPrice = Float.parseFloat(secondProduct);
        float expectedTotal = firstProductPrice + secondProductPrice;
        String totalPrice = driver.findElement(totalAmount).getText().replace("$", "").trim();
        float actualTotalPrice = Float.parseFloat(totalPrice);
        Assert.assertEquals(actualTotalPrice, expectedTotal);
        return this;
    }
}
