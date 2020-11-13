package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.WebObject;
import com.automation.test.libraries.web.ui.pageobjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class PackingListTests extends BaseTest {

    /**
     * Created by thuyluong on 3/26/2020.
     */

    private String baseURL;

    public PackingListTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can search for a customer in the packing list
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanSearchExistingCustomerInPackingList(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Packing List page
        p4gPackingList.goToPackingListPage();

        //Search for customer in packing list
        p4gPackingList.searchAnItem(driver, null);

        //Log out
        p4gPage.signOut();
    }

    //TC002-Verify user can search for a non existing customer in the packing list
    @Test(priority = 2, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanSearchNonExistingCustomerInPackingList(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Packing List page
        p4gPackingList.goToPackingListPage();

        //Search for customer in packing list
        p4gPackingList.searchAnItem(driver, data.get("searchKeyword"));

        //Search for a non existing customer in packing list
        p4gPackingList.verifySearchResultForNonExistingItem(data.get("expectedResult"));

        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify user can pack preferred items for a customer
    @Test(priority = 2, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanPackPreferredItems(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Packing List page
        p4gPackingList.goToPackingListPage();

        //Pack preferred items for selected customer
        p4gPackingList.PackPreferredItems(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC004-Verify user can pack preferred items for multiple customers
    @Test(priority = 3, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanPackPreferredItemsForMultipleCustomers(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Packing List page
        p4gPackingList.goToPackingListPage();
        sleep(3000);

        //Pack packages for multiple customers
        p4gPackingList.packedPackageForMultipleCustomer(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify user can unpack preferred items
    @Test(priority = 4, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanUnpackPreferredItems(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Packing List page
        p4gPackingList.goToPackingListPage();
        sleep(3000);

        //Pack preferred items for selected customer
        p4gPackingList.PackPreferredItems(driver);

        //Unpack preferred items for selected customer
        p4gPackingList.UnPack(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC006-Verify user can sort the result table by # column
    @Test(priority = 5, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanSortResultTableByIDColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Packing List page
        p4gPackingList.goToPackingListPage();
        sleep(3000);
        //Sort the table by ID in ascending order and verify the result
        p4gPackingList.clickOnColumnToSort("#");
        p4gPackingList.verifySortTableWorksCorrectlyPackingList(driver, "#", "asc", data.get("pageName"));
        //Sort the table by ID in descending order and verify the result
        p4gPackingList.clickOnColumnToSort("#");
        p4gPackingList.verifySortTableWorksCorrectlyPackingList(driver, "#", "desc", data.get("pageName"));
        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify list of page sizes and default page size option
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyDefaultOptionAndListOfTablePageSizes(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Packing List page
        p4gPackingList.goToPackingListPage();
        sleep(3000);
        //verify default page size and list of page sizes
        p4gPackingList.verifyListPageSizesAndDefaultOptionPackingList(driver, data.get("lstPageSizes"));
        //Log out
        p4gPage.signOut();
    }

    //TC008-Verify Unpack and  Delivered buttons are enable when package status is Packed
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyUnpackAndDeliveredButtonsAreClickableWhenPackageStatusIsPacked(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        //Go to Packing List page
        p4gPackingList.goToPackingListPage();
        //Pack a package and verify Unpack and Delivered button are enable
        p4gPackingList.verifyUnpackAndDeliveredButtonsAreEnableWhenPackageStatusIsPacked(driver, data.get("expectedStatusAfterPacking"));
        //Log out
        p4gPage.signOut();
    }

    //TC009-Verify Unpack and  Delivered buttons are disable when package status is Delivered.
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyUnpackAndDeliveredButtonsAreUnClickableWhenPackageStatusIsDelivered(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Packing List page
        p4gPackingList.goToPackingListPage();
        sleep(3000);
        //Delivered a package and verify Unpack and Delivered button are disable
        p4gPackingList.verifyUnpackAndDeliveredButtonsAreDisableWhenPackageStatusIsDelivered(driver, data.get("expectedStatusAfterDelivered"));
        //Log out
        p4gPage.signOut();
    }

    //TC010-Verify table size is correct
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_selectSizePerPageAndVerifyTableSize(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodPackingListPage p4gPackingList = new PantryForGoodPackingListPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Packing List page
        p4gPackingList.goToPackingListPage();
        //Search for customer in packing list
        p4gPackingList.selectSizePerPageAndVerifyTableSize(driver);
        //Log out
        p4gPage.signOut();
    }
}
