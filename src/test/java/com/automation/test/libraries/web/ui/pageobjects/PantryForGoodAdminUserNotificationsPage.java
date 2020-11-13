package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class PantryForGoodAdminUserNotificationsPage extends BasePage {
    public PantryForGoodAdminUserNotificationsPage(WebDriver driver) {
        super(driver);
    }

    private String lnkNotificationsOnUserDropDown = "//a[contains(text(),'Notifications')]";
    private String lblUserNameInDropdown = "//li[@class='dropdown user user-menu']//span";

    private String tblSearchResult = "//div[@class='react-bs-container-body']//table";
    private String lnkEyeIcon = "//tr[1]//div//a//i[@class='fa fa-eye']";
    private String btnDelete = "//button[@class='btn btn-sm btn-danger']";
    private String btnDeleteOnTable = "//td//button[@class='btn btn-sm btn-danger']";
    private String mnuNotificationsNavBar = "//div[@class='navbar-custom-menu']/ul[position()=2]/li//span";
    private String ddlNotifications = "//div[@class='navbar-custom-menu']//ul[position()=2]//ul/li";
    private String actualSize, expectedSize;

    public WebObject getLnkNotificationsOnUserDropDown() {
        return findWebElement(lnkNotificationsOnUserDropDown);
    }

    public WebObject getLnkEyeIcon() {
        return findWebElement(lnkEyeIcon);
    }

    public WebObject getTblSearchResult() {
        return findWebElement(tblSearchResult);
    }

    public WebObject getLblUserNameInDropdown() {
        return findWebElement(lblUserNameInDropdown);
    }

    public WebObject getBtnDelete() {
        return findWebElement(btnDelete);
    }

    public WebObject getMnuNotificationsNavBar() {
        return findWebElement(mnuNotificationsNavBar);
    }

    public List<WebElement> countRowsOnNotificationsTable(WebDriver driver) {
        PantryForGoodHomePage p4gHome = new PantryForGoodHomePage(driver);
        //p4gHome.getLblUserNameInDropdown().click();
        p4gHome.getElement(p4gHome.lblUserNameInDropdown).click();
        getLnkNotificationsOnUserDropDown().click();

        List<WebElement> rows = driver.findElements(By.xpath(tblSearchResult + "//tr"));
        return rows;
    }

    public void goToNotificationsPage(WebDriver driver) {
        PantryForGoodHomePage p4gHome = new PantryForGoodHomePage(driver);
        //p4gHome.getLblUserNameInDropdown().click();
        p4gHome.getElement(p4gHome.lblUserNameInDropdown).click();
        getLnkNotificationsOnUserDropDown().click();
        waitForElementInSeconds(3);
    }

    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public void createDataForNotificationsTable(WebDriver driver, String fName, String lName, String email, String pw, String street, String townCity, String state, String howHeard) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        //Sign out
        p4gPage.signOut();
        waitForElementInSeconds(3);

        for (int i = 0; i < 4; i++) {
            p4gSignUp.goToSignUpPage();
            p4gSignUp.registerAnAccount(fName, lName, email, pw);
            p4gPage.applyNewUserAsDonorAndVerifyTheResult(driver, street, townCity, state, howHeard);
            p4gPage.applyNewUserAsACustomer();
            waitForElementInSeconds(5);
            //Sign out
            getLblUserNameInDropdown().click();
            //p4gPage.getLnkSignOut().click();
             p4gPage.getElement(p4gPage.lnkSignOut).click();
            if (isAlertPresent(driver)){
                driver.switchTo().alert().accept();
                waitForElementInSeconds(5);
            }
        }
    }

    public void searchAnItem(WebDriver driver, String keyword) {
        if (keyword != null) {
            getTxtSearch().sendKeys(keyword, false);
        } else {
            int rows = countRowsOnNotificationsTable(driver).size();
            String get1stColumnText = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[1]")).getText();
            if (rows > 1) {
                keyword = driver.findElement(By.xpath(tblSearchResult + "//tr[2]//td[3]")).getText();
            } else if (!get1stColumnText.equals("No notifications found") && rows == 1) {
                keyword = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[3]")).getText();
            }
            String expectedSearchResult = keyword;
            getTxtSearch().sendKeys(keyword, false);
            String actualSearchResult = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[3]")).getText();

            Assert.assertEquals(actualSearchResult, expectedSearchResult, "The search result is not matched with the keyword");
        }
    }

    public void deleteAllNotifications(WebDriver driver){
        List<WebElement> allDeleteButton = driver.findElements(By.xpath(btnDelete));
        String getFirstCell = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[1]")).getText();

        if(allDeleteButton.size() > 1 && !getFirstCell.equals("No notifications found")){
            allDeleteButton.get(0).click();

            waitForElementInSeconds(3);

            driver.switchTo().activeElement();
            WebElement btnDeleteOnPopup = driver.findElement(By.xpath("//div[@class='modal-content']" + btnDelete));
            btnDeleteOnPopup.click();

            waitForElementInSeconds(3);
            List<WebElement> rows = driver.findElements(By.xpath(tblSearchResult + "//tr"));
            String getFirstCell1 = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[1]")).getText();
            if (rows.size() == 1 && getFirstCell1.equals("No notifications found")){
                Assert.assertTrue(true,"All notifications are not deleted");
            } else {
                Assert.assertTrue(false, "All notifications are not deleted");
            }
        } else {
            Assert.assertTrue(false, "There is no Notifications to delete");
        }
    }

    public void deleteASingleNotification(WebDriver driver){
        List<WebElement> result = countRowsOnNotificationsTable(driver);
        int countedRows = result.size();
        String getFirstCell = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[1]")).getText();

        if(countedRows > 1 && !getFirstCell.equals("No notifications found")){
            String getFirstUrl = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[4]")).getText();
            List<WebElement> getAllBtnDelete = driver.findElements(By.xpath(btnDeleteOnTable));
            getAllBtnDelete.get(0).click();

            waitForElementInSeconds(3);

            driver.switchTo().activeElement();
            WebElement btnDeleteOnPopup = driver.findElement(By.xpath("//div[@class='modal-content']" + btnDelete));
            btnDeleteOnPopup.click();

            waitForElementInSeconds(3);

            searchAnItem(driver,getFirstUrl);
            String getFirstCell1 = driver.findElement(By.xpath(tblSearchResult + "//tr[1]//td[1]")).getText();
            if (getFirstCell1.equals("No notifications found")){
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false,"Selected notification is not deleted");
            }
        } else {
            Assert.assertTrue(false, "There is no Notifications to delete");
        }
    }

    public void verifyNumberCountedOnNavBarNotifications(WebDriver driver){
        String lblNotifications = getMnuNotificationsNavBar().getText();
        System.out.println(lblNotifications);
        String numberOnNotifications = lblNotifications.split("[)]")[0].split("[(]")[1];
        System.out.println(numberOnNotifications);
        getMnuNotificationsNavBar().click();
        List<WebElement> lstNotifications  = driver.findElements(By.xpath(ddlNotifications));

        if(numberOnNotifications.equals(String.valueOf(lstNotifications.size()))){
            Assert.assertTrue(true);
        } else {
            Assert.assertTrue(false,"The number Notifications are counted incorrectly");
        }
    }

    public void viewCustomerInfoFromNotifications(WebDriver driver){
        List<WebElement> result = driver.findElements(By.xpath(tblSearchResult + "//tr"));
        if (result != null && result.size() > 0){
            String urlOfCustomer = driver.findElement(By.xpath("//tr[1]//td[4]")).getText();
            getLnkEyeIcon().click();
            String actualURL = driver.getCurrentUrl();
            String expectedURL = "http://localhost:8080" + urlOfCustomer;

            Assert.assertEquals(actualURL, expectedURL, "Navigate to wrong customer");
        } else {
            Assert.assertTrue(false,"No data to view");
        }
    }


}


