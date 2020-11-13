package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by trangtnnguyen on 3/17/2020.
 */
public class PantryForGoodProfilePage extends BasePage{
    public PantryForGoodProfilePage(WebDriver driver) {
        super(driver);
    }


    private String txtFirstName = "firstName";
    private String txtLastName = "lastName";
    private String txtEmail = "email";
    private String btnSaveProfile = "//button[@class='btn btn-large btn-primary']";
    private String lblUserNameInDropdown = "//nav//ul[1]/li/a/span";
    private String lblEditProfile = "//a[contains(text(),'Edit Profile')]";


    public WebObject getTxtFirstName() {
        return findWebElement(txtFirstName);
    }

    public WebObject getTxtLastName() {
        return findWebElement(txtLastName);
    }

    public WebObject getTxtEmail() {
        return findWebElement(txtEmail);
    }

    public WebObject getBtnSaveProfile() {
        return findWebElement(btnSaveProfile);
    }

    public WebObject getLblUserNameInDropdown() {
        return findWebElement(lblUserNameInDropdown);
    }

    public WebObject getLblEditProfile(){
        return findWebElement(lblEditProfile);
    }

    public void goToProfilePage(){
        getLblUserNameInDropdown().click();
        getLblEditProfile().click();
    }

    public void editUserProfileFromDropdownMenu(WebDriver driver,String fName, String lName,String fullName){
        //Get username before update
        String currentName = getLblUserNameInDropdown().getText();

        getTxtFirstName().sendKeys(fName, false);
        getTxtLastName().sendKeys(lName, false);
        getBtnSaveProfile().click();

        //Get username after update
        String afterUpdateName = getLblUserNameInDropdown().getText();

        //Verify if the update is made correctly
        Assert.assertEquals(afterUpdateName, fullName, "User is NOT updated correctly.");
        Assert.assertNotEquals(currentName,afterUpdateName,"User is NOT updated correctly.");

    }





}
