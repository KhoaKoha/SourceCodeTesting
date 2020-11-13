package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by trangtnnguyen on 4/2/2020.
 */
public class PantryForGoodSettingsGeneralPage extends BasePage {
    public PantryForGoodSettingsGeneralPage(WebDriver driver) {
        super(driver);
    }

    private String btnChooseFile = "//input[@name='logo']";
    private String btnUpload = "//form[@name='logo-upload']//button";
    private String imgLogo = "//form[@name ='logo-upload']//img";
    private String path = "D:\\Learning\\Pantry For Good Site\\Pantry-for-Good\\assets\\media\\";

    public void goToSettingsPage(WebDriver driver){
        driver.navigate().to("http://localhost:8080/settings");
        waitForElementInSeconds(3);
    }

    public void uploadAnImageAndVerifyTheResult(WebDriver driver){
        prepareImageToUpload();

        WebElement chooseFile = driver.findElement(By.xpath(btnChooseFile));
        Actions actions = new Actions(driver);
        actions.moveToElement(chooseFile).build().perform();
        chooseFile.sendKeys(path+"logoToUpload.png");
        waitForElementInSeconds(3);
        getElement(btnUpload).click();

        //get image name after upload
        String newLogoSource = getElement(imgLogo).getAttribute("src");
        String separator = "//";
        String newLogoName = (newLogoSource.substring((newLogoSource.lastIndexOf(separator))+separator.length())).split("\\?")[0];
        System.out.println("Settings name:" + newLogoName);


        //get image name in Sign In page
        PantryForGoodHomePage home = new PantryForGoodHomePage(driver);
        home.signOut();
        PantryForGoodSignInPage signIn = new PantryForGoodSignInPage(driver);
        String logoNameInSignInPage = signIn.getLogoName();

        Assert.assertEquals(newLogoName,logoNameInSignInPage,"Logo is not updated correctly.");



    }

    public void prepareImageToUpload(){
        File sourceFile = new File(path + "logo.png");
        File destFile = new File( path + "logoToUpload.png");
        System.out.println(destFile.exists());

        if(!destFile.exists()){
            try {
                Files.copy(sourceFile.toPath(), destFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
