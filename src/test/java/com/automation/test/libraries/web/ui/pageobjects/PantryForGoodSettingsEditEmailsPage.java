package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.Assertion;

/**
 * Created by thuyluong on 04/01/2020.
 */


public class PantryForGoodSettingsEditEmailsPage extends BasePage {

    private String editorTwoCustomerAccepted = "//div/div[@class='ql-editor']/p[6]";
    private String btnUpdate = "//button[@class='btn btn-success']";
    private String tabCustomerApplication = "//a[@title='Customer Application']";
    private String tabCustomerRejected = "//a[@title='Customer Rejected']";
    private String tabCustomerUpdated = "//a[@title='Customer Updated']";
    private String tabDonationReceipt = "//a[@title='Donation Receipt']";
    private String tabDonationReceived = "//a[@title='Donation Received']";
    private String tabPasswordReset = "//a[@title='Password Reset']";
    private String tabPasswordResetGoogle = "//a[@title='Password Reset Google']";

    private String editorTwoCustomerApplication = "//div/div[@class='ql-editor']/p[3]";
    private String editorTwoCustomerRejected = "//div/div[@class='ql-editor']/p[7]";
    private String editorTwoCustomerUpdated = "//div/div[@class='ql-editor']/p[3]";
    private String editorTwoDonationReceipt = "//div/div[@class='ql-editor']/p[3]";
    private String editorTwoDonationReceived = "//div/div[@class='ql-editor']/p[6]";
    private String editorTwoPasswordReset = "//div/div[@class='ql-editor']/p[6]";
    private String editorTwoPasswordResetGoogle = "//div/div[@class='ql-editor']/p[4]";

    public PantryForGoodSettingsEditEmailsPage(WebDriver driver) {
        super(driver);
    }

