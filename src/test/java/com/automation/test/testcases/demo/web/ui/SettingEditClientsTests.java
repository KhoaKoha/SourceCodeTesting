package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSettingsEditClientPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSettingsEditEmailsPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SettingEditClientsTests extends BaseTest {
    private String baseURL;

    public SettingEditClientsTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can update content at Home tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanUpdateContentAtHomeTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditClientPage p4gSettingsClientPage = new PantryForGoodSettingsEditClientPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Settings > Pages page
        p4gSettingsClientPage.goToEditClientPage(driver);

        //Update content
        p4gSettingsClientPage.updateContentOnHomeTab(driver);

        //Undo updating content at Home tab
        p4gSettingsClientPage.cancelUpdateContentOnHomeTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC002-Verify user can update content at Customers tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanUpdateContentAtCustomerTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditClientPage p4gSettingsClientPage = new PantryForGoodSettingsEditClientPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Settings > Pages page
        p4gSettingsClientPage.goToEditClientPage(driver);

        //Update content at Customers tab
        p4gSettingsClientPage.updateContentOnCustomersTab(driver);

        //Undo updating content at Customers tab
        p4gSettingsClientPage.cancelUpdateContentOnCustomersTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify user can update content at Donors tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanUpdateContentAtDonorsTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditClientPage p4gSettingsClientPage = new PantryForGoodSettingsEditClientPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Settings > Pages page
        p4gSettingsClientPage.goToEditClientPage(driver);

        //Update content at Donors tab
        p4gSettingsClientPage.updateContentOnDonorsTab(driver);


        //Undo updating content at Donors tab
        p4gSettingsClientPage.cancelUpdateContentOnDonorsTab(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC004-Verify user can update content at Donors tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanUpdateContentAtVolunteersTab(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsEditClientPage p4gSettingsClientPage = new PantryForGoodSettingsEditClientPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Settings > Pages page
        p4gSettingsClientPage.goToEditClientPage(driver);

        //Update content at Volunteers tab
        p4gSettingsClientPage.updateContentOnVolunteersTab(driver);

        //Undo updating content at Volunteers tab
        p4gSettingsClientPage.cancelUpdateContentOnVolunteersTab(driver);

        //Log out
        p4gPage.signOut();
    }
}
