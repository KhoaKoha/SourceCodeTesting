package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.apache.commons.lang3.SerializationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.testng.Assert;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import java.util.ArrayList;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by thuyluong on 3/19/2020.
 */

public class PantryForGoodInventoryPage extends BasePage {
    public PantryForGoodInventoryPage(WebDriver driver) {
        super(driver);
    }

    private String inventoryMenu = "//a[contains(@href,'/inventory')]";

    private String btnAddToInventory = "//button[@class='btn-success btn btn-default']";

    private String txtFoodCategory = "//select[@id='foodCategory']";

    private String txtFoodQuantity = "//input[@id='foodQuantity']";

    private String btnAdd = "//button[text()='Add']";

    private String drpFoodName = "//button[@class='dropdown-toggle btn btn-default']";

    private String btnEditInventory = "//table//tr[1]//td[4]//button//i[@class='fa fa-pencil']";

    private String btnUpdate = "//button[text()='Update']";

    private String txtCategoryName = "//input[@class='form-control']";

    private String btnAddCategory = "//button[@class='btn btn-success btn-flat']";

    private String CategoryMessage = "//div[@class='text-center text-danger']/strong[contains(text(), 'That category already exists')]";

    private String btnDeleteCategory = "//span[contains(text(), 'CategoryDelete')]/parent::td//i[contains(@class,'fa-trash-o')]";

    private String btnEditCategory = "//span[(text()='CategoryABC')]/parent::td//i[contains(@class,'fa-edit')]";

    private String deletespecificcategory = "//span[contains(text(), 'CategoryDelete')]";

    private String editspecificcategory ="//input[@class='form-control'][@value='CategoryABC']";

    private String saveeditcategory ="//i[contains(@class,'fa fa-check')]";

    private String btnDeleteAssignedCategory = "//span[contains(text(), 'Milk')]/parent::td//i[contains(@class,'fa-trash-o')]";

    private String DeleteMessage ="//div[text()='Food category must be empty before deleting']";

    private String tblSearchResult = "//div[@class='react-bs-container-body']//table";

    private String headerXpath = "//table//th[@title='";

    private String btnSizePerPage = "pageDropDown";

    private String btnDeleteFood = "//button[@class='btn btn-xs btn-danger'][1]";

    private String btnDeleteItemConfirmation = "//div[@class='modal-content']//button[@class='btn btn-sm btn-danger']";

    private String btnCancelConfirmation = "//div[@class='modal-content']//button[@class='btn btn-sm btn-primary']";

    private String txtSearch = "//input[@placeholder='Search']";

    public WebObject getInventoryMenu() {
        return findWebElement(inventoryMenu);
    }

    public void goToInventoryPage() {
        getElement(inventoryMenu).click();
        waitForElementInSeconds(3);
    }

    //Method for adding a new category

    public void addCategory(String CategoryName) {
        Random r = new Random();
        CategoryName = "Category" + r.nextInt();
        getElement(txtCategoryName).sendKeys(CategoryName, false);
        getElement(btnAddCategory).click();
        System.out.println("Category name is: " + CategoryName);
    }

    //Method for checking duplicated category

    public void addDuplicatedCategory(String CategoryName, String expectedResult) {
        getElement(txtCategoryName).sendKeys(CategoryName, false);
        getElement(btnAddCategory).click();
        System.out.println("The existing category name is: " + CategoryName);
        getElement(txtCategoryName).sendKeys(CategoryName, false);
        String message = getElement(CategoryMessage).getText();
        Assert.assertEquals(message, expectedResult, "The message is not correct");
    }

    //Method for deleting a category

    public void deleteCategory(String CategoryName) {
        getElement(txtCategoryName).sendKeys(CategoryName, false);
        getElement(btnAddCategory).click();
        System.out.println("Category name is: " + CategoryName);

        String createdcategory = getElement(deletespecificcategory).getText();
        System.out.println("The category is deleted: " + createdcategory);
        getElement(btnDeleteCategory).click();
        Assert.assertEquals(CategoryName, createdcategory);
    }

    //Method for deleting an assigned category
    public void deleteAssignedCategory(String CategoryName, String expectedResultDelete) {

        getElement(btnDeleteAssignedCategory).click();

        String deletemessage = getElement(DeleteMessage).getText();
        Assert.assertEquals(deletemessage, expectedResultDelete, "The message is not correct");
        System.out.println("Actual message: " +deletemessage);
        System.out.println("Expected message: " +expectedResultDelete);
    }

    //Method for editing a category

