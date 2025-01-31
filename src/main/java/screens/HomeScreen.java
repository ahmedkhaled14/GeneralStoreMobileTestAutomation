package screens;

import io.qameta.allure.Step;
import utils.Actions.MobileActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeScreen {
    WebDriver driver;
    MobileActions mobileActions;
    private final By dropDownList = By.id("com.androidsample.generalstore:id/spinnerCountry");
    private final By nameInput = By.id("com.androidsample.generalstore:id/nameField");
    private final By letsShopBtn = By.id("com.androidsample.generalstore:id/btnLetsShop");

    private By countryLocator(String countryName) {
        String xpath = "//android.widget.TextView[@resource-id='android:id/text1' and contains(@text, '" + countryName + "')]";
        return By.xpath(xpath);
    }
    private By genderLocator(String gender) {
        String id = "com.androidsample.generalstore:id/radio"+gender;
        return By.id(id);
    }



    public HomeScreen(WebDriver driver, MobileActions mobileActions) {
        this.driver = driver;
        this.mobileActions = mobileActions;
    }

    @Step("When I select country: [{country}] from the country dropdown list")
    public HomeScreen selectCountry(String country) {
        mobileActions.click(dropDownList);
        mobileActions.scrollDownToSpecificTextContained(country);
        mobileActions.click(countryLocator(country));
        return this;
    }

    @Step("And I enter my name: [{name}] in the name text field")
    public HomeScreen enterName(String name){
        mobileActions.type(nameInput,name);
        return this;
    }

    @Step("And I select my gender: [{gender}]")
    public HomeScreen chooseGender(String gender){
        mobileActions.click(genderLocator(gender));
        return this;
    }

    @Step("And I click on the Let’s Shop button")
    public ProductsScreen clickOnLetsShopBtn(){
        mobileActions.click(letsShopBtn);
        return new ProductsScreen(driver,mobileActions);
    }

}
