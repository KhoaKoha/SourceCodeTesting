package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


/**
 * Created by thuyluong on 04/03/2020.
 */

public class PantryForGoodSettingsEditClientPage extends BasePage {

    private String btnUpdate = "//button[@class='btn btn-success']";
    private String tabCustomers = "//a[@title='Customers']";
    private String tabDonors = "//a[@title='Donors']";
    private String tabVolunteers = "//a[@title='Volunteers']";
    private String editorHomeTab = "//div[@class='ql-editor']/p[2]";
    private String editorCustomersTab = "//div[@class='ql-editor']//ul/li[5]";
    private String editorDonorsTab = "//div/p";
    private String editorVolunteersTab = "//div[@class='ql-editor']/p[2]";

    public PantryForGoodSettingsEditClientPage(WebDriver driver) {
        super(driver);
    }

    //Navigate to Edit Client page
    public void goToEditClientPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/settings/pages");
        waitForElementInSeconds(3);
    }

    //Update content on Home tab
    public void updateContentOnHomeTab(WebDriver driver) {
        getElement(editorHomeTab).sendKeys(Keys.ENTER);
        getElement(editorHomeTab).sendKeys("Update content on Home tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorHomeTab).getText();
        String expectedResult = "Update content on Home tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);

    }

    //Undo updating content on Home tab
    public void cancelUpdateContentOnHomeTab(WebDriver driver) {
        getElement(editorHomeTab).sendKeys(Keys.ENTER);
        getElement(editorHomeTab).sendKeys("The content of this page and the site branding can be changed by an administrator on the settings page.", false);
        getElement(btnUpdate).click();
    }

    //Update content on Customers tab
    public void updateContentOnCustomersTab(WebDriver driver) {
        getElement(tabCustomers).click();
        getElement(editorCustomersTab).sendKeys(Keys.ENTER);
        getElement(editorCustomersTab).sendKeys("Update content on Customers tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorCustomersTab).getText();
        String expectedResult = "Update content on Customers tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);

    }

    //Undo updating content on Customers tab
    public void cancelUpdateContentOnCustomersTab(WebDriver driver) {
        getElement(editorCustomersTab).sendKeys(Keys.ENTER);
        getElement(editorCustomersTab).sendKeys("If there is a change in your financial situation and you no longer need our assistance, if you will be away for a certain period, if you have a change in contact information, or any other change, please inform us promptly.", false);
        getElement(btnUpdate).click();
    }

    //Update content on Donors tab
    public void updateContentOnDonorsTab(WebDriver driver) {
        getElement(tabDonors).click();
        getElement(editorDonorsTab).sendKeys(Keys.ENTER);
        getElement(editorDonorsTab).sendKeys("Update content on Donors tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorDonorsTab).getText();
        String expectedResult = "Update content on Donors tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);

    }

    //Undo updating content on Donors tab
    public void cancelUpdateContentOnDonorsTab(WebDriver driver) {
        getElement(editorDonorsTab).sendKeys(Keys.ENTER);
        getElement(editorDonorsTab).sendKeys("", false);
        getElement(btnUpdate).click();
    }

    //Update content on Volunteers tab
    public void updateContentOnVolunteersTab(WebDriver driver) {
        getElement(tabVolunteers).click();
        getElement(editorVolunteersTab).sendKeys(Keys.ENTER);
        getElement(editorVolunteersTab).sendKeys("Update content on Volunteers tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorVolunteersTab).getText();
        String expectedResult = "Update content on Volunteers tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);

    }

    //Undo updating content on Volunteers tab
    public void cancelUpdateContentOnVolunteersTab(WebDriver driver) {
        getElement(editorVolunteersTab).sendKeys(Keys.ENTER);
        getElement(editorVolunteersTab).sendKeys("With your help, we are able to support the less fortunate families in our community, by providing them with nutritious food and energy to grow, learn, work, and give them hope for a better and brighter future.", false);
        getElement(btnUpdate).click();
    }

}
