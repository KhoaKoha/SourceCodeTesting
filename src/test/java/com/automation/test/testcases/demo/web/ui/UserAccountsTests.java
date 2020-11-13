package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.*;
import org.testng.annotations.Test;

import java.util.HashMap;

public class UserAccountsTests extends BaseTest {
    private String baseURL;

    public UserAccountsTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanSearchAnExistingUser(HashMap<String, String> data) {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);

        //Sign up a new account
        p4gSignUp.navigateToURL(baseURL);
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"), data.get("password"));
        sleep(3000);

        //get UserName after registering
        String userName[] = p4gSignUp.getElement(p4gSignUp.lblUserName).getText().split(" ");

        //Sign out
        p4gPage.signOut();
        sleep(3000);

        //Sign in with Admin user
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();

        //search an user just registered
        p4gUserAccounts.searchAnUserAccount(driver, userName[0]);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyAnUserCanBeSetAsAnAdminUser(HashMap<String, String> data) throws InterruptedException {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);

        //Sign up a new account
        p4gSignUp.navigateToURL(baseURL);
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"), data.get("password"));
        sleep(3000);

        //get FirstName and LastName after registering
        String userName[] = p4gSignUp.getElement(p4gSignUp.lblUserName).getText().split(" ");

        //Sign out
        p4gPage.signOut();
        sleep(3000);

        //Sign in with Admin user
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();

        //assign Admin Role To A Normal User just registered
        p4gUserAccounts.goToEditUserAccountPage(driver, userName[0]);
        p4gUserAccounts.assignAdminRoleToANormalUser(driver);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyAdminUserCannotDemoteHimself(HashMap<String, String> data) throws InterruptedException {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);

        //Sign up a new account
        p4gSignUp.navigateToURL(baseURL);

        //Sign in with Admin user
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();

        //logged user demote himself
        p4gUserAccounts.demoteAnAdminUser(driver,data.get("demote_user1"),true);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyAdminUserCanDemoteOtherAdminUser(HashMap<String, String> data) throws InterruptedException {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);

        //Sign up a new account
        p4gSignUp.navigateToURL(baseURL);

        //Sign in with Admin user
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();

        //demote other Admin User
        p4gUserAccounts.demoteAnAdminUser(driver, data.get("demote_user2"),true);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyAdminUserCanEditAnUserProfile(HashMap<String, String> data) throws InterruptedException {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);

        //Sign up a new account
        p4gSignUp.navigateToURL(baseURL);
        p4gSignUp.goToSignUpPage();
        p4gSignUp.registerAnAccount(data.get("fName"), data.get("lName"), data.get("email"), data.get("password"));
        sleep(3000);

        //get FirstName and LastName after registering
        //String userName[] = p4gSignUp.getLblUserName().getText().split(" ");
        String userName[] = p4gSignUp.getElement(p4gSignUp.lblUserName).getText().split(" ");

        //Sign out
        p4gPage.signOut();
        sleep(3000);

        //Sign in with Admin user
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();

        //assign Admin Role To A Normal User just registered
        p4gUserAccounts.goToEditUserAccountPage(driver, userName[0]);
        p4gUserAccounts.editUserProfileFields(driver,data.get("fName"), data.get("lName"), data.get("email"));

        //Log out
        p4gPage.signOut();
    }

    //TC004-Verify user can sort the result table by # column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanSortUserAccountsResultTableByUserIdColumn(HashMap<String, String> data) throws InterruptedException {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //Go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();
        sleep(3000);

        //Verify the descending result
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "User ID", "asc", data.get("pageName"));

        //Sort the table by Id in asc order and verify the result
        p4gUserAccounts.clickOnColumnToSort("User ID");
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "User ID", "desc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify user can sort the result table by First Name column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserCanSortUserAccountsResultTableByFirstNameColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //Go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();
        sleep(3000);

        //Verify the descending result
        p4gUserAccounts.clickOnColumnToSort("First Name");
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "First Name", "desc", data.get("pageName"));

        //Sort the table by First Name in asc order and verify the result
        p4gUserAccounts.clickOnColumnToSort("First Name");
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "First Name", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC008-Verify user can sort the result table by First Name column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyUserCanSortUserAccountsResultTableByLastNameColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //Go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();
        sleep(3000);

        //Verify the descending result
        p4gUserAccounts.clickOnColumnToSort("Last Name");
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "Last Name", "desc", data.get("pageName"));

        //Sort the table by Last Name in asc order and verify the result
        p4gUserAccounts.clickOnColumnToSort("Last Name");
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "Last Name", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }



    //TC009-Verify user can sort the result table by Email column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyUserCanSortUserAccountsResultTableByEmailColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //Go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();
        sleep(3000);

        //Verify the descending result
        p4gUserAccounts.clickOnColumnToSort("Email");
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "Email", "desc", data.get("pageName"));

        //Sort the table by Email in asc order and verify the result
        p4gUserAccounts.clickOnColumnToSort("Email");
        p4gUserAccounts.verifySortTableWorksCorrectly(driver, "Email", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_VerifyDefaultOptionAndListOfTablePageSizes(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodUserAccounts p4gUserAccounts = new PantryForGoodUserAccounts(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("lg_username"), data.get("lg_password"));
        sleep(3000);

        //Go to User Accounts page
        p4gUserAccounts.getMnuUserAccounts().click();
        sleep(3000);

        //verify default page size and list of page sizes
        p4gUserAccounts.verifyListPageSizesAndDefaultOption(driver,data.get("lstPageSizes"));

        //Log out
        p4gPage.signOut();
    }
}