    public void editCategory(String CategoryName, String UpdatedCategoryName) throws InterruptedException {
        getElement(txtCategoryName).sendKeys(CategoryName, false);
        getElement(btnAddCategory).click();
        System.out.println("Category name is: " + CategoryName);

        getElement(inventoryMenu).click();
        getElement(btnEditCategory).click();

        getElement(editspecificcategory).sendKeys(UpdatedCategoryName,false);
        getElement(saveeditcategory).click();
        System.out.println("New category name is: " + UpdatedCategoryName);

    }

    //Method for searching inventory by using data file

    public void inputSearchTextAndSubmitInventory(String searchText, String expectedsearchText) {
        getElement(txtSearch).sendKeys(searchText, false);
        Assert.assertEquals(searchText,expectedsearchText,"result is not correct");
        System.out.println(searchText);
        System.out.print(expectedsearchText);
    }

    //Method for searching inventory by using dynamic data in the Inventory list
    public void searchAnInventory(WebDriver driver, String searchtext) {
        if (searchtext != null) {
            getTxtSearch().sendKeys(searchtext, false);
        } else {
            searchtext = driver.findElement(By.xpath(tblSearchResult + "//tr[1]/td[1]")).getText();
            String expectedSearchResult = searchtext;

            getTxtSearch().sendKeys(searchtext, false);
            String actualSearchResult = driver.findElement(By.xpath(tblSearchResult + "//tr[1]/td[1]")).getText();

            System.out.println(searchtext);
            System.out.println(expectedSearchResult);
            Assert.assertEquals(actualSearchResult, expectedSearchResult, "The search result is not matched with the keyword");

        }
    }
    // Method for searching a non existing inventory
    public void searchANonExistingInventory(WebDriver driver, String search_text) {
        getTxtSearch().sendKeys(search_text, false);
        System.out.println(search_text);
        WebElement result = driver.findElement(By.xpath("//tr/td[@class='react-bs-table-no-data']"));
        String resultContent = result.getText();
        String expectedResult = "No foods in inventory matching " +search_text;
        Assert.assertEquals(resultContent, expectedResult, "The result is not correct");
        System.out.println(resultContent);
        System.out.println(expectedResult);
    }

    //Method for sorting
    public void verifySortTableWorksCorrectly(WebDriver driver, String columnToSort, String sortOrder, String pageName) {
        boolean isTheSortCorrect = true;
        ArrayList<String> actualResult = getActualResult(driver, columnToSort, pageName);
        ArrayList<String> expectedResult = SerializationUtils.clone(actualResult);

        if (sortOrder.equalsIgnoreCase("desc")) {
            Collections.sort(expectedResult, Collections.reverseOrder());
            System.out.println("Expected result is sorted in DESC: " + expectedResult);

            for (int i = 0; i < actualResult.size(); i++) {
                if (!actualResult.get(i).equals(expectedResult.get(i))) {
                    isTheSortCorrect = false;
                    break;
                }
            }
            Assert.assertTrue(isTheSortCorrect, "The result is NOT sorted in DESC");
        } else if (sortOrder.equalsIgnoreCase("asc")) {
            Collections.sort(expectedResult);
            System.out.println("Expected result is sorted in ASC: " + expectedResult);

            for (int i = 0; i < actualResult.size(); i++) {
                if (!actualResult.get(i).equals(expectedResult.get(i))) {
                    isTheSortCorrect = false;
                    break;
                }
            }
            Assert.assertTrue(isTheSortCorrect, "The result is NOT sorted by ASC");
        }
    }

public ArrayList<String> getActualResult(WebDriver driver, String columnToSort, String pageName) {
    String tblSearchResult = "//div[@class='react-bs-container-body']//table";
    int rowCount = driver.findElements(By.xpath(tblSearchResult + "//tr")).size();
    ArrayList<String> actualResult = new ArrayList<String>();

    for (int i = 1; i <= rowCount; i++) {
        switch (columnToSort) {
            case "#":
                actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[1]")).getText());
                break;
            case "Name":
                switch (pageName)
                {
                    case "Inventory":
                        actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[1]")).getText());
                        break;
                    case "Customers":
                        actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[2]")).getText());
                        break;
                }
                break;

            case "Email":
                switch (pageName){
                    case "Customers":
                    case "User Accounts":
                        actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[4]")).getText());
                        break;
                    case "Donors":
                        actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[5]")).getText());
                        break;
                }
                break;

            case "Status":
                actualResult.add(driver.findElement(By.xpath(tblSearchResult + "//tr[" + i + "]//td[7]")).getText());
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
                break;
        }
    }
    System.out.println("Actual Result: " + actualResult);
    return actualResult;
}

