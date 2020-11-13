package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

/**
 * Created by trangtnnguyen on 4/9/2020.
 */
public class PantryForGoodSettingsApplicationDonors extends BasePage{
    public PantryForGoodSettingsApplicationDonors(WebDriver driver) {
        super(driver);
    }

    private String lnkDonorApplication = "//a[text()='Donor Application']";
    private String btnAddSection = "//button[text()='Add Section']";
    private String btnCancel = "//div[@class='list-group-item active']//i[@class='fa fa-times']";
    private String btnAddSectionSave = "//div[@class='list-group-item active']//i[@class='fa fa-save']";
    private String btnEdit = "//div[@class='list-group-item']//i[@class='fa fa-edit']";
    private String btnSave = "//button[text()='Save']";
    private String btnModalSave = "//button[@class='btn btn-sm btn-danger']";
    private String txtSectionName = "css=div.list-group >div.list-group-item:last-child>div>input";


    public void goToApplicationsPage(WebDriver driver){
        driver.navigate().to("http://localhost:8080/settings/questionnaires");
        waitForElementInSeconds(3);
    }

    public void cancelAddingSectionAndVerifyTheResult(){
        getElement(lnkDonorApplication).click();
        getElement(btnAddSection).click();
        getElement(btnCancel).click();

        verifyTheResultAfterAddingOrCancel();
    }

    public void addASectionAndVerifyTheResult(WebDriver driver, String sectionName){
        getElement(lnkDonorApplication).click();
        getElement(btnAddSection).click();
        getElement(txtSectionName).sendKeys(sectionName,false);
        getElement(btnAddSectionSave).click();
        getElement(btnSave).click();

        driver.switchTo().activeElement();
        getElement(btnModalSave).click();
        verifyTheResultAfterAddingOrCancel();
    }

    public void editASectionAndVerifyTheResult(WebDriver driver, String sectionName, String editSectionName){
        getElement(lnkDonorApplication).click();
        getElement(btnAddSection).click();
        getElement(txtSectionName).sendKeys(sectionName,false);
        getElement(btnAddSectionSave).click();

        //edit
        getElement(btnEdit).click();
        Actions actions = new Actions(driver);
        // go to the beginning of input
        actions.sendKeys(Keys.HOME).build().perform();
        actions.keyDown(Keys.LEFT_SHIFT);
        actions.sendKeys(Keys.END);
        actions.keyUp(Keys.LEFT_SHIFT);
        actions.build().perform();
        actions.sendKeys(Keys.DELETE).build().perform();


      /*  getWebElement(txtSectionName).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getWebElement(txtSectionName).sendKeys(Keys.chord(Keys.CONTROL, "a"));*/

        getElement(txtSectionName).sendKeys(editSectionName, false);
        getElement(btnAddSectionSave).click();
        getElement("//div[@class='list-group-item active']//i[@class='fa fa-edit']").click();
        String sectionNameAfterEdit = getElement(txtSectionName).getAttribute("value");

        Assert.assertEquals(sectionNameAfterEdit, editSectionName, "Section name after edit is incorrect.");

    }

    public void verifyTheResultAfterAddingOrCancel(){
        if(getElementsCount(txtSectionName)>0){
            Assert.assertTrue(false, "Failed to cancel");
        }else{
            Assert.assertTrue(true, "Failed to cancel");
        }
    }
}
