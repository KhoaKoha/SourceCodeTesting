package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.*;
import org.testng.annotations.Test;

import java.util.HashMap;

public class VolunteersTests extends BaseTest {
    private String baseURL;

    public VolunteersTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    @Test(priority = 0, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserIsAbleToViewVolunteerInfo(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //view a volunteer information by clicking on Eye button on the table result
        p4gVolunteers.viewVolunteersInfo(driver);

        //Log out
        p4gPage.signOut();
    }

    @Test(priority = 1, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanSearchForAnExistingVolunteer(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //Search an existing volunteer and verify the result
        p4gVolunteers.searchAnItem(driver, null);

        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify user can search for an non-existing customer
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyNoDataOnTableWithSearchingNonExistingVolunteer(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //Search for an non existing volunteers
        p4gVolunteers.searchAnItem(driver, data.get("searchKeyword"));

        //Verify the search result
        p4gVolunteers.verifySearchResultForNonExistingItem(data.get("expectedResult"));
        System.out.println(data.get("expectedResult"));

        //Log out
        p4gPage.signOut();
    }



    //TC004-Verify user can sort the result table by # column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanSortVolunteersResultTableByIdColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //Verify the descending result
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "#", "desc", data.get("pageName"));

        //Sort the table by Id in asc order and verify the result
        p4gVolunteers.clickOnColumnToSort("#");
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "#", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify user can sort the result table by Name column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanSortVolunteersResultTableByNameColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //Verify the descending result
        p4gVolunteers.clickOnColumnToSort("Name");
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "Name", "desc", data.get("pageName"));

        //Sort the table by Name in asc order and verify the result
        p4gVolunteers.clickOnColumnToSort("Name");
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "Name", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC006-Verify user can sort the result table by Email column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanSortVolunteersResultTableByEmailColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //Verify the descending result
        p4gVolunteers.clickOnColumnToSort("Email");
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "Email", "desc", data.get("pageName"));

        //Sort the table by Email in asc order and verify the result
        p4gVolunteers.clickOnColumnToSort("Email");
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "Email", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify user can sort the result table by Status column
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserCanSortVolunteersResultTableByStatusColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //Verify the descending result
        p4gVolunteers.clickOnColumnToSort("Status");
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "Status", "desc", data.get("pageName"));

        //Sort the table by Status in asc order and verify the result
        p4gVolunteers.clickOnColumnToSort("Status");
        p4gVolunteers.verifySortTableWorksCorrectly(driver, "Status", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }
/*
    //TC008-Verify table size is displayed correctly
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyTableSizeIsDisplayedCorrectly(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Customer page
        p4gVolunteers.goToVolunteersPage();

        //Go to Edit page from customer detail page
      //  p4gVolunteers.selectSizePerPageAndVerifyTableSize(driver);

        //Log out
        p4gPage.signOut();
    }

 */
    @Test(priority = 0, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyUserIsAbleToDeleteAnExistingVolunteer(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //delete a volunteer from the table result
        p4gVolunteers.deleteAVolunteer(driver);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_VerifyDefaultOptionAndListOfTablePageSizes(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodVolunteersPage p4gVolunteers = new PantryForGoodVolunteersPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //go to Volunteers page
        p4gVolunteers.goToVolunteersPage();
        sleep(3000);

        //verify default page size and list of page sizes
        p4gVolunteers.verifyListPageSizesAndDefaultOption(driver,data.get("lstPageSizes"));

        //Log out
        p4gPage.signOut();
    }
}
