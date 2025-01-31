package EndToEnd;

import io.qameta.allure.*;
import utils.Actions.MobileActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;
import setup.Setup;
import utils.DataReader.JsonFileManager;

public class EndToEndTest {

    WebDriver driver;
    MobileActions mobileActions;
    JsonFileManager testData = new JsonFileManager("src/test/resources/TestDataFiles/testData.json");
    @BeforeMethod(description = "setup")
    public void setupDevice() {
        driver = new Setup(driver).setupDevice("Android");
        mobileActions = new MobileActions(driver);
    }

    @Description("Given I open the General Store app on an Android device\n" +
            "    When I select country from the country dropdown list\n" +
            "    And I enter my name in the name text field\n" +
            "    And I select my gender\n" +
            "    And I click on the Let’s Shop button\n" +
            "    Then I add two products to the cart\n" +
            "    When I navigate to the cart screen\n" +
            "    Then I should see the two products displayed in the cart\n" +
            "    And I should verify that the total amount equals the sum of the two products' prices")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("add to cart")
    @Story("test end to end add to cart feature")
    @Test(description = "test End to End Scenario")
    public void EndToEndScenario() {
        new HomeScreen(driver, mobileActions)
                .selectCountry(testData.getTestData("Country"))
                .enterName(testData.getTestData("Name"))
                .chooseGender(testData.getTestData("Gender"))
                .clickOnLetsShopBtn()
                .addProductToCart(1)
                .addProductToCart(2)
                .clickOnCartIcon()
                .isProductsImagesDisplayed()
                .validateTotalAmountForProducts();
    }


    @AfterMethod(description = "tear down")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