    public void clickOnColumnToSort(WebDriver driver, String columnToSort)
        {
        driver.findElement(By.xpath(headerXpath + columnToSort + "']")).click();
    }

    //Methods for verifying size per page
    public WebObject getBtnSizePerPage() {
        return findWebElement(btnSizePerPage);
    }
/*

>>>>>>> bc435667c09dd45f331e827a2d25bc786cfae495
    //Method for selecting size per page, Each page has 10. 25, 30, 50 items
    public void selectSizePerPageAndVerifyTableSize(WebDriver driver) {
        String optionXpath = "//div[@class='react-bs-table-pagination']//ul[@class='dropdown-menu']//li";
        int sizeOfDropdown = driver.findElements(By.xpath(optionXpath + "//a")).size();

        for (int i = 1; i <= sizeOfDropdown; i++) {
            //Click on size dropdown
            getElement(btnSizePerPage).click();

            //Get size number
            WebElement sizeElement = driver.findElement(By.xpath(optionXpath + "[" + i + "]//a"));
            int sizeNum = Integer.parseInt(sizeElement.getText());

            //Click on size
            sizeElement.click();

            //Get row count
            int rowCount = driver.findElements(By.xpath(tblSearchResult + "//tr")).size();

            //Get pagination
            int pagination = driver.findElements(By.cssSelector("ul.pagination>li>a")).size();
            System.out.println("Size num =  " + sizeNum + ". Row count = " + rowCount + ". Pagination = " + pagination);

            //Verify if the table is displayed with correct size
            if (pagination > 1) {
                Assert.assertEquals(sizeNum, rowCount, "Table size is not correct.");
            } else {
                if (sizeNum > rowCount) {
                    Assert.assertTrue(true, "Table size is not correct.");
                } else {
                    Assert.assertTrue(false, "Table size is larger than the selected size.");
                }
            }
        }
    }
*/

    //Method for deleting food from an inventory

    public void deleteFoodFromInventory(WebDriver driver) {

        String foodname = driver.findElement(By.xpath(tblSearchResult + "//tr[1]/td[1]")).getText();
        System.out.println(foodname);
        getElement(btnDeleteFood).click();
        getElement(btnDeleteItemConfirmation).click();
        Assert.assertEquals(foodname.isEmpty(), true);
    }

    //Method for cancelling the deletion item from inventory
    public void cancelDeletingFoodFromInventory(WebDriver driver) {

        String foodname = driver.findElement(By.xpath(tblSearchResult + "//tr[1]/td[1]")).getText();
        System.out.println("Cancel deleting food name: " +foodname);
        getElement(btnDeleteFood).click();
        getElement(btnCancelConfirmation).click();;
        Assert.assertEquals(foodname.isEmpty(), false);
    }

