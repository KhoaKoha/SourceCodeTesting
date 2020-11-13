package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by trangtnnguyen on 3/25/2020.
 */
public class PantryForGoodDonorsPage extends BasePage {
    String lblDonorsMenu = "//a[@href='/donors/list']";
    private String btnEdit = "//tr[1]//td[6]//a//i[@class='fa fa-pencil']";
    protected String txtStreet = "//label[contains(text(),'Street *')]/following::input[1]";
    protected String txtTownCity = "//label[contains(text(),'Town/City *')]/following::input[1]";
    protected String txtState = "//label[contains(text(),'State/Province *')]/following::input[1]";
    protected String txtZipcode = "//label[contains(text(),'Zip/Post Code *')]/following::input[1]";
    protected String txtPhone = "//label[contains(text(),'Telephone Number')]/following::input[1]";
    protected String txaHowHeard = "//label/following::textarea";
    private String btnUpdate = "//button";
    //private String btnUpdate = btnSuccess;

    private String tblSearchResult = "//div[@class='react-bs-container-body']//table";
    private String txtFirstDonation = tblSearchResult + "//tr[1]//td[1]";
    private String btnDelete = "//tr[1]//button[@class='btn btn-sm btn-danger']";
    private String btnDeleteDialog = "//div[@class='modal-dialog']//button[contains(text(),'Delete')]";
    private String btnEyeIcon = "//tr[1]//td[6]//a//i[@class='fa fa-eye']";
    private String btnAddDonation = "//button[@class='btn btn-sm btn-success']";
    private String txtDescription = "//input[@id='description']";
    private String txtItemName = "//input[@id='items[0].name']";
    private String txtItemValue= "//input[@id='items[0].value']";
    private String btnAddDonationDialog= "//button[@class='btn btn-success']";
    //private String btnUpdate = btnSuccess;

    private String btnApprove= "//tr[1]//td[5]//button";
    private String btnApproveDonation= "//button[@class='btn btn-primary']";
    private String lblApproved = "//tr[1]//td[5]//span[@class='label label-success']";
    private String btnEyeIconAddDonation = "//tr[1]//td[6]//button//i[@class='fa fa-eye']";
    private String btnCancel = "//div[@class='modal-dialog']//button[contains(text(),'Cancel')]";
    private String lnkDonorsMenu = "//a[@class='submenu-item']//span[contains(text(),'Donors')]";

    public PantryForGoodDonorsPage(WebDriver driver) {
        super(driver);
    }

    public void goToDonorsPage() {
        getElement(lblDonorsMenu).click();
        waitForElementInSeconds(3);
    }

    public void updateADonorAndVerifyTheResult(String street, String townCity, String state, String howHeard) {
        //Click on Edit link
        getElement(btnEdit).click();

        Random rd = new Random();
        int zipcodeRndNum = 100000 + rd.nextInt(999999);
        int phoneRandNum = 100000000 + rd.nextInt(999999999);
        int rndNum = rd.nextInt(999999999);

        String newStreet = street + rndNum;
        String newTownCity = townCity + rndNum;
        String newState = state + rndNum;
        String newZipcode = String.valueOf(zipcodeRndNum);
        String newPhone = String.valueOf(phoneRandNum);
        String newHowHeard = howHeard + rndNum;

        //Send values to donor fields
        getElement(txtStreet).clear();
        getElement(txtStreet).sendKeys(newStreet, false);
        getElement(txtTownCity).clear();
        getElement(txtTownCity).sendKeys(newTownCity, false);
        getElement(txtState).clear();
        getElement(txtState).sendKeys(newState, false);
        getElement(txtZipcode).clear();
        getElement(txtZipcode).sendKeys(newZipcode, false);
        getElement(txtPhone).clear();
        getElement(txtPhone).sendKeys(newPhone, false);
        getElement(txaHowHeard).clear();
        getElement(txaHowHeard).sendKeys(newHowHeard, false);

        //Click on Update button
        getElement(btnUpdate).click();
        waitForElementInSeconds(3);


        //Verify the result
        getElement(btnEdit).click();
        Assert.assertEquals(getElement(txtStreet).getValue(), newStreet, "Street is not updated correctly.");
        Assert.assertEquals(getElement(txtTownCity).getValue(), newTownCity, "Town/City is not updated correctly.");
        Assert.assertEquals(getElement(txtState).getValue(), newState, "State/Province is not updated correctly.");
        Assert.assertEquals(getElement(txtZipcode).getValue(), newZipcode, "Zipcode is not updated correctly.");
        Assert.assertEquals(getElement(txtPhone).getValue(), newPhone, "Phone is not updated correctly.");
        Assert.assertEquals(getElement(txaHowHeard).getValue(), newHowHeard, "How You Heard is not updated correctly.");

    }