    //Navigate to Edit Emails page
    public void goToEditEmailsPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/settings/emails");
        waitForElementInSeconds(3);
    }

    //Update email content on Customer Accepted tab
    public void updateEmailContentCustomerAccepted(WebDriver driver) {
        getElement(editorTwoCustomerAccepted).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerAccepted).sendKeys("Update email content on Customer Accepted tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoCustomerAccepted).getText();
        String expectedResult = "Update email content on Customer Accepted tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);

    }

    //Undo updating email content on Customer Accepted tab
    public void cancelUpdateEmailContentCustomerAccepted(WebDriver driver) {
        getElement(editorTwoCustomerAccepted).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerAccepted).sendKeys("If you have any questions or concerns, feel free to contact us.", false);
        getElement(btnUpdate).click();
    }

    //Undo updating email content at Customer Application tab
    public void updateEmailContentCustomerApplication(WebDriver driver) {
        getElement(tabCustomerApplication).click();
        getElement(editorTwoCustomerApplication).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerApplication).sendKeys("Update email content on Customer Application tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoCustomerApplication).getText();
        String expectedResult = "Update email content on Customer Application tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    //Undo updating email content at Customer Application tab
    public void cancelUpdateEmailContentCustomerApplication(WebDriver driver) {
        getElement(editorTwoCustomerApplication).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerApplication).sendKeys("Please visit <Foodbank Website> and login with your admin credentials.", false);
        getElement(btnUpdate).click();

    }

    //Update email content at Customer Rejected tab
    public void updateEmailContentCustomerRejected(WebDriver driver) {
        getElement(tabCustomerRejected).click();
        getElement(editorTwoCustomerRejected).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerRejected).sendKeys("Update email content on Customer Rejected tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoCustomerRejected).getText();
        String expectedResult = "Update email content on Customer Rejected tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    //Undo updating email content at Customer Rejected tab
    public void cancelUpdateEmailContentCustomerRejected(WebDriver driver) {
        getElement(editorTwoCustomerRejected).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerRejected).sendKeys("If you have any questions or concerns, feel free to contact us.", false);
        getElement(btnUpdate).click();
    }

    //Update email content at Customer Updated tab
    public void updateEmailContentCustomerUpdated(WebDriver driver) {
        getElement(tabCustomerUpdated).click();
        getElement(editorTwoCustomerUpdated).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerUpdated).sendKeys("Update email content on Customer Updated tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoCustomerUpdated).getText();
        String expectedResult = "Update email content on Customer Updated tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    //Undo updating email content at Customer Updated tab
    public void cancelUpdateEmailContentCustomerUpdated(WebDriver driver) {
        getElement(editorTwoCustomerUpdated).sendKeys(Keys.ENTER);
        getElement(editorTwoCustomerUpdated).sendKeys("Please visit <Foodbank Website> and login with your admin credentials.", false);
        getElement(btnUpdate).click();
    }

    //Update email content at Donation Receipt tab
    public void updateEmailContentDonationReceipt(WebDriver driver) {
        getElement(tabDonationReceipt).click();
        getElement(editorTwoDonationReceipt).sendKeys(Keys.ENTER);
        getElement(editorTwoDonationReceipt).sendKeys("Update email content on Donation Receipt tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoDonationReceipt).getText();
        String expectedResult = "Update email content on Donation Receipt tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    //Undo updating email content at Donation Receipt tab
    public void cancelUpdateEmailContentDonationReceipt(WebDriver driver) {
        getElement(editorTwoDonationReceipt).sendKeys(Keys.ENTER);
        getElement(editorTwoDonationReceipt).sendKeys("<Donation Receipt>", false);
        getElement(btnUpdate).click();
    }

    //Update email content at Donation Received tab
    public void updateEmailContentDonationReceived(WebDriver driver) {
        getElement(tabDonationReceived).click();
        getElement(editorTwoDonationReceived).sendKeys(Keys.ENTER);
        getElement(editorTwoDonationReceived).sendKeys("Update email content on Donation Received tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoDonationReceived).getText();
        String expectedResult = "Update email content on Donation Received tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    //Undo updating email content at Donation Received tab
    public void cancelUpdateEmailContentDonationReceived(WebDriver driver) {
        getElement(editorTwoDonationReceived).sendKeys(Keys.ENTER);
        getElement(editorTwoDonationReceived).sendKeys("If you have any questions or concerns, feel free to contact us.", false);
        getElement(btnUpdate).click();
    }

    //Update email content at Password Reset tab
    public void updateEmailContentPasswordReset(WebDriver driver) {
        getElement(tabPasswordReset).click();
        getElement(editorTwoPasswordReset).sendKeys(Keys.ENTER);
        getElement(editorTwoPasswordReset).sendKeys("Update email content on Password Reset tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoPasswordReset).getText();
        String expectedResult = "Update email content on Password Reset tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    //Undo updating email content at Password Reset tab
    public void cancelUpdateEmailPasswordReset(WebDriver driver) {
        getElement(editorTwoPasswordReset).sendKeys(Keys.ENTER);
        getElement(editorTwoPasswordReset).sendKeys("If you didn't make this request, you can ignore this email.The <Foodbank Name> support team", false);
        getElement(btnUpdate).click();
    }

    //Update email content at Password Reset Google tab
    public void updateEmailContentPasswordResetGoogle(WebDriver driver) {
        getElement(tabPasswordResetGoogle).click();
        getElement(editorTwoPasswordResetGoogle).sendKeys(Keys.ENTER);
        getElement(editorTwoPasswordResetGoogle).sendKeys("Update email content on Password Reset Google tab.", false);
        getElement(btnUpdate).click();
        String actualResult = getElement(editorTwoPasswordResetGoogle).getText();
        String expectedResult = "Update email content on Password Reset Google tab.";
        System.out.println(actualResult);
        Assert.assertEquals(expectedResult, actualResult);
    }

    //Undo updating email content at Password Reset Google tab
    public void cancelUpdateEmailContentPasswordResetGoogle(WebDriver driver) {
        getElement(editorTwoPasswordResetGoogle).sendKeys(Keys.ENTER);
        getElement(editorTwoPasswordResetGoogle).sendKeys("If you didn't make this request, you can ignore this email.The <Foodbank Name> support team", false);
        getElement(btnUpdate).click();
    }

}