    //Method for adding  food into inventory
    public void addToInventory(WebDriver driver, String foodName, String category, String quantity, String keyword) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Get expected data before adding inventory
        searchAnItem(driver, keyword);
        ArrayList<String> expectedData = getExpectedInventoryData(quantity);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(btnAddToInventory)));

        //Add inventory
        getElement(btnAddToInventory).click();
        waitForElementInSeconds(3);

        driver.switchTo().activeElement();
        WebElement foodCategoryDrp = getWebElement(txtFoodCategory);
        WebElement foodQuantityDrp = getWebElement(txtFoodQuantity);

        getElement(drpFoodName).click();
        String foodXpath = "//ul//li//a[contains(text(),'" + foodName + "')]";
        getElement(foodXpath).click();

        wait.until(ExpectedConditions.elementToBeClickable(foodCategoryDrp));
        Select s = new Select(foodCategoryDrp);
        s.selectByVisibleText(category);

        wait.until(ExpectedConditions.elementToBeClickable(foodQuantityDrp));
        foodQuantityDrp.click();;
        getElement(txtFoodQuantity).sendKeys(quantity, false);
        waitForElementInSeconds(2);

        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(btnAdd)));
        getElement(btnAdd).click();
        waitForElementInSeconds(2);

        //Get inventory data after adding
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(btnAddToInventory)));
        searchAnItem(driver, keyword);
        ArrayList<String> actualData = getInventoryDataAfterAdding();

        //Verify inventory is added successfully
        Assert.assertEquals(actualData,expectedData,"Inventory is not added correctly");
    }

    public ArrayList<String> getExpectedInventoryData(String quantity) {
        waitForElementInSeconds(3);

        //get current data
        String currentName = getElement("//div[@class='react-bs-container-body']//tr//td[1]").getText();
        String currentCategory = getElement("//div[@class='react-bs-container-body']//tr//td[2]").getText();
        String currentQuantity = getElement("//div[@class='react-bs-container-body']//tr//td[3]").getText();
        int expectedQuantity = Integer.parseInt(currentQuantity) + Integer.parseInt(quantity);

        ArrayList<String> expectedData = new ArrayList<String>();
        expectedData.add(currentName);
        expectedData.add(currentCategory);
        expectedData.add(String.valueOf(expectedQuantity));

        System.out.println("currentData = "+expectedData);
        return expectedData;
    }

    public ArrayList<String> getInventoryDataAfterAdding() {
        waitForElementInSeconds(3);

        //get actual data
        String actualName = getElement("//div[@class='react-bs-container-body']//tr//td[1]").getText();
        String actualCategory = getElement("//div[@class='react-bs-container-body']//tr//td[2]").getText();
        String actualQuantity = getElement("//div[@class='react-bs-container-body']//tr//td[3]").getText();

        ArrayList<String> actualData = new ArrayList<String>();
        actualData.add(actualName);
        actualData.add(actualCategory);
        actualData.add(actualQuantity);

        System.out.println("actualData = " +actualData);
        return actualData;
    }

    public ArrayList<String> getInventoryDataAfterEdit(String category) {
        waitForElementInSeconds(3);

        //get actual data
        int rowCount = getElementsCount("//div[@class='react-bs-container-body']//tr");
        String actualName = "";
        String actualCategory = "";
        String actualQuantity = "";
        ArrayList<String> actualData = new ArrayList<String>();

        if (rowCount == 1) {
            actualName = getElement("//div[@class='react-bs-container-body']//tr//td[1]").getText();
            actualCategory = getElement("//div[@class='react-bs-container-body']//tr//td[2]").getText();
            actualQuantity = getElement("//div[@class='react-bs-container-body']//tr//td[3]").getText();
            actualData.add(actualName);
            actualData.add(actualCategory);
            actualData.add(actualQuantity);

        } else {
            for (int i = 1; i <= rowCount; i++) {
                actualCategory = getElement("//div[@class='react-bs-container-body']//tr[" + i + "]//td[2]").getText();
                if (actualCategory.equalsIgnoreCase(category)) {
                    actualName = getElement("//div[@class='react-bs-container-body']//tr[" + i + "]//td[1]").getText();
                    actualQuantity = getElement("//div[@class='react-bs-container-body']//tr[" + i + "]//td[3]").getText();
                    actualData.add(actualName);
                    actualData.add(actualCategory);
                    actualData.add(actualQuantity);
                    break;
                }else{break;}
            }
        }

        System.out.println("actualData = " +actualData);
        return actualData;
    }

    //Method for editing inventory
    public void editInventory(WebDriver driver,String foodName, String category, String quantity, String keyword){
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(btnAddToInventory)));

        //Edit inventory
        getElement(btnEditInventory).click();
        waitForElementInSeconds(3);

        driver.switchTo().activeElement();
        WebElement foodCategoryDrp = driver.findElement(By.xpath(txtFoodCategory));
        WebElement foodQuantityDrp = driver.findElement(By.xpath(txtFoodQuantity));

        getElement(drpFoodName).click();
        String foodXpath = "//ul//li//a[contains(text(),'" + foodName + "')]";
        getWebElement(foodXpath).click();

        wait.until(ExpectedConditions.elementToBeClickable(foodCategoryDrp));
        Select s = new Select(foodCategoryDrp);
        s.selectByVisibleText(category);

        wait.until(ExpectedConditions.elementToBeClickable(foodQuantityDrp));
        foodQuantityDrp.click();;
        getElement(txtFoodQuantity).sendKeys(quantity, false);
        waitForElementInSeconds(2);

        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(btnUpdate)));
        getElement(btnUpdate).click();
        waitForElementInSeconds(2);

        //Get inventory data after adding
        wait.until(ExpectedConditions.elementToBeClickable(getWebElement(btnAddToInventory)));
        searchAnItem(driver,keyword);
        ArrayList<String> actualData = getInventoryDataAfterEdit(category);

        //Verify inventory is updated successfully
        Assert.assertEquals(actualData.get(0),(foodName));
        Assert.assertEquals(actualData.get(1),(category));
        Assert.assertEquals(actualData.get(2),(quantity));

    }
}


