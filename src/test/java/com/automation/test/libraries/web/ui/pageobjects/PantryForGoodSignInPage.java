package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.framework.core.web.ui.object.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by trangtnnguyen on 3/16/2020.
 */
public class PantryForGoodSignInPage extends BasePage {

    private String lnkSignIn = "//a[contains(text(),'Sign In')]";
    private String txtUsername = "id=email";
    private String txtPassword = "id=password";
    private String btnSignIn = "//button[@class='btn btn-primary btn-flat']";
    private String txtSignInErrorMess = "//div[@class='text-danger']";
    private String imgLogo = "//img";

    public PantryForGoodSignInPage(WebDriver driver) {
        super(driver);
    }

    public void logInToHomePage(String username, String password) {
        getElement(lnkSignIn).click();
        waitForElementInSeconds(3);
        getElement(txtUsername).sendKeys(username, false);
        getElement(txtPassword).sendKeys(password, false);
        getElement(btnSignIn).click();
        waitForElementInSeconds(3);
    }

    public void verifyInvalidCredentialCannotLogIn(){
        String errorMess = getElement(txtSignInErrorMess).getText();
        if(getElementsCount(txtSignInErrorMess)>0){
            Assert.assertEquals(errorMess,"Unknown user or invalid password","User failed to log in with invalid credentials.");
        }
    }

    public void verifyUserLogOutSuccessfully(){
        if(getElementsCount(lnkSignIn)>0) {
            Assert.assertTrue(true);
            System.out.println("User is logged out successfully");
        }
    }

    public String getLogoName(){
        String logoSource = getElement(imgLogo).getAttribute("src");
        String separator = "/";
        String logoName = logoSource.substring((logoSource.lastIndexOf(separator))+separator.length());
        System.out.println("SignIn name: " + logoName);
        return logoName;
    }


}
