package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

import static javax.swing.text.html.CSS.getAttribute;

public class PantryForGoodUserAccounts extends BasePage {
    public PantryForGoodUserAccounts(WebDriver driver){
        super(driver);
    }

    private String mnuUserAccounts = "//a[@class='submenu-item']//span[contains(text(),'User Accounts')]";
    private String txtSearch = "//input[@class='form-control ']";
    private String tblUsersList = "//div[@class='react-bs-container-body']//table";
    private String chkAdmin = "//input[@name='isAdmin']";
    private String lblAdminCheck = "//div[@class='checkbox']//span";
    private String btnUpdate = "//button[@class='btn btn-large btn-primary']";
    private String lblSuccessMsg = "//div[@class='text-center text-success']";
    private String lblErrorMsg = "//div[@class='text-danger']";
    private String txtFirstName = "//input[@name='firstName']";
    private String txtLastName = "//input[@name='lastName']";
    private String txtEmail = "//input[@name='email']";

    public WebObject getMnuUserAccounts() {
        return findWebElement(mnuUserAccounts);
    }

    public WebObject getTblUsersList() {
        return findWebElement(tblUsersList);
    }

    public WebObject getTxtSearch() {
        return findWebElement(txtSearch);
    }

    public WebObject getChkAdmin() {
        return findWebElement(chkAdmin);
    }

    public WebObject getBtnUpdate() {
        return findWebElement(btnUpdate);
    }

    public WebObject getLblSuccessMsg() {
        return findWebElement(lblSuccessMsg);
    }

    public WebObject getLblErrorMsg() {
        return findWebElement(lblErrorMsg);
    }

    public WebObject getLblAdminCheck() {
        return findWebElement(lblAdminCheck);
    }

    public WebObject getTxtFirstName() {
        return findWebElement(txtFirstName);
    }

    public WebObject getTxtLastName() {
        return findWebElement(txtLastName);
    }

    public WebObject getTxtEmail() {
        return findWebElement(txtEmail);
    }

    public void searchAnUserAccount(WebDriver driver, String keyword) {
        if (keyword != null) {
            String expectedSearchResult = keyword;
            getTxtSearch().sendKeys(keyword, false);
            List<WebElement> rows = driver.findElements(By.xpath(tblUsersList + "//tr"));

            if (rows != null && rows.size() > 0){
                String actualSearchResult = driver.findElement(By.xpath(tblUsersList + "//tr[1]//td[2]")).getText();
                if (actualSearchResult.contains(expectedSearchResult)) {
                    Assert.assertTrue(true);
                    System.out.println("The search result is matched with the keyword");
                }
            } else {
                System.out.println("There is no results matched with the keyword");
            }

        } else {
            keyword = driver.findElement(By.xpath(tblUsersList + "//tr[2]//td[2]")).getText();
            String expectedSearchResult = keyword;

            getTxtSearch().sendKeys(keyword, false);
            String actualSearchResult = driver.findElement(By.xpath(tblUsersList + "//tr[1]//td[2]")).getText();

            Assert.assertEquals(actualSearchResult, expectedSearchResult, "The search result is not matched with the keyword");

        }
    }

    public void goToEditUserAccountPage(WebDriver driver, String keyword) throws InterruptedException {
        searchAnUserAccount(driver,keyword);
        WebElement btnEdit = driver.findElement(By.xpath(tblUsersList + "//tr[1]//td[5]//a"));
        btnEdit.click();
        Thread.sleep(3000);
    }

    public void editUserProfileFields(WebDriver driver, String firstName, String lastName, String email) throws InterruptedException {
        Random rd = new Random();
        int rdNum = rd.nextInt(999999);

        getTxtFirstName().sendKeys(firstName + rdNum,false);
        String updatedFirstName = getTxtFirstName().getValue();
        System.out.println(updatedFirstName);

        getTxtLastName().sendKeys(lastName + rdNum,false);
        String updatedLastName = getTxtLastName().getValue();

        getTxtEmail().sendKeys(firstName.toLowerCase()+rdNum+email, false);
        String updatedEmail = getTxtEmail().getValue();

        getBtnUpdate().click();
        String successMsg = getLblSuccessMsg().getText();
        String expectedMsg = "Profile Saved Successfully";
        Assert.assertEquals(successMsg,expectedMsg);
        Thread.sleep(3000);

        getMnuUserAccounts().click();
        searchAnUserAccount(driver,updatedEmail);
        String resultFirstName = driver.findElement(By.xpath(tblUsersList + "//tr[1]//td[2]")).getText();
        String resultLastName = driver.findElement(By.xpath(tblUsersList + "//tr[1]//td[3]")).getText();
        String resultEmail = driver.findElement(By.xpath(tblUsersList + "//tr[1]//td[4]")).getText();

        Assert.assertEquals(resultFirstName,updatedFirstName,"First Name is not updated on Users List.");
        Assert.assertEquals(resultLastName,updatedLastName,"Last Name is not updated on Users List.");
        Assert.assertEquals(resultEmail,updatedEmail,"Email is not updated on Users List.");
    }

    public boolean isAdminUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        String isChecked = driver.findElement(By.xpath(chkAdmin)).getAttribute("checked");
        System.out.println(isChecked);
        if (isChecked == null){
            return false;
        } else {
            return isChecked.equals("true");
        }
    }

    public void clickOnCheckBoxIsAdmin(WebDriver driver) {
        WebElement checkbox = driver.findElement(By.xpath(chkAdmin));
        Actions action = new Actions(driver);
        action.moveToElement(checkbox).click(checkbox).build().perform();
    }

    public void assignAdminRoleToANormalUser(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        clickOnCheckBoxIsAdmin(driver);
        //getLblAdminCheck().click();
        getBtnUpdate().click();
        Thread.sleep(3000);

        String successMsg = getLblSuccessMsg().getText();
        String expectedMsg = "Profile Saved Successfully";
        Assert.assertEquals(successMsg,expectedMsg);

        driver.navigate().refresh();
        Thread.sleep(3000);

        boolean isAdminUser = isAdminUser(driver);
        if (isAdminUser){
            Assert.assertTrue(true);
            System.out.println("Selected user is assigned as an Admin successfully");
        } else {
            Assert.assertTrue(false);
            System.out.println("Failed");
        }
    }

    public void demoteAnAdminUser(WebDriver driver, String keyword, boolean flagEdit) throws InterruptedException {
        if(flagEdit){
            goToEditUserAccountPage(driver, keyword);
        }

        String firstName = driver.findElement(By.xpath(txtFirstName)).getAttribute("value");
        String lastName = driver.findElement(By.xpath(txtLastName)).getAttribute("value");
        String userName = firstName + " " + lastName;

        System.out.println(userName);

        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        boolean isAdmin = isAdminUser(driver);
        System.out.println(isAdmin);

        if (isAdmin) {
            if (p4gSignUp.getElement(p4gSignUp.lblUserName).getText().equals(userName)) {
                clickOnCheckBoxIsAdmin(driver);
                getBtnUpdate().click();
                Thread.sleep(3000);
                String errorMsg = getLblErrorMsg().getText();
                String expectedMsg = "You are not allowed to demote yourself";
                Assert.assertEquals(errorMsg,expectedMsg);
                System.out.println("Admin user cannot demote himself successfully");
            } else {
                clickOnCheckBoxIsAdmin(driver);
                getBtnUpdate().click();
                Thread.sleep(3000);

                String successMsg = getLblSuccessMsg().getText();
                String expectedMsg = "Profile Saved Successfully";
                Assert.assertEquals(successMsg,expectedMsg);
                System.out.println("Admin user can demote other Admin successfully");
            }
        } else {
            assignAdminRoleToANormalUser(driver);
            demoteAnAdminUser(driver,keyword,false);
        }
    }
}
