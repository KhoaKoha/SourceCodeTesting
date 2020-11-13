package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignUpPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by trangtnnguyen on 3/19/2020.
 */
public class SignUpTests extends BaseTest{
    private String baseURL;

    public SignUpTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can register successfully
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanRegisterSuccessfully(HashMap<String, String> data) {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        p4gSignUp.navigateToURL(baseURL);

        //Register new user
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"),data.get("password"));

    }


    //TC003-Verify new user can apply as a donor
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyNewUserCanApplyAsDonor(HashMap<String, String> data) {
        PantryForGoodHomePage p4gHome = new PantryForGoodHomePage(driver);
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        p4gSignUp.navigateToURL(baseURL);

        //Register new user
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"),data.get("password"));

        //Apply new user as donor and verify the result
        p4gHome.applyNewUserAsDonorAndVerifyTheResult(driver,data.get("street"), data.get("townCity"),
                                                        data.get("state"), data.get("howHeard"));
    }

    //TC007 - Verify SignIn link below Sign Up form works well
    @Test(priority = 1)
    public void TC007_VerifyUserCanGoToSignInPage(){
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);

        p4gSignUp.navigateToURL(baseURL);

        //go to Sign Up page
        p4gSignUp.goToSignUpPage();

        //click to Sign up link below Register form
        p4gSignUp.clickOnSignInLink();

        //verify user is navigated to Sign In page correctly
       // p4gSignUp.clickOnSignInLink();

        Assert.assertTrue(p4gSignIn.isPageTitleContains("Signin - Users - Pantry for Good"));
        System.out.println("User is on Sign In Page");
    }

    //TC004-Verify new user can apply as a customer
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyNewUserCanApplyAsACustomer(HashMap<String, String> data) {
        PantryForGoodHomePage p4gHome = new PantryForGoodHomePage(driver);
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        p4gSignUp.navigateToURL(baseURL);

        //Register new user
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"),data.get("password"));

        //Apply new user as customer and verify the result
        p4gHome.applyNewUserAsCustomerAndVerifyTheResult(data.get("DOB"));
    }

}
