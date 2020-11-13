package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodCustomersPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodDeliveryDriversPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by trangtnnguyen on 3/31/2020.
 */
public class DeliveryDriversTests extends BaseTest {
    private String baseURL;

    public DeliveryDriversTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can search for a existing driver
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanSearchForAExistingDriver(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryDriversPage p4gDriver = new PantryForGoodDeliveryDriversPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gDriver.goToDriversPage(driver);

        //Search for an existing customer and verify the result
        p4gDriver.searchAnItem(driver, null);

        //Log out
        p4gPage.signOut();
    }

    //TC002-Verify user can search for an non-existing driver
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanSearchForANonExistingDriver(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryDriversPage p4gDriver = new PantryForGoodDeliveryDriversPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gDriver.goToDriversPage(driver);

        //Search for an non existing driver
        p4gDriver.searchAnItem(driver, data.get("searchKeyword"));

        //Verify the search result
        p4gDriver.verifySearchResultForNonExistingItem(data.get("expectedResult"));

        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify drivers are sorted by ID
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyDriversAreSortedById(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryDriversPage p4gDriver = new PantryForGoodDeliveryDriversPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gDriver.goToDriversPage(driver);

        //Verify the descending result
        p4gDriver.verifySortTableWorksCorrectly(driver, "#", "desc", data.get("pageName"));

        //Sort the table by Id in asc order and verify the result
        p4gDriver.clickOnColumnToSort("#");
        p4gDriver.verifySortTableWorksCorrectly(driver, "#", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC004-Verify drivers are sorted by Name
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyDriversAreSortedByName(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryDriversPage p4gDriver = new PantryForGoodDeliveryDriversPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gDriver.goToDriversPage(driver);

        //Verify the descending result
        p4gDriver.clickOnColumnToSort("Name");
        p4gDriver.verifySortTableWorksCorrectly(driver, "Name", "desc", data.get("pageName"));

        //Sort the table by Name in asc order and verify the result
        p4gDriver.clickOnColumnToSort("Name");
        p4gDriver.verifySortTableWorksCorrectly(driver, "Name", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC008-Verify drivers are sorted by Status
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyDriversAreSortedByStatus(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryDriversPage p4gDriver = new PantryForGoodDeliveryDriversPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gDriver.goToDriversPage(driver);

        //Verify the descending result
        p4gDriver.clickOnColumnToSort("Status");
        p4gDriver.verifySortTableWorksCorrectly(driver, "Status", "desc", data.get("pageName"));

        //Sort the table by Name in asc order and verify the result
        p4gDriver.clickOnColumnToSort("Status");
        p4gDriver.verifySortTableWorksCorrectly(driver, "Status", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC009-Verify table size is displayed correctly
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyTableSizeIsDisplayedCorrectly(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryDriversPage p4gDriver = new PantryForGoodDeliveryDriversPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gDriver.goToDriversPage(driver);

        //Go to Edit page from customer detail page
        p4gDriver.selectSizePerPageAndVerifyTableSize(driver);

        //Log out
        p4gPage.signOut();
    }

}
