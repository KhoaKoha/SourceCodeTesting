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
 * Created by trangtnnguyen on 3/25/2020.
 */
public class DonorsTests extends BaseTest {

    private String baseURL;

    public DonorsTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can update a donor without error
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanUpdateADonorWithoutError(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Update a donor and verify the result
        p4gDonor.updateADonorAndVerifyTheResult(data.get("street"), data.get("townCity"), data.get("state"), data.get("howHeard"));

        //Log out
        p4gPage.signOut();
    }

    //TC002-Verify user can search for an existing donor
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserSearchForAnExistingDonor(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Search an existing donor and verify the result
        p4gDonor.searchAnItem(driver, null);

        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify user can search for an non-existing donor
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanSearchForANonExistingDonor(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Search an donor and verify the result
        p4gDonor.searchAnItem(driver, data.get("searchKeyword"));

        //Verify the search result
        p4gDonor.verifySearchResultForNonExistingItem(data.get("expectedResult"));

        //Log out
        // p4gPage.signOut();
    }

    //TC004-Verify user can sort the donor result table by # column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanSortDonorsResultTableByIdColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Verify the descending result
        p4gDonor.verifySortTableWorksCorrectly(driver, "#", "desc", data.get("pageName"));

        //Sort the table by Id in asc order and verify the result
        p4gDonor.clickOnColumnToSort("#");
        p4gDonor.verifySortTableWorksCorrectly(driver, "#", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify user can sort the donor result table by Name column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanSortDonorsResultTableByNameColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Verify the descending result
        p4gDonor.clickOnColumnToSort("Name");
        p4gDonor.verifySortTableWorksCorrectly(driver, "Name", "desc", data.get("pageName"));

        //Sort the table by Name in asc order and verify the result
        p4gDonor.clickOnColumnToSort("Name");
        p4gDonor.verifySortTableWorksCorrectly(driver, "Name", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC006-Verify user can sort the donor result table by Email column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanSortDonorsResultTableByEmailColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Verify the descending result
        p4gDonor.clickOnColumnToSort("Email");
        p4gDonor.verifySortTableWorksCorrectly(driver, "Email", "desc", data.get("pageName"));

        //Sort the table by Email in asc order and verify the result
        p4gDonor.clickOnColumnToSort("Email");
        p4gDonor.verifySortTableWorksCorrectly(driver, "Email", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify user can delete a donor without error
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserCanDeleteADonorWithoutError(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Delete a donor
        p4gDonor.deleteADonorAndVerifyTheResult(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC008-Verify user can add a donation without error
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyUserCanAddADonationWithoutError(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Go to Add Donation page
        p4gDonor.goToAddDonationPage(driver);

        //Add a donation
        p4gDonor.addADonationAndVerifyTheResult(driver, data.get("description"), data.get("itemName"), data.get("itemValue"));

        //Log out
        p4gPage.signOut();
    }

    //TC009-Verify user can approve a donation from Donations table
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyUserCanApproveADonationFromDonationsTable(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Go to Add Donation page
        p4gDonor.goToAddDonationPage(driver);

        //Approve a donation
        p4gDonor.approveDonationFromDonationsTable(driver, data.get("description"), data.get("itemName"), data.get("itemValue"));

        //Log out
        p4gPage.signOut();
    }

    //TC010-Verify table size is displayed correctly
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_VerifyDonorsTableSizeIsDisplayedCorrectly(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodCustomersPage p4gCustomer = new PantryForGoodCustomersPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Go to Add Donation page
        p4gDonor.goToAddDonationPage(driver);

        //Go to Edit page from customer detail page
        p4gCustomer.selectSizePerPageAndVerifyTableSize(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC011-Verify user can approve a donation from Donation dialog
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC011_VerifyUserCanApproveADonationFromDonationDialog(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Go to Add Donation page
        p4gDonor.goToAddDonationPage(driver);

        //Approve a donation from donation dialog
        p4gDonor.approveDonationFromDonationDialogAndVerifyTheResult(driver, data.get("description"), data.get("itemName"), data.get("itemValue"));

        //Log out
        p4gPage.signOut();
    }

    //TC012-Verify user can cancel adding a donation
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC011_VerifyUserCanCancelAddingADonation(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Go to Add Donation page
        p4gDonor.goToAddDonationPage(driver);

        //Cancel adding a donation
        p4gDonor.cancelAddingDonationAndVerifyTheResult(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC013-Verify Donors link on left side menu lead to Donors page
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC012_VerifyDonorsOnLeftSideMeniLeadToDonorsPage(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDonorsPage p4gDonor = new PantryForGoodDonorsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Donors page
        p4gDonor.goToDonorsPage();

        //Verify Donors link on left side menu lead to Donors page
        p4gDonor.verifyDonorsLinkOnLeftMenuDirectToDonorsPage(driver);

        //Log out
        p4gPage.signOut();
    }

}
