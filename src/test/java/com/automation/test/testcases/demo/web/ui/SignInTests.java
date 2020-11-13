package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodProfilePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignUpPage;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SignInTests extends BaseTest {

    private String baseURL;

    public SignInTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user logs in to P4G page successfully
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserLogsInToP4GpageSuccessfully(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        p4gPage.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        //Verify if user is logged in successfully
        p4gPage.isUserLoggedInSuccessfully(data.get("expectedUsername"));
        //Log out
        p4gPage.signOut();
    }

    //TC002-Verthôify user cannot log in with invalid credentials
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCannotLogInWithInvalidCredentials(HashMap<String, String> data) {
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        p4gSignIn.navigateToURL(baseURL);
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Verify invalid credentials cannot log in
        p4gSignIn.verifyInvalidCredentialCannotLogIn();

    }


    //TC003-Verify user can log out successfully
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanLogOutSuccessfully(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        p4gPage.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Log out
        p4gPage.signOut();

        //Verify user is logged out successfully
        p4gSignIn.verifyUserLogOutSuccessfully();
    }

    //TC004-Verify user can update his profile from user dropdown menu
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanUpdateHisProfileFromUserDropdownMenu(HashMap<String, String> data) {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodProfilePage p4gProfile = new PantryForGoodProfilePage(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);

        //Sign up a new account
        p4gSignUp.navigateToURL(baseURL);
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"), data.get("password"));
        sleep(3000);

        //Go to profile page
        p4gProfile.goToProfilePage();

        //Edit user profile and verify if the update is made correctly
        p4gProfile.editUserProfileFromDropdownMenu(driver, data.get("fName"), data.get("lName"), data.get("fullName"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyNavBarMenuOfNormalUser(HashMap<String, String> data) {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        p4gSignUp.navigateToURL(baseURL);

        //Register new user
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"),data.get("password"));
        sleep(3000);

        //New user is always a Normal User after registering. Verify the Nav bar menu displayed correctly
        p4gPage.verifyNavBarMenuOfNormalUser(driver,data.get("navBarMenu"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifySideBarMenuOfAdminUser(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        p4gPage.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Verify the Side bar menu displayed correctly
        p4gPage.verifySideBarMenuOfAdminUser(driver,data.get("sideBarMenu"));

        //Log out
        p4gPage.signOut();
    }
}
