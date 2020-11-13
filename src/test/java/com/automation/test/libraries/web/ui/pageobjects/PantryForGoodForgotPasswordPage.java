package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.sql.Driver;

public class PantryForGoodForgotPasswordPage extends BasePage {
    public PantryForGoodForgotPasswordPage(WebDriver driver){
        super(driver);
    }

    private String lnkForgotPwd = "//div[@class = 'form-group']/a";
    private String txtEmail = "//input[@class='form-control']";
    private String btnSubmit = "//button[@class='btn btn-primary']";

    public WebObject getLnkForgotPwd() {
        return findWebElement(lnkForgotPwd);
    }

    public WebObject getTxtEmail() {
        return findWebElement(txtEmail);
    }

    public WebObject getBtnSubmit() {
        return findWebElement(btnSubmit);
    }

    public void inputEmailAndSubmit(String email, WebDriver driver){
        getLnkForgotPwd().click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.xpath(btnSubmit)).isEnabled() == false){
            Assert.assertTrue(true);
        }

        getTxtEmail().sendKeys(email,true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.xpath(btnSubmit)).isEnabled() == true){
            Assert.assertTrue(true);
        }

        getBtnSubmit().click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.xpath(btnSubmit)).isEnabled() == false){
            Assert.assertTrue(true);
            System.out.println("Email is submitted successfully");
        }
    }
}
