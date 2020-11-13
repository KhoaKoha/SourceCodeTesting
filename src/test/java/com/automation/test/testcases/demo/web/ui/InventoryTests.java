package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodCustomersPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodInventoryPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodUserAccounts;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by thuyluong on 3/19/2020.
 */
public class InventoryTests extends BaseTest {
    private String baseURL;

    public InventoryTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can add new inventory
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanAddANewInventory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        // Add to inventory and verify the result
        p4gInventory.addToInventory(driver, data.get("foodName"), data.get("category"), data.get("quantity"), data.get("keyword"));
        //Log out
        p4gPage.signOut();
    }

    //TC002-Verify user can edit an item in inventory
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanEditAnItemInInventory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        // Edit an item in inventory and verify the result
        p4gInventory.editInventory(driver, data.get("foodNameForEdit"), data.get("categoryForEdit"),
                data.get("quantityForEdit"), data.get("keywordForEdit"));
        //Log out
        p4gPage.signOut();
    }

    //TC003-Verify user can delete an item from inventory
    @Test(priority = 16, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanDeleteFoodFromInventory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Delete food
        p4gInventory.deleteFoodFromInventory(driver);
        //Log out
        p4gPage.signOut();
    }

    //TC004-Verify user can search for an existing inventory
    @Test(priority = 1, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanSearchExistingInventory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Search for an existing inventory
        p4gInventory.inputSearchTextAndSubmitInventory(data.get("search_text"), data.get("expected_text"));
        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify user can search for a non existing inventory
    @Test(priority = 2, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanSearchNonExistingInventory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Search for a non existing inventory

        //Search for an non existing customer
        p4gInventory.searchAnItem(driver, data.get("searchtext"));

        //Verify the search result
        p4gInventory.verifySearchResultForNonExistingItem(data.get("expectedResult"));

        //Log out
        p4gPage.signOut();
    }

    //TC006-Verify user can sort the result table by Name column
    @Test(priority = 3, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanSortResultTableByNameColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();

        //Sort the table by Name in descending order and verify the result
        p4gInventory.clickOnColumnToSort("Name");
        p4gInventory.verifySortTableWorksCorrectly(driver, "Name", "desc", data.get("pageName"));

        //Sort the table by Name in ascending order and verify the result
        p4gInventory.clickOnColumnToSort("Name");
        p4gInventory.verifySortTableWorksCorrectly(driver, "Name", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC007-Verify user can sort the result table by Category column
    @Test(priority = 4, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserCanSortResultTableByCategoryColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();

        //Sort the table by Name in ascending order and verify the result
        p4gInventory.clickOnColumnToSort("Category");
        p4gInventory.verifySortTableWorksCorrectly(driver, "Category", "asc", data.get("pageName"));

        //Sort the table by Name in descending order and verify the result
        p4gInventory.clickOnColumnToSort("Category");
        p4gInventory.verifySortTableWorksCorrectly(driver, "Category", "desc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    //TC008-Verify user can sort the result table by Qty column
    @Test(priority = 5, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyUserCanSortResultTableByQtyColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Sort the table by Name in descending order and verify the result
        p4gInventory.clickOnColumnToSort("Qty");
        p4gInventory.verifySortTableWorksCorrectly(driver, "Qty", "desc", data.get("pageName"));
        //Sort the table by Name in ascending order and verify the result
        p4gInventory.clickOnColumnToSort("Qty");
        p4gInventory.verifySortTableWorksCorrectly(driver, "Qty", "asc", data.get("pageName"));
        //Log out
        p4gPage.signOut();
    }

    //TC009-Verify table size is displayed correctly
    @Test(priority = 6, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyTableSizeIsDisplayedCorrectly(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Go to Edit page from customer detail page
        p4gInventory.selectSizePerPageAndVerifyTableSize(driver);
        //Log out
        p4gPage.signOut();
    }

    //TC010-Verify user can search for an existing inventory by using dynamic data
    @Test(priority = 7, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_VerifyUserCanSearchAnInventory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Inventory page
        p4gInventory.goToInventoryPage();

        //Search for an existing inventory by using dynamic data in Inventory list
        p4gInventory.searchAnItem(driver, null);

        //Log out
        p4gPage.signOut();
    }

    //TC011-Verify user can add new category
    @Test(priority = 8, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC011_VerifyUserCanAddNewCategory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Add a category
        p4gInventory.addCategory(data.get("addcategoryname"));
        //Log out
        p4gPage.signOut();
    }

    //TC012-Verify user cannot add duplicated category
    @Test(priority = 9, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC012_VerifyUserCannotAddDuplicatedCategories(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Verify the message when adding duplicated category
        p4gInventory.addDuplicatedCategory(data.get("duplicatecategoryname"), data.get("expectedResult"));
        //Log out
        p4gPage.signOut();
    }

    //TC013-Verify user can delete a category
    @Test(priority = 10, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC013_VerifyUserCanDeleteCategory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Delete a category
        p4gInventory.deleteCategory(data.get("deletecategoryname"));
        //Log out
        p4gPage.signOut();
    }

    //TC014-Verify user cannot delete an assigned category
    @Test(priority = 11, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC014_VerifyUserCannotDeleteAssignedCategory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Verify a message displays when deleting an assigned category
        p4gInventory.deleteAssignedCategory(data.get("deleteassignedcategoryname"), data.get("expectedresult"));
        //Log out
        p4gPage.signOut();
    }

    //TC015-Verify user can edit category
    @Test(priority = 12, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC015_VerifyUserCanEditCategory(HashMap<String, String> data) throws InterruptedException {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Edit a category
        p4gInventory.editCategory(data.get("editcategoryname"), data.get("updatedcategoryname"));
        //Log out
        p4gPage.signOut();
    }

    //TC016-Verify user cancel deleting an item from inventory
    @Test(priority = 13, dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC016_VerifyUserCancelDeletingFoodFromInventory(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);
        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);
        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);
        //Go to Inventory page
        p4gInventory.goToInventoryPage();
        //Cancel deleting food from inventory
        p4gInventory.cancelDeletingFoodFromInventory(driver);
        //Log out
        p4gPage.signOut();
    }

    //TC017-Verify list of page sizes and default page size option
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC017_VerifyDefaultOptionAndListOfTablePageSizes(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodInventoryPage p4gInventory = new PantryForGoodInventoryPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));
        sleep(3000);

        //Go to Inventory page
        p4gInventory.goToInventoryPage();

        //verify default page size and list of page sizes
        p4gInventory.verifyListPageSizesAndDefaultOption(driver, data.get("lstPageSizes"));

        //Log out
        p4gPage.signOut();
    }

}