package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by trangtnnguyen on 3/16/2020.
 */
public class PantryForGoodDeliveryDriversPage extends BasePage {
    public PantryForGoodDeliveryDriversPage(WebDriver driver) {
        super(driver);
    }

    public void goToDriversPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/drivers/list");
        waitForElementInSeconds(3);
    }


}
