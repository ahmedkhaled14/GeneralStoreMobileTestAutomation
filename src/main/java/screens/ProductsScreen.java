package screens;

import io.qameta.allure.Step;
import utils.Actions.MobileActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsScreen {

    WebDriver driver;
    MobileActions mobileActions;

    private final By cartIcon = By.id("com.androidsample.generalstore:id/appbar_btn_cart");

    private By addToCartBtn(int index){
        return By.xpath("(//*[@resource-id='com.androidsample.generalstore:id/productAddCart'])["+index+"]");
    }

    public ProductsScreen(WebDriver driver, MobileActions mobileActions) {
        this.driver = driver;
        this.mobileActions = mobileActions;
    }

    @Step("Then I add two products to the cart")
    public ProductsScreen addProductToCart(int index){
        mobileActions.click(addToCartBtn(index));
        return this;
    }

    @Step("When I navigate to the cart screen")
    public CartScreen clickOnCartIcon(){
        mobileActions.click(cartIcon);
        return new CartScreen(driver,mobileActions);
    }

}
