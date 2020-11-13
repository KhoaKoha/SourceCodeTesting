package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodPackingListPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSettingsEditEmailsPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by thuyluong on 04/01/2020.
 */
public class SettingsEditEmailsTests extends BaseTest {

    private String baseURL;

    public SettingsEditEmailsTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can update email content at the Customer Accepted tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanUpdateEmailContentAtCustomerAcceptedTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentCustomerAccepted(driver);

        //Undo updating email content at Customer Accepted tab
        p4gSettingsEmails.cancelUpdateEmailContentCustomerAccepted(driver);

        //Log out
        p4gPage.signOut();
    }


    //TC002-Verify user can update email content at the Customer Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanUpdateEmailContentAtCustomerApplicationTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentCustomerApplication(driver);

        //Undo updating email content at Customer Application tab
        p4gSettingsEmails.cancelUpdateEmailContentCustomerApplication(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify user can update email content at the Customer Rejected tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanUpdateEmailContentAtCustomerRejectedTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentCustomerRejected(driver);

        //Undo updating email content at Customer Rejected tab
        p4gSettingsEmails.cancelUpdateEmailContentCustomerRejected(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC004-Verify user can update email content at the Customer Updated tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanUpdateEmailContentAtCustomerUpdatedTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentCustomerUpdated(driver);

        //Undo updating email content at Customer Rejected tab
        p4gSettingsEmails.cancelUpdateEmailContentCustomerUpdated(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify user can update email content at the Donation Receipt tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanUpdateEmailContentAtDonationReceiptTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentDonationReceipt(driver);

        //Undo updating email content at Customer Rejected tab
        p4gSettingsEmails.cancelUpdateEmailContentDonationReceipt(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC006-Verify user can update email content at the Donation Received tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanUpdateEmailContentAtDonationReceivedTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentDonationReceived(driver);

        //Undo updating email content at Customer Rejected tab
        p4gSettingsEmails.cancelUpdateEmailContentDonationReceived(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify user can update email content at the Password Reset tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserCanUpdateEmailContentAtPasswordResetTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentPasswordReset(driver);

        //Undo updating email content at Customer Rejected tab
        p4gSettingsEmails.cancelUpdateEmailPasswordReset(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC008-Verify user can update email content at the Password Reset Google tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyUserCanUpdateEmailContentAtPasswordResetGoogleTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditEmailsPage p4gSettingsEmails = new PantryForGoodSettingsEditEmailsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Setting Email page
        p4gSettingsEmails.goToEditEmailsPage(driver);

        //Update email content
        p4gSettingsEmails.updateEmailContentPasswordResetGoogle(driver);

        //Undo updating email content at Customer Rejected tab
        p4gSettingsEmails.cancelUpdateEmailContentPasswordResetGoogle(driver);

        //Log out
        p4gPage.signOut();
    }
}