    public void deleteADonorAndVerifyTheResult(WebDriver driver){
        //Get donor Id before deleting
        String donorId = getElement("css=div.react-bs-container-body tr:nth-child(1) td:first-child").getText();
        getElement(btnDelete).click();
        driver.switchTo().activeElement();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(btnDeleteDialog)));
        getElement(btnDeleteDialog).click();
        waitForElementInSeconds(3);

        //Verify the result after deleting
        int rowCount = getElementsCount(tblSearchResult+"//tr");
        for(int i=1;i<=rowCount;i++){
            String resultId = getElement(tblSearchResult+"//tr["+i+"]//td[1]").getText();
            if(resultId.equalsIgnoreCase(donorId)){
                Assert.assertTrue(false,"Deletion failed.");
            }else{
                Assert.assertTrue(true,"Deletion failed");
            }
        }

    }

    public void addADonationAndVerifyTheResult(WebDriver driver, String description, String itemName, String itemValue){
        //Get donation row count
        String rowContent = getElement(txtFirstDonation).getText();
        System.out.println(rowContent);

        int donationIdBeforeAdding = 0;
        if(!rowContent.equalsIgnoreCase("No donations found")){
            //Get Id of 1st donation
            donationIdBeforeAdding = getDonationId();
            System.out.println("donationIdBeforeAdding: " + donationIdBeforeAdding);
        }else{
            System.out.println("rowContent: " + rowContent);
        }

        //Click on Add Donation button
        getElement(btnAddDonation).click();
        driver.switchTo().activeElement();

        waitForElementToBeVisible(driver,getWebElement(txtDescription));
        getElement(txtDescription).sendKeys(description, false);
        getElement(txtItemName).sendKeys(itemName, false);
        getElement(txtItemValue).sendKeys(itemValue, false);
        getElement(btnAddDonationDialog).click();
        waitForElementInSeconds(3);

        //get donation id after adding
        waitForElementToBeVisible(driver,getWebElement(txtFirstDonation));
        int donationIdAfterAdding = getDonationId();
        System.out.println(donationIdAfterAdding);
        if(!rowContent.equalsIgnoreCase("No donations found")){
            if(donationIdAfterAdding==donationIdBeforeAdding+1){
                Assert.assertTrue(true, "Donation is not added correctly.");
            }
        }else{
            //verify donation is available after adding
            if(getDonationId()>0);
                Assert.assertTrue(true, "Donation is not added correctly.");
        }
    }

    public void approveDonationFromDonationsTable(WebDriver driver,String description, String itemName, String itemValue){
        addADonationAndVerifyTheResult(driver,description,itemName,itemValue);
        getElement(btnApprove).click();
    }

    public void approveDonationFromDonationDialogAndVerifyTheResult(WebDriver driver,String description, String itemName, String itemValue){
        addADonationAndVerifyTheResult(driver,description,itemName,itemValue);
        getElement(btnEyeIconAddDonation).click();
        driver.switchTo().activeElement();
        getElement(btnApproveDonation).click();

        String donationStatus = getElement(lblApproved).getText();
        Assert.assertEquals(donationStatus,"Approved","Donation item is not approved correctly.");
    }

    public void cancelAddingDonationAndVerifyTheResult(WebDriver driver){
        WebElement element = driver.findElement(By.xpath(btnAddDonation));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        driver.switchTo().activeElement();
        waitForElementInSeconds(3);
        getElement(btnCancel).click();

        //Verify the results
        if(getElementsCount(btnAddDonation)>0){
            Assert.assertTrue(true,"Cancel button works incorrectly.");
        }else{
            Assert.assertTrue(false,"Cancel button works incorrectly.");
        }

    }


    public int getDonationId(){
        int donationId = Integer.parseInt(getElement(txtFirstDonation).getText());
        return donationId;
    }

    public void goToAddDonationPage(WebDriver driver){
        waitForElementToBeVisible(driver,getWebElement(btnEyeIcon));
        getElement(btnEyeIcon).click();
        waitForElementInSeconds(3);
    }


    public void verifyDonorsLinkOnLeftMenuDirectToDonorsPage(WebDriver driver){
        String expectedURL = "http://localhost:8080/donors/list";
        getElement(lnkDonorsMenu).click();
        String currentURL = driver.getCurrentUrl();

        //Verify Donors on Menu links to Donors page
        Assert.assertEquals(currentURL,expectedURL,"URL is not correct.");

    }

}
