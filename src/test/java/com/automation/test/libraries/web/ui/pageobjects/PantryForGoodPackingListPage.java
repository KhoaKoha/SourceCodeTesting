package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import org.apache.commons.lang3.SerializationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Collections;

public class PantryForGoodPackingListPage extends BasePage {

    private String lblPackingListMenu = "//a/span[text()='Packing List']";

    private String txtSearch = "//input[@placeholder='Search']";

    private String tblSearchResult = "//button[contains(text(),'Mark')]//preceding::tr[last()-1]//td[3]";

    private String btnPack = "//tr[1]/td[7]//button";

    private String btnPackSelected = "//div[@class='modal-content']//div//button[text()='Pack Selected']";

    private String btnCancel = "//div[@class='modal-content']//div//button[text()='Cancel']";

    private String tblPackedPackagesList = "//button[contains(text(),'Mark')]//following::tr[2]//td[3]//div";

    private String btnMarkedSelectedCustomersPacked = "//button[@class='btn btn-success']";

    public String btnUnpack = "//table/tbody/tr[1]/td[8]/div/button[1]";

    private String btnMarkCustomers = "//button[contains(text(),'Mark')]";

    private String tblPackingList = btnMarkCustomers + "//preceding::div[@class='react-bs-container-body']";

    private String tblPackedPackage = btnMarkCustomers + "//following::div[@class='react-bs-container-body']";

    public String btnDelivered = "//button[contains(text(),'Mark')]//following::tr[2]//td[8]//button[2]";

    public String lbDelivered = "//button[contains(text(),'Mark')]//following::tr[2]//td[8]//button[2]";

    private String chkPreferredItem = "//div[@class='well']/div[1]/label";

    private String chkOne = "//button[contains(text(),'Mark')]//preceding::tr[last()-1]//td[1]//span";

    private String chkTwo = "//button[contains(text(),'Mark')]//preceding::tr[last()-2]//td[1]//span";


    public PantryForGoodPackingListPage(WebDriver driver) {
        super(driver);
    }

    public void goToPackingListPage() {
        getElement(lblPackingListMenu).click();
        waitForElementInSeconds(3);
    }

       public void PackPreferredItems(WebDriver driver) {
        getElement(btnPack).click();
        selectCheckbox(driver);
        getElement(btnPackSelected).click();
        PackedPackagesList(driver);
    }

    public void selectCheckbox(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//div[@class='well']/div[@class='checkbox'][position()=1]/label"));
        Actions action = new Actions(driver);
        action.moveToElement(element).click(element).build().perform();
    }

    public void PackedPackagesList(WebDriver driver) {
        String customernameinpackagelist = getElement(tblPackedPackagesList).getText();
        System.out.println("Packed item of customer :" + customernameinpackagelist);
    }

    public void packedPackageForMultipleCustomer(WebDriver driver) {
        getElement(chkOne).click();
        getElement(chkTwo).click();
        getElement(btnMarkedSelectedCustomersPacked).click();
        PackedMultipleCustomerInPackagesList(driver);
    }

    public void PackedMultipleCustomerInPackagesList(WebDriver driver) {
        WebElement customer1 = driver.findElement(By.xpath("//button[contains(text(),'Mark')]//following::tr[2]//td[3]//div"));
        WebElement customer2 = driver.findElement(By.xpath("//button[contains(text(),'Mark')]//following::tr[3]//td[3]//div"));
        Assert.assertEquals(true, customer1.isDisplayed());
        String customerName1 = customer1.getText();
        System.out.println(customerName1);

        Assert.assertEquals(true, customer2.isDisplayed());
        String customerName2 = customer2.getText();
        System.out.println(customerName2);
    }

    public void UnPack(WebDriver driver) {
        getElement(btnUnpack).click();
        PackingList(driver);
    }

    public void PackingList(WebDriver driver) {
        String customerInPackingList = driver.findElement(By.xpath(tblPackingList)).getText();
        System.out.println("Unpack preferred item of customer: " + customerInPackingList);
    }

    public WebObject getLblDefaultPageSizePackingList() {
        String lblDefaultPageSize = "//button[contains(text(),'Mark')]//preceding::button[@id='pageDropDown']";
        return findWebElement(lblDefaultPageSize);
    }

