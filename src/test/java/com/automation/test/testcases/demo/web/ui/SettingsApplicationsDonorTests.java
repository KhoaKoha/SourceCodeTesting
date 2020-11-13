package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSettingsApplicationDonors;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by trangtnnguyen on 4/9/2020.
 */
public class SettingsApplicationsDonorTests extends BaseTest {
    private String baseURL;

    public SettingsApplicationsDonorTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can add section at the Donors Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanAddNewSection(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsApplicationDonors p4gApp = new PantryForGoodSettingsApplicationDonors(driver);

        p4gPage.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Applications page
        p4gApp.goToApplicationsPage(driver);

        //Add a section then cancel and verify the result
        p4gApp.addASectionAndVerifyTheResult(driver, data.get("sectionName"));

        //Log out
        p4gPage.signOut();

    }

    //TC002-Verify user cancel adding section at the Donors Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanCancelAddingSection(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsApplicationDonors p4gApp = new PantryForGoodSettingsApplicationDonors(driver);

        p4gPage.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Applications page
        p4gApp.goToApplicationsPage(driver);

        //Add a section then cancel and verify the result
        p4gApp.cancelAddingSectionAndVerifyTheResult();


        //Log out
        p4gPage.signOut();

    }

    //TC003-Verify user can edit section at the Donors Application tab
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanEditASection(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsApplicationDonors p4gApp = new PantryForGoodSettingsApplicationDonors(driver);

        p4gPage.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Applications page
        p4gApp.goToApplicationsPage(driver);

        //Edit a section then cancel and verify the result
        p4gApp.editASectionAndVerifyTheResult(driver, data.get("sectionName"), data.get("editSectionName"));

        //Log out
        p4gPage.signOut();

    }



}
