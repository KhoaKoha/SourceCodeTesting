package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodAdminUserNotificationsPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignUpPage;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AdminNotificationsTests extends BaseTest {
    private String baseURL;

    public AdminNotificationsTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001 - Verify user can search for an existing Notifications
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanSearchForAnExistingNotifications(HashMap<String, String> data) {
        PantryForGoodSignUpPage p4gSignUp = new PantryForGoodSignUpPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);

        p4gSignUp.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));

        //create data for Notifications table
        if (p4gNotifications.countRowsOnNotificationsTable(driver).size() < 2) {
            p4gNotifications.createDataForNotificationsTable(driver, data.get("fName"), data.get("lName"), data.get("email"), data.get("password"),
                    data.get("street"), data.get("townCity"), data.get("state"), data.get("howHeard"));
            //Sign In
            p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        }

        //go to Admin Notifications page
        p4gNotifications.goToNotificationsPage(driver);

        //search an existing Notification
        p4gNotifications.searchAnItem(driver, null);

        //Sign out
        p4gPage.signOut();
        sleep(3000);
    }

    //TC002 - Verify no data on table when user search with non existing Notification
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyNoDataOnTableWithSearchingNonExistingNotification(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //Search for an non existing Notifications
        p4gNotifications.searchAnItem(driver, data.get("searchKeyword"));

        //Verify the search result
        p4gNotifications.verifySearchResultForNonExistingItem(data.get("expectedResult"));
        System.out.println(data.get("expectedResult"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanSortNotificationsResultTableByIdColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //Verify the descending result
        p4gNotifications.verifySortTableWorksCorrectly(driver, "#", "desc", data.get("pageName"));

        //Sort the table by Id in asc order and verify the result
        p4gNotifications.clickOnColumnToSort("#");
        p4gNotifications.verifySortTableWorksCorrectly(driver, "#", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanSortNotificationsResultTableByDateColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //Verify the descending result
        p4gNotifications.clickOnColumnToSort("Date");
        p4gNotifications.verifySortTableWorksCorrectly(driver, "Date", "desc", data.get("pageName"));

        //Sort the table by Date in asc order and verify the result
        p4gNotifications.clickOnColumnToSort("Date");
        p4gNotifications.verifySortTableWorksCorrectly(driver, "Date", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyUserCanSortNotificationsResultTableByMessageColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //Verify the descending result
        p4gNotifications.clickOnColumnToSort("Message");
        p4gNotifications.verifySortTableWorksCorrectly(driver, "Message", "desc", data.get("pageName"));

        //Sort the table by Message in asc order and verify the result
        p4gNotifications.clickOnColumnToSort("Message");
        p4gNotifications.verifySortTableWorksCorrectly(driver, "Message", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC006_VerifyUserCanSortNotificationsResultTableByUrlColumn(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //Verify the descending result
        p4gNotifications.clickOnColumnToSort("Url");
        p4gNotifications.verifySortTableWorksCorrectly(driver, "Url", "desc", data.get("pageName"));

        //Sort the table by Url in asc order and verify the result
        p4gNotifications.clickOnColumnToSort("Url");
        p4gNotifications.verifySortTableWorksCorrectly(driver, "Url", "asc", data.get("pageName"));

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC007_VerifyUserIsAbleToDeleteAllNotifications(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //delete all Notifications by clicking on Delete All button
        p4gNotifications.deleteAllNotifications(driver);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC008_VerifyUserIsAbleToDeleteASingleNotification(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //delete a single Notifications by clicking on Delete button on the table result
        p4gNotifications.deleteASingleNotification(driver);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC009_VerifyNumberOnNotificationsNavBarCountedCorrectly(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Verify number on Nav Bar Notifications counted correctly with the Notifications droplist
        p4gNotifications.verifyNumberCountedOnNavBarNotifications(driver);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC010_VerifyUserCanViewCustomerInfoFromNotificationsPage(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //view customer info from Notifications table by clicking on Eye button
        p4gNotifications.viewCustomerInfoFromNotifications(driver);

        //Log out
        p4gPage.signOut();
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC011_VerifyDefaultOptionAndListOfTablePageSizes(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodAdminUserNotificationsPage p4gNotifications = new PantryForGoodAdminUserNotificationsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("admin_username"), data.get("admin_pwd"));
        sleep(3000);

        //Go to Notifications page
        p4gNotifications.goToNotificationsPage(driver);
        sleep(3000);

        //verify default page size and list of page sizes
        p4gNotifications.verifyListPageSizesAndDefaultOption(driver, data.get("lstPageSizes"));

        //Log out
        p4gPage.signOut();
    }
}
