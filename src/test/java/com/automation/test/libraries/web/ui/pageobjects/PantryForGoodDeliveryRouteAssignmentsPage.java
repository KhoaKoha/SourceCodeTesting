package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trangtnnguyen on 3/16/2020.
 */
public class PantryForGoodDeliveryRouteAssignmentsPage extends BasePage {
    private String btnAssignSelected = "//button[contains(text(),'Assign Selected')]";
    //private String btnAssignSelected = btnSuccess;

    private String tblDrivers = "//div[@class='list-group']//button";
    private String drpCustomersAssignedTo = "//select[@class='form-control']";
    private String tblCustomers = "//table[@class='table table-striped']//tbody//tr";
    private String lblAssignedCount = "//span[@class='list-group-item']//span";

    public PantryForGoodDeliveryRouteAssignmentsPage(WebDriver driver) {
        super(driver);
    }

    public void goToRouteAssignmentsPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/drivers/routes");
        waitForElementInSeconds(5);
    }

    public void assignACustomerToADriver() {
        //Get first driver's number of assigned customers
        int numCustomersBeforeAssignment = getNumOfCustomersBeforeAction();

        //Click on last driver
        getElement(tblDrivers + "[1]").click();

        //Assign customer
        WebElement custAssignedToDrp = getWebElement(drpCustomersAssignedTo);
        Select s = new Select(custAssignedToDrp);
        s.selectByVisibleText("Unassigned Customers");

        if (getElement(tblCustomers + "//td").getText().equalsIgnoreCase("No matching customers.")) {
            s.selectByVisibleText("All Customers");
            int custRowCount = getElementsCount(tblCustomers);
            System.out.println("custRowCount = "+custRowCount);

            for (int i = 1; i < custRowCount; i++) {
                WebElement cust = getWebElement(tblCustomers + "[" + i + "]//input");
                if (cust.isSelected()==true) {
                    continue;
                } else {
                    getElement(tblCustomers + "[" + i + "]").click();
                    break;
                }
            }
        } else {
            getElement(tblCustomers + "[1]").click();
        }

        getElement(btnAssignSelected).click();
        waitForElementInSeconds(3);

        //Get first driver's number of assigned customers
        int numCustomersAfterAssignment = getNumOfCustomersAfterAction();
        Assert.assertEquals(numCustomersAfterAssignment, numCustomersBeforeAssignment + 1, "Customer is not assigned correctly.");
    }

    public void assignAllCustomersToADriver() {
        waitForElementInSeconds(3);
        //Get first driver's number of assigned customers
        int numCustomersBeforeAssignment = getNumOfCustomersBeforeAction();
        System.out.println("numCustomersBeforeAssignment "+numCustomersBeforeAssignment);

        //Click on first driver
        getElement(tblDrivers + "[1]").click();

        //Assign all customers
        WebElement custAssignedToDrp = getWebElement(drpCustomersAssignedTo);
        Select s = new Select(custAssignedToDrp);
        s.selectByVisibleText("Unassigned Customers");

        int numOfUnassignedCustomers;
        int numCustomersAfterAssignment;
        int numOfNewAssignedCustomers = 0;

        if (getElement(tblCustomers + "//td").getText().equalsIgnoreCase("No matching customers.")) {
            s.selectByVisibleText("All Customers");
            int custRowCount = getElementsCount(tblCustomers);
            System.out.println("custRowCount = "+custRowCount);

            for (int i = 1; i <= custRowCount; i++) {
                WebElement cust = getWebElement(tblCustomers + "[" + i + "]//input");
                if (cust.isSelected()==true) {
                    continue;
                } else {
                    getElement(tblCustomers + "[" + i + "]").click();
                    numOfNewAssignedCustomers++;
                }
            }
            System.out.println("numOfNewAssignedCustomers = " + numOfNewAssignedCustomers);
            numCustomersAfterAssignment = numCustomersBeforeAssignment + numOfNewAssignedCustomers;
        } else {
            numOfUnassignedCustomers = getElementsCount(tblCustomers);
            numCustomersAfterAssignment = numCustomersBeforeAssignment + numOfUnassignedCustomers;
            System.out.println("numOfUnassignedCustomers = " + numOfUnassignedCustomers);
            getElement("//table[@class='table table-striped']//thead//tr//th//span").click();
        }

        getElement(btnAssignSelected).click();

        //Get first driver's number of assigned customers
        waitForElementInSeconds(3);
        int expNumCustomersAfterAssignment = getNumOfCustomersAfterAction();
        System.out.println("expNumCustomersAfterAssignment" + expNumCustomersAfterAssignment);
        Assert.assertEquals(numCustomersAfterAssignment,expNumCustomersAfterAssignment , "Customer is not assigned correctly.");

    }

    public void unassignACustomerToADriver(WebDriver driver){
        //Get first driver's number of assigned customers
        int numCustomersBeforeUnassignment = getNumOfCustomersBeforeAction();
        if(numCustomersBeforeUnassignment==0){
            assignACustomerToADriver();
            waitForElementInSeconds(2);
            WebElement assignedCountElement = getWebElement(lblAssignedCount);
            numCustomersBeforeUnassignment = Integer.parseInt(assignedCountElement.getText());
            System.out.println("numCustomersBeforeUnassignment: " + numCustomersBeforeUnassignment);
        }else{
            //Click on first driver
            getElement(tblDrivers + "[1]").click();
        }

        //Unassign customer
        getElement(tblCustomers + "[1]").click();

        getElement(btnAssignSelected).click();
        waitForElementInSeconds(3);

        //Get number of assigned customer after unassignment
        int numCustomersAfterUnassignment = getNumOfCustomersAfterAction();
        System.out.println(numCustomersAfterUnassignment + "   " + (numCustomersBeforeUnassignment-1));
        Assert.assertEquals(numCustomersAfterUnassignment, numCustomersBeforeUnassignment - 1, "Customer is not assigned correctly.");

    }

    public void unassignAllCustomersOfADriver(){
        //Get first driver's number of assigned customers
        int numCustomersBeforeUnassignment = getNumOfCustomersBeforeAction();

        if(numCustomersBeforeUnassignment==0){
            assignACustomerToADriver();
            WebElement assignedCountElement = getWebElement(lblAssignedCount);
            numCustomersBeforeUnassignment = Integer.parseInt(assignedCountElement.getText());
            System.out.println("numCustomersBeforeUnassignment: " + numCustomersBeforeUnassignment);
        }else{
            //Click on first driver
            getElement(tblDrivers + "[1]").click();
        }

        //Unassign all customers
        int numOfCustomersToUnassigned = getElementsCount(tblCustomers);
        getElement("//table[@class='table table-striped']//thead//tr//th//span").click();

        getElement(btnAssignSelected).click();
        waitForElementInSeconds(3);

        //Get number of assigned customer after unassignment
        int numCustomersAfterUnassignment = getNumOfCustomersAfterAction();
        System.out.println(numCustomersAfterUnassignment + "   " + (numCustomersBeforeUnassignment-numOfCustomersToUnassigned));
        Assert.assertEquals(numCustomersAfterUnassignment, numCustomersBeforeUnassignment-numOfCustomersToUnassigned , "Customer is not assigned correctly.");

    }


    public void verifyNumberOfDrivers(){
        //Get number of drivers on table
        int numOfDriversOnTable = getElementsCount(tblDrivers);
        ArrayList<String> driversListOnTable = new ArrayList<String>();
        for(int i=1;i<=numOfDriversOnTable;i++){
            driversListOnTable.add(getElement("//button["+ i + "]//div//img//following-sibling::div[1]").getText());
        }
        System.out.println(driversListOnTable);

        //Get number of drivers on dropdown
        String driversOnDrpXpath = drpCustomersAssignedTo+"//optgroup[@label='Assigned Driver:']//option";
        int numOfDriversOnDrp = getElementsCount(driversOnDrpXpath);
        ArrayList<String> driversListOnDrp = new ArrayList<String>();
        for(int i=1;i<=numOfDriversOnDrp;i++){
            driversListOnDrp.add(getElement(driversOnDrpXpath+"["+i+"]").getText());
        }
        System.out.println(driversListOnDrp);

        Assert.assertEquals(driversListOnTable,driversListOnDrp,"Number of drivers are not equal.");
    }

    public int getNumOfCustomersBeforeAction(){
        //Get first driver's number of assigned customers
        WebElement assignedCount = getWebElement(tblDrivers + "[1]//span");
        int numCustomersBeforeAction = Integer.parseInt(assignedCount.getText());

        return  numCustomersBeforeAction;
    }

    public int getNumOfCustomersAfterAction(){
        //Get first driver's number of assigned customers
        WebElement assignedCountAfterAssignment = getWebElement(lblAssignedCount);
        int numCustomersAfterAction = Integer.parseInt(assignedCountAfterAssignment.getText());

        return  numCustomersAfterAction;
    }

}
