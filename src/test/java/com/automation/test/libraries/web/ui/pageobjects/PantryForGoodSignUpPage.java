package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Random;

/**
 * Created by trangtnnguyen on 3/19/2020.
 */
public class PantryForGoodSignUpPage extends BasePage{
    public PantryForGoodSignUpPage(WebDriver driver) {
        super(driver);
    }

    private String lnkSignUp ="//a[@href='/users/signup']";
    private String txtFirstName = "id=firstName";
    private String txtLastName = "id=lastName";
    private String txtEmail = "email";
    private String txtPassword = "password";
    private String txtConfirmPassword = "passwordConfirm";
    private String btnSignUp = "//button[@type='submit']";
    private String lnkSignIn = "//div[@class='text-center form-group']/a";
    public String lblUserName = "//li[@class='dropdown user user-menu']//span";

    public void goToSignUpPage(){
        getElement(lnkSignUp).click();
    }

    public void registerAnAccount(String fName, String lName, String email, String pw){
        Random rd = new Random();
        int rdNum = rd.nextInt(999999);
        getElement(txtFirstName).sendKeys(fName+rdNum, false);
        getElement(txtLastName).sendKeys(lName+rdNum, false);
        getElement(txtEmail).sendKeys(fName.toLowerCase()+rdNum+email, false);
        System.out.println(fName.toLowerCase()+rdNum+email);
        getElement(txtPassword).sendKeys(pw, false);
        getElement(txtConfirmPassword).sendKeys(pw, false);
        getElement(btnSignUp).click();
        waitForElementInSeconds(3);
    }

    public void clickOnSignInLink(){
        getElement(lnkSignIn).click();
    }


}
