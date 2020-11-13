package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by trangtnnguyen on 3/18/2020.
 */
public class PantryForGoodCustomersPage extends BasePage {
    private String lblCustomerMenu = "//a[@class='submenu-item']//span[contains(text(),'Customers')]";
    private String lnkEyeIcon = "//tr[1]//div//a//i[@class='fa fa-eye']";
    private String lnkCustomersMenu = "//a[@class='submenu-item']//span[contains(text(),'Customers')]";

    public PantryForGoodCustomersPage(WebDriver driver) {
        super(driver);
    }

    public void goToCustomerPage() {
        getElement(lblCustomerMenu).click();
        waitForElementInSeconds(3);
    }

    public void goToEditPageUsingEyeIconAndVerifyTheResult(WebDriver driver) {
        String idOfCustomer = getElement("//tr[1]//td[1]").getText();
        getElement(lnkEyeIcon).click();
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "http://localhost:8080/customers/" + idOfCustomer;
        Assert.assertEquals(actualURL, expectedURL, "URL is not equal.");
        System.out.println("Actual URL: " + actualURL + ", expected URL: " + expectedURL);
    }

    public void verifyCustomersLinkOnLeftMenuDirectToCustomersPage(WebDriver driver) {
        String expectedURL = "http://localhost:8080/customers/list";
        getElement(lnkCustomersMenu).click();
        String currentURL = driver.getCurrentUrl();

        //Verify Customers on Menu links to Customers page
        Assert.assertEquals(currentURL, expectedURL, "URL is not correct.");

    }


 /*   public void selectSizePerPageAndVerifyTableSize() {
        String optionXpath = "//div[@class='react-bs-table-pagination']//ul[@class='dropdown-menu']//li";
        int sizeOfDropdown = getElementsCount(optionXpath + "//a");

        for (int i = 1; i <= sizeOfDropdown; i++) {
            //Click on size dropdown
            getElement(btnSizePerPage).click();

            //Get size number
            WebElement sizeElement = getWebElement(optionXpath + "[" + i + "]//a");
            int sizeNum = Integer.parseInt(sizeElement.getText());

            //Click on size
            sizeElement.click();

            //Get row count
            int rowCount = getElementsCount(tblSearchResult + "//tr");

            //Get pagination
            int pagination = getElementsCount(txtPagination);
            System.out.println("Size num =  " + sizeNum + ". Row count = " + rowCount + ". Pagination = " + pagination);

            //Verify if the table is displayed with correct size
            if (pagination > 1) {
                Assert.assertEquals(sizeNum, rowCount, "Table size is not correct.");
            } else {
                if (sizeNum > rowCount) {
                    Assert.assertTrue(true, "Table size is not correct.");
                } else {
                    Assert.assertTrue(false, "Table size is larger than the selected size.");
                }
            }
        }


    }
    */
}





