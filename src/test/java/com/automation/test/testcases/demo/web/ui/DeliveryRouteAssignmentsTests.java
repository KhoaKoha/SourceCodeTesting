package com.automation.test.testcases.demo.web.ui;

import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.web.ui.object.BaseTest;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodDeliveryDriversPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodDeliveryRouteAssignmentsPage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodHomePage;
import com.automation.test.libraries.web.ui.pageobjects.PantryForGoodSignInPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * Created by trangtnnguyen on 3/31/2020.
 */
public class DeliveryRouteAssignmentsTests extends BaseTest {
    private String baseURL;

    public DeliveryRouteAssignmentsTests() {
        baseURL = "http://automationpractice.com/index.php";
    }

    //TC001-Verify user can assign a customer to a driver
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC001_VerifyUserCanAssignACustomerToADriver(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryRouteAssignmentsPage p4gRoute = new PantryForGoodDeliveryRouteAssignmentsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gRoute.goToRouteAssignmentsPage(driver);

        //Assign a customer to a driver and verify the result
        p4gRoute.assignACustomerToADriver();

        //Log out
        p4gPage.signOut();
    }

    //TC002-Verify user can assign all customers to a driver
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC002_VerifyUserCanAssignAllCustomersToADriver(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryRouteAssignmentsPage p4gRoute = new PantryForGoodDeliveryRouteAssignmentsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gRoute.goToRouteAssignmentsPage(driver);

        //Assign all customers to a driver and verify the result
        p4gRoute.assignAllCustomersToADriver();

        //Log out
        p4gPage.signOut();
    }

    //TC003-Unassign a customer to a driver
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC003_VerifyUserCanUnAssignACustomerToADriver(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryRouteAssignmentsPage p4gRoute = new PantryForGoodDeliveryRouteAssignmentsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gRoute.goToRouteAssignmentsPage(driver);

        //Unassign a customer to a driver and verify the result
        p4gRoute.unassignACustomerToADriver(driver);

        //Log out
        p4gPage.signOut();
    }

    //TC004-Unassign all customers of a driver
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC004_VerifyUserCanUnAssignAllCustomersOfADriver(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryRouteAssignmentsPage p4gRoute = new PantryForGoodDeliveryRouteAssignmentsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gRoute.goToRouteAssignmentsPage(driver);

        //Unassign a customer to a driver and verify the result
        p4gRoute.unassignAllCustomersOfADriver();

        //Log out
        p4gPage.signOut();
    }

    //TC005-Verify number of drivers on Drivers table is equal to Customers Assigned To dropdown
    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void TC005_VerifyNumberOfDrivers(HashMap<String, String> data) {
        PantryForGoodHomePage p4gPage = new PantryForGoodHomePage(driver);
        PantryForGoodSignInPage p4gSignIn = new PantryForGoodSignInPage(driver);
        PantryForGoodDeliveryRouteAssignmentsPage p4gRoute = new PantryForGoodDeliveryRouteAssignmentsPage(driver);

        //Navigate to URL
        p4gSignIn.navigateToURL(baseURL);

        //Sign In
        p4gSignIn.logInToHomePage(data.get("username"), data.get("password"));

        //Go to Drivers page
        p4gRoute.goToRouteAssignmentsPage(driver);

        //Compare number of drivers on Route Assignment table and dropdown
        p4gRoute.verifyNumberOfDrivers();

        //Log out
        p4gPage.signOut();
    }

}
