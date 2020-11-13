package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSettingsCustomerApplicationPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSettingsEditEmailsPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SettingsCustomerApplicationTests extends BaseTest {
    private String baseURL;

    public SettingsCustomerApplicationTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can add section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanAddSectionAtCustomerApplicationTab(HashMap<String, String> data) throws InterruptedException {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.addSectionAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC002-Verify user can cancel adding section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCancelAddSectionAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.cancelAddSectionAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify user can update section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanUpdateSectionAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.updateSectionAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC004-Verify user can cancel updating section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCancelUpdateSectionAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.cancelUpdateSectionAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify user can cancel updating section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanDeleteSectionAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        //     p4gSettingsCustomerApplication.deleteSectionAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC006-Verify user can cancel updating section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCancelDeleteSectionAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.cancelDeleteSectionAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify user can link field to section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserCanLinkFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.linkFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC008-Verify user cancel link field to section at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyUserCancelLinkFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.cancelLinkFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC009-Verify user can add field at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyUserCanAddFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.addFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC010-Verify user cancel add field at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_VerifyUserCancelAddFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.cancelAddFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC011-VVerify that validation error message displays when adding Link Field without Question Label at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC011_VerifyMessageDisplaysWhenUserAddFieldWithoutNameAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.addFieldWithoutNameAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }


    //TC012-Verify user edit field from Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC012_VerifyUserCanEditFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.editFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC013-Verify user cancel edit field from Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC013_VerifyUserCancelEditFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.cancelEditFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC014-Verify user delete field from Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC014_VerifyUserCanDeleteFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.deleteFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC015-Verify user cancel delete field from Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC015_VerifyUserCancelDeleteFieldAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.cancelDeleteFieldAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC016-Verify user can edit field name and type from Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC016_VerifyUserCanEditFieldNameAndTypeAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsCustomerApplicationPage p4gSettingsCustomerApplication = new PantryForGoodSettingsCustomerApplicationPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsCustomerApplication.goToCustomerApplicationPage(driver);

        //Add section on Customer Application page
        p4gSettingsCustomerApplication.editFieldAndTypeAtCustomerApplicationTab(driver);

        //Log out
        p4gPage.signOut();
    }
}