    public void verifyListPageSizesAndDefaultOptionPackingList(WebDriver driver, String listPageSizes) {
        String lstPageSizes, actualSize, expectedSize;
        lstPageSizes = "//button[contains(text(),'Mark')]//preceding::a[@role='menuitem']";
        List<WebElement> ddlPageSizes = driver.findElements(By.xpath(lstPageSizes));
        String[] pageSizes = listPageSizes.split(",");
        String defaultPageSize = getLblDefaultPageSizePackingList().getText();

        if (ddlPageSizes.size() > 0 && ddlPageSizes.size() == pageSizes.length) {
            if (defaultPageSize.equals(pageSizes[0])) {
                Assert.assertTrue(true);
                System.out.println("Default page size is 10");
            } else {
                Assert.assertTrue(false, "Default page size is not equals 10");
            }

            getLblDefaultPageSizePackingList().click();

            System.out.print("List of Actual Page Sizes: ");

            for (int i = 0; i < ddlPageSizes.size(); i++) {
                actualSize = ddlPageSizes.get(i).getText();
                expectedSize = pageSizes[i];

                Assert.assertEquals(actualSize, expectedSize, "List of Page Sizes is not correct.");
                System.out.print(actualSize + " ");
            }
        } else {
            Assert.assertTrue(false, "List of Page Sizes is not equals 4 options.");
        }
    }

    //Methods to sort table by columns
    public void clickOnColumnToSort(String columnToSort) {
        String headerXpath = "//table//th[@title='";
        getElement(headerXpath + columnToSort + "']").click();
    }

    public void verifySortTableWorksCorrectlyPackingList(WebDriver driver, String columnToSort, String
            sortOrder, String pageName) {
        boolean isTheSortCorrect = false;
        ArrayList<String> result = getActualResult(driver, columnToSort, pageName);

        int resultSize = result.size();

        for (int i = 0; i < resultSize; i++) {
            for (int j = i + 1; j < resultSize; j++) {
                if (sortOrder.equalsIgnoreCase("asc")) {
                    if (columnToSort.equalsIgnoreCase("email")) {
                        if ((result.get(i).split("@")[0].compareTo(result.get(j).split("@")[0])) <= 0) {
                            isTheSortCorrect = true;
                        } else {
                            isTheSortCorrect = false;
                            break;
                        }
                    } else {
                        if ((result.get(i).compareTo(result.get(j))) <= 0) {
                            isTheSortCorrect = true;
                            System.out.println("result.get(" + i + ").compareTo(result.get(" + j + ")) = " + result.get(i).compareTo(result.get(j)));
                        } else {
                            isTheSortCorrect = false;
                            break;
                        }
                    }
                } else if (sortOrder.equalsIgnoreCase("desc")) {
                    if (columnToSort.equalsIgnoreCase("email")) {
                        if ((result.get(i).split("@")[0].compareTo(result.get(j).split("@")[0])) >= 0) {
                            isTheSortCorrect = true;
                        } else {
                            isTheSortCorrect = false;
                            break;
                        }
                    } else {
                        if ((result.get(i).compareTo(result.get(j))) >= 0) {
                            isTheSortCorrect = true;
                            System.out.println("result.get(" + i + ").compareTo(result.get(" + j + ")) = " + result.get(i).compareTo(result.get(j)));
                        } else {
                            isTheSortCorrect = false;
                            break;
                        }
                    }
                }
            }
            System.out.println(isTheSortCorrect);
            Assert.assertTrue(isTheSortCorrect, "Sort is not correct.");
        }
    }

