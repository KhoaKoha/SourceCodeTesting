package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.*;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by trangtnnguyen on 3/11/2020.
 */
public class SettingsGeneralTests extends BaseTest {

    private String baseURL;

    public SettingsGeneralTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can upload logo successfully
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanUploadLogoSuccessfully(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodSettingsGeneralPage p4gGen = new PantryForGoodSettingsGeneralPage(driver);

        p4gPage.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //
        p4gGen.goToSettingsPage(driver);

        p4gGen.uploadAnImageAndVerifyTheResult(driver);


    }


}
