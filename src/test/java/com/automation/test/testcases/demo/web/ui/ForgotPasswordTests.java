package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodForgotPasswordPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ForgotPasswordTests extends BaseTest {
    private String baseURL;

    public ForgotPasswordTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user is able to submit email to forgot password
    @Test(priority = 0, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserIsAbleToForgotPassword(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodForgotPasswordPage p4gForgotPwd = new PantryForGoodForgotPasswordPage(driver);

        // Go to P4G page
        p4gPage.navigateToURL(baseURL);

        //Click on Sign in link
        // p4gSignIn.getLnkSignIn().click();


        //Verify user is able to forgot password
        p4gForgotPwd.inputEmailAndSubmit(data.get("email"), driver);
    }
}