    public ArrayList<String> getActualResult(WebDriver driver, String columnToSort, String pageName) {
        String tblSearchResult = "//button[contains(text(),'Mark')]//preceding::div[@class='react-bs-container-body']//table";
        int rowCount = getElementsCount(tblSearchResult + "//tr");
        ArrayList<String> actualResult = new ArrayList<String>();

        for (int i = 1; i <= rowCount; i++) {
            switch (columnToSort) {
                case "#":
                    switch (pageName) {
                        case "Customers":
                        case "Packing List":
                            actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[2]")).getText());
                            break;
                    }
                    break;
                case "Name":
                    switch (pageName) {
                        case "Inventory":
                            actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[1]")).getText());
                            break;
                        case "Customers":
                            actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[2]")).getText());
                            break;
                    }
                    break;
                case "First Name":
                case "Date":
                    actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[2]").getText());
                    break;
                case "Last Name":
                case "Message":
                    actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[3]").getText());
                    break;
                case "Url":
                    actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[4]").getText());
                    break;
                case "Email":
                    switch (pageName) {
                        case "Customers":
                        case "User Accounts":
                        case "Volunteers":
                            actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[4]").getText());
                            break;
                        case "Donors":
                            actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[5]").getText());
                            break;
                    }
                    break;
                case "Status":
                    switch (pageName) {
                        case "Customers":
                            actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[7]").getText());
                            break;
                        case "Volunteers":
                            actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[5]").getText());
                            break;
                    }
                    break;

                case "Category":
                    switch (pageName) {
                        case "Inventory":
                            actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[2]")).getText());
                            break;
                    }
                    break;

                case "Qty":
                    switch (pageName) {
                        case "Inventory":
                            actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[3]")).getText());
                            break;
                    }
            }
        }
        System.out.println("Actual Result: " + actualResult);
        return actualResult;
    }

    //Methods to sort table by columns
    public void clickOnColumnToSort(WebDriver driver, String columnToSort) {
        String headerXpath = "//table//th[@title='";
        driver.findElement(By.xpath(headerXpath + columnToSort + "']")).click();
    }

    public void verifyUnpackAndDeliveredButtonsAreEnableWhenPackageStatusIsPacked(WebDriver driver, String
            expectedStatus) {
        String idOfCustomerInPackingList, idOfCustomerInPackedPackages;
        if (getElementsCount(tblPackingList + "//tr") > 0) {
            idOfCustomerInPackingList = getElement(tblPackingList + "//tr[1]//td[2]").getText();
            getElement(btnPack).click();
            waitForElementInSeconds(2);
            selectCheckbox(driver);
            getElement(btnPackSelected).click();
            waitForElementInSeconds(3);

            //get Id of customer in Packed Package table
            idOfCustomerInPackedPackages = getElement(tblPackedPackage + "//tr[1]//td[2]").getText();
            Assert.assertEquals(idOfCustomerInPackedPackages, idOfCustomerInPackingList, "Packing is not correct.");

            //Verify Status = packed
            String actualStatus = getElement(tblPackedPackage + "//tr[1]//td[7]").getText();
            System.out.println(actualStatus);
            Assert.assertEquals(actualStatus, expectedStatus, "Packing is not correct.");

            //Verify Unpack and Delivered buttons are clickable when package status is Packed
            boolean isUnpackButtonEnable = Boolean.parseBoolean(getElement(btnUnpack).getAttribute("disabled"));
            boolean isDeliveredButtonEnable = Boolean.parseBoolean(getElement(btnDelivered).getAttribute("disabled"));
            Assert.assertEquals(isUnpackButtonEnable, false, "Unpack button is disable.");
            Assert.assertEquals(isDeliveredButtonEnable, false, "Delivered button is disable.");
        }


    }

    public void verifyUnpackAndDeliveredButtonsAreDisableWhenPackageStatusIsDelivered(WebDriver driver, String
            expectedStatus) {
        if (getElementsCount(tblPackingList + "//tr") > 0) {
            getElement(btnPack).click();
            waitForElementInSeconds(2);
            selectCheckbox(driver);
            getElement(btnPackSelected).click();
            waitForElementInSeconds(3);
            getElement(btnDelivered).click();

            //Verify Status = Delivered
            String actualStatus = getElement(tblPackedPackage + "//tr[1]//td[7]").getText();
            System.out.println(actualStatus);
            Assert.assertEquals(actualStatus, expectedStatus, "Packing is not correct.");

            //Verify Unpack and Delivered buttons are unclickable when package status is Delivered
            boolean isUnpackButtonEnable = Boolean.parseBoolean(getElement(btnUnpack).getAttribute("disabled"));
            boolean isDeliveredButtonEnable = Boolean.parseBoolean(getElement(btnDelivered).getAttribute("disabled"));
            Assert.assertEquals(isUnpackButtonEnable, true, "Unpack button is disable.");
            Assert.assertEquals(isDeliveredButtonEnable, true, "Delivered button is disable.");
        }


    }
}