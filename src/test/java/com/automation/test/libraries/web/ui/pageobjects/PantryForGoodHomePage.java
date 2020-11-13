package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

/**
 * Created by trangtnnguyen on 3/11/2020.
 */
public class PantryForGoodHomePage extends BasePage {

    String lnkSignOut = "//a[contains(text(),'Signout')]";
    String lblUserNameInDropdown = "//nav//ul[1]/li/a/span";
    private String mnuNavBarHeader = "//div[@class='collapse navbar-collapse']/ul[@class='nav navbar-nav']"; // "//li/a";
    private String mnuSideBar = "//ul[@class='sidebar-menu']"; // "/li/a";
    private String lnkApplyAsDonor = "//a[@href='/donors/create']";
    private String btnSubmit = "//button[@class='btn btn-success']";
    private String lnkDonors = "//li[2]//a[@class='dropdown-toggle' and contains(text(),'Donors')]";
    private String lnkApplyACustomer = "//a[@href='/customers/create']";
    private String dtpDateOfBirth = "//input[@type='date']";
    private String rdGender = "//label[text()='Gender']//following-sibling::div//input[@value='Female']";
    private String rdAccommodation = "//label[text()='Accommodation Type']//following-sibling::div//input[@value='Own']";
    private String rdContact = "//label[contains(text(),'contact')]//following-sibling::div//input[@value='Phone']";
    private String cbFoodCategory = "//span[text()='Meat']//preceding-sibling::div//label//input//following-sibling::span";
    public PantryForGoodHomePage(WebDriver driver) {
        super(driver);
    }

    public WebObject getBtnSubmit() {
        return findWebElement(btnSubmit);
    }

    public WebObject getLnkApplyACustomer() {
        return findWebElement(lnkApplyACustomer);
    }

    public WebObject getDtpDateOfBirth() {
        return findWebElement(dtpDateOfBirth);
    }

    public void isUserLoggedInSuccessfully(String expectedUserName) {
        if (getElementsCount(lblUserNameInDropdown) > 0) {
            String userName = getElement(lblUserNameInDropdown).getText();
            System.out.println(userName);
            Assert.assertEquals(userName, expectedUserName, "User is logged in successfully");
            System.out.println("User logged in successfully");
        }
    }

    public void signOut() {
        getElement(lblUserNameInDropdown).click();
        getElement(lnkSignOut).click();
    }

    public void verifyNavBarMenuOfNormalUser(WebDriver driver, String expectedNavBarMenu) {
        String[] expectedMenus = expectedNavBarMenu.split(",");
        if (driver.findElement(By.xpath(mnuNavBarHeader)).isDisplayed()) {
            List<WebElement> navBarMenus = driver.findElements(By.xpath(mnuNavBarHeader + "//li/a"));
            if (navBarMenus != null && navBarMenus.size() == 3) {
                for (int i = 0; i < navBarMenus.size(); i++) {
                    Assert.assertEquals(navBarMenus.get(i).getText(), expectedMenus[i], "This is NOT a '" + expectedMenus[i] + "' menu");
                }
                System.out.println("Nav Bar Menu of Normal User is displayed correctly");
            } else {
                Assert.assertTrue(false);
            }
        } else {
            Assert.assertTrue(false);
            System.out.println("Nav bar menu is InVisible");
        }
    }

    public void verifySideBarMenuOfAdminUser(WebDriver driver, String expectedSideBarMenu) {
        String[] expectedMenus = expectedSideBarMenu.split(",");
        if (driver.findElement(By.xpath(mnuSideBar)).isDisplayed()) {
            List<WebElement> sideBarMenus = driver.findElements(By.xpath(mnuSideBar + "/li/a"));
            if (sideBarMenus != null && sideBarMenus.size() == 9) {
                for (int i = 0; i < sideBarMenus.size(); i++) {
                    Assert.assertEquals(sideBarMenus.get(i).getText(), expectedMenus[i], "This is NOT a '" + expectedMenus[i] + "' menu");
                    System.out.println(sideBarMenus.get(i).getText());
                }
                System.out.println("Side Bar Menu of Admin User is displayed correctly");
            } else {
                Assert.assertTrue(false);
            }
        } else {
            Assert.assertTrue(false);
            System.out.println("Side bar menu is InVisible");
        }
    }

    public void applyNewUserAsDonorAndVerifyTheResult(WebDriver driver, String street, String townCity, String state, String howHeard) {
        PantryForGoodDonorsPage donor = new PantryForGoodDonorsPage(driver);
        Random rd = new Random();
        int zipcodeRndNum = 100000 + rd.nextInt(999999);
        int phoneRandNum = 100000000 + rd.nextInt(999999999);
        int rndNum = rd.nextInt(999999999);

        getElement(lnkApplyAsDonor).click();

        String newStreet = street + rndNum;
        String newTownCity = townCity + rndNum;
        String newState = state + rndNum;
        String newZipcode = String.valueOf(zipcodeRndNum);
        String newPhone = String.valueOf(phoneRandNum);
        String newHowHeard = howHeard + rndNum;

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(donor.txtStreet)));

        //Send values to donor fields
        getElement(donor.txtStreet).clear();
        getElement(donor.txtStreet).sendKeys(newStreet, false);


        getElement(donor.txtTownCity).clear();
        getElement(donor.txtTownCity).sendKeys(newTownCity, false);

        getElement(donor.txtState).clear();
        getElement(donor.txtState).sendKeys(newState, false);

        getElement(donor.txtZipcode).clear();
        getElement(donor.txtZipcode).sendKeys(newZipcode, false);

        getElement(donor.txtPhone).clear();
        getElement(donor.txtPhone).sendKeys(newPhone, false);

        getElement(donor.txaHowHeard).clear();
        getElement(donor.txaHowHeard).sendKeys(newHowHeard, false);

        getElement(btnSubmit).click();
        waitForElementInSeconds(3);

        //Verify user is applied as donor successfully
        WebElement e = getWebElement(lnkDonors);
        wait.until(ExpectedConditions.visibilityOf(e));

        Boolean isPresent = getElementsCount(lnkDonors) > 0;
        if (isPresent) {
            Assert.assertTrue(true, "User is failed to apply as donor");
        } else {
            Assert.assertTrue(false, "User is failed to apply as donor");
        }
    }

    //just input Date of Birth and click Submit button for Notifications
    public void applyNewUserAsACustomer() {
        getLnkApplyACustomer().click();
        getDtpDateOfBirth().click();
        getDtpDateOfBirth().sendKeys("10-19-1987", false);
        getBtnSubmit().click();


    }

    public void applyNewUserAsCustomerAndVerifyTheResult(String DOB) {
        getElement(lnkApplyACustomer).click();
        waitForElementInSeconds(3);
        getDtpDateOfBirth().sendKeys(DOB, false);
        getElement(rdGender).click();
        getElement(rdAccommodation).click();
        getElement(rdContact).click();
        getElement(cbFoodCategory).click();
        getElement(btnSubmit).click();

        //Verify user is applied as customer successfully
        //Note: this test case is expected to failed since it's not working yet in UI
        if (getElementsCount(lnkApplyACustomer) > 0) {
            Assert.assertTrue(false, "Failed to apply as customer");
        } else {
            Assert.assertTrue(true, "Failed to apply as customer");
        }

    }
}


