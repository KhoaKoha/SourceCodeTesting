package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodCustomersPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodDonorsPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by trangtnnguyen on 3/18/2020.
 */
public class CustomersTests extends BaseTest {
    private String baseURL;

    public CustomersTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC002-Verify user can search for an existing customer
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanSearchForAnExistingCustomer(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Search for an existing customer and verify the result
        p4gCustomer.searchAnItem(driver, null);

        //Log out
        //p4gPage.signOut();
    }

    //TC003-Verify user can search for an non-existing customer
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanSearchForANonExistingCustomer(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Search for an non existing customer
        p4gCustomer.searchAnItem(driver, data.get("searchKeyword"));

        //Verify the search result
        p4gCustomer.verifySearchResultForNonExistingItem(data.get("expectedResult"));


        //Log out
      //  p4gPage.signOut();
    }

    //TC004-Verify user can sort the result table by # column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanSortResultTableByIdColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Verify the descending result
        p4gCustomer.verifySortTableWorksCorrectly(driver, "#", "desc", data.get("pageName"));

        //Sort the table by Id in asc order and verify the result
        p4gCustomer.clickOnColumnToSort("#");
        p4gCustomer.verifySortTableWorksCorrectly(driver, "#", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify user can sort the result table by Name column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanSortResultTableByNameColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Verify the descending result
        p4gCustomer.clickOnColumnToSort("Name");
        p4gCustomer.verifySortTableWorksCorrectly(driver, "Name", "desc", data.get("pageName"));

        //Sort the table by Name in asc order and verify the result
        p4gCustomer.clickOnColumnToSort("Name");
        p4gCustomer.verifySortTableWorksCorrectly(driver, "Name", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC006-Verify user can sort the result table by Email column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanSortResultTableByEmailColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Verify the descending result
        p4gCustomer.clickOnColumnToSort("Email");
        p4gCustomer.verifySortTableWorksCorrectly(driver, "Email", "desc", data.get("pageName"));

        //Sort the table by Name in asc order and verify the result
        p4gCustomer.clickOnColumnToSort("Email");
        p4gCustomer.verifySortTableWorksCorrectly(driver, "Email", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify user can sort the result table by Status column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserCanSortResultTableByStatusColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Verify the descending result
        p4gCustomer.clickOnColumnToSort("Status");
        p4gCustomer.verifySortTableWorksCorrectly(driver, "Status", "desc", data.get("pageName"));

        //Sort the table by Name in asc order and verify the result
        p4gCustomer.clickOnColumnToSort("Status");
        p4gCustomer.verifySortTableWorksCorrectly(driver, "Status", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC009-Verify user can go to Edit page from customer detail page
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyUserCanGoToEditPageFromCustomerDetailPage(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Go to Edit page from customer detail page
        p4gCustomer.goToEditPageUsingEyeIconAndVerifyTheResult(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC010-Verify table size is displayed correctly
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_VerifyTableSizeIsDisplayedCorrectly(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Customer page
        p4gCustomer.goToCustomerPage();

        //Go to Edit page from customer detail page
        p4gCustomer.selectSizePerPageAndVerifyTableSize(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC011-Verify Customers link on left side menu lead to Customers page
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC011_VerifyCustomersOnLeftSideMeniLeadToCustomersPage(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gCustomer.goToCustomerPage();

        //Verify Donors link on left side menu lead to Donors page
        p4gCustomer.verifyCustomersLinkOnLeftMenuDirectToCustomersPage(driver);

        //Log out
        p4gPage.signOut();
    }

}
