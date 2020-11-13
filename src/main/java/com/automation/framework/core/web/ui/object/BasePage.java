package com.automation.framework.core.web.ui.object;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

enum SearchBy {
    ID,
    XPath,
    Name,
    Css
}

public class BasePage {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class);

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public WebObject findWebElement(String locator) {
        SearchBy searchType = SearchBy.ID;
        String searchStr = locator;
        WebObject findObject = null;

        if (locator.startsWith("//")) {
            searchType = SearchBy.XPath;
        } else {
            int idx = locator.indexOf('=');

            if (idx > 0) {
                String locatorHead = locator.substring(0, idx);
                SearchBy lookingUpSearchBy = null;

                for (SearchBy s : SearchBy.values()) {
                    if (s.name().equalsIgnoreCase(locatorHead)) {
                        lookingUpSearchBy = s;
                        break;
                    }
                }

                if (lookingUpSearchBy == null) {
                    throw new NullPointerException("SearchBy header text not found");
                }

                searchType = lookingUpSearchBy;
                searchStr = locator.substring(idx + 1);
            }
        }

        try {
            switch (searchType) {
                case ID:
                    findObject = new WebObject(driver.findElement(new By.ById(searchStr)));
                    break;
                case XPath:
                    findObject = new WebObject(driver.findElement(new By.ByXPath(searchStr)));
                    break;
                case Name:
                    findObject = new WebObject(driver.findElement(new By.ByName(searchStr)));
                    break;
                case Css:
                    findObject = new WebObject(driver.findElement(new By.ByCssSelector(searchStr)));
                    break;
                default:
                    break;
            }
        } catch (StaleElementReferenceException ex) {
            LOGGER.error(ex.getMessage());
        }

        return findObject;
    }

    public void navigateToURL(String sURL){
        driver.navigate().to(sURL);
    }

    public boolean isPageTitleContains(String searchStr) {
        return driver.getTitle().contains(searchStr);
    }


    //Search method
    public WebObject getTxtSearch() {
        String txtSearch = "//input[@placeholder='Search']";
        return findWebElement(txtSearch);
    }

    public WebObject getLblDefaultPageSize() {
        String lblDefaultPageSize = "//button[@id='pageDropDown']";
        return findWebElement(lblDefaultPageSize);
    }

    public void searchAnItem(WebDriver driver, String keyword) {
        String txtSearch = "//input[@placeholder='Search']";
        String tblSearchResult = "//div[@class='react-bs-container-body']//table";
        int rowCount = getElementsCount(tblSearchResult + "//tr");
        if (keyword != null) {
            getElement(txtSearch).sendKeys(keyword, false);
        } else {
            if (rowCount == 1) {
                String getRowContent = getElement(tblSearchResult + "//tr[1]//td[1]").getText();
                if (getRowContent.contains("No") && getRowContent.contains("found")) {
                    keyword = "Jane";
                }
            } else {
                keyword = getElement(tblSearchResult + "//tr[1]//td[1]").getText();
            }

            String expectedSearchResult = keyword;

            getElement(txtSearch).sendKeys(keyword, false);
            String actualSearchResult = getElement(tblSearchResult + "//tr[1]//td[1]").getText();

            Assert.assertEquals(actualSearchResult, expectedSearchResult, "The search result is not matched with the keyword");

        }
    }

    //Verify search result of non-existed item
    public void verifySearchResultForNonExistingItem(String expectedResult) {
        String resultContent = getElement("//td[@class='react-bs-table-no-data']").getText();
        Assert.assertEquals(resultContent, expectedResult, "The result is not correct");
    }

    //Methods to sort table by columns
    public void clickOnColumnToSort(String columnToSort) {
        String headerXpath = "//table//th[@title='";
        getElement(headerXpath + columnToSort + "']").click();
    }

    public void verifySortTableWorksCorrectly(WebDriver driver, String columnToSort, String sortOrder, String pageName) {
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


/*

    public void verifySortTableWorksCorrectly(WebDriver driver, String columnToSort, String sortOrder, String pageName) {
        boolean isTheSortCorrect = true;
        ArrayList<String> actualResult = getActualResult(driver, columnToSort, pageName);
        ArrayList<String> expectedResult = SerializationUtils.clone(actualResult);
        ArrayList<String> emailActualResult = new ArrayList<String>();
        ArrayList<String> emailExpectedResult = new ArrayList<String>();

        if (sortOrder.equalsIgnoreCase("desc")) {
            if (columnToSort.equalsIgnoreCase("email")) {
                for (String a : actualResult) {
                    emailActualResult.add(a.split("@")[0]);
                }

                for (String e : expectedResult) {
                    emailExpectedResult.add(e.split("@")[0]);
                }
                Collections.sort(emailExpectedResult, Collections.reverseOrder());

                for (int i = 0; i < emailActualResult.size(); i++) {
                    if (!emailActualResult.get(i).equalsIgnoreCase(emailExpectedResult.get(i))) {
                        isTheSortCorrect = false;
                        break;
                    } else {
                        isTheSortCorrect = true;
                    }
                }

                Assert.assertTrue(isTheSortCorrect, "The result is NOT sorted in DESC");
                System.out.println("emailActualResult"+emailActualResult);
                System.out.println("emailExpectedResult"+emailExpectedResult);
            } else {
                Collections.sort(expectedResult, Collections.reverseOrder());
                System.out.println("Expected result is sorted in DESC: " + expectedResult);

                for (int i = 0; i < actualResult.size(); i++) {
                    if (!actualResult.get(i).equals(expectedResult.get(i))) {
                        isTheSortCorrect = false;
                        break;
                    }
                }
            }
            Assert.assertTrue(isTheSortCorrect, "The result is NOT sorted in DESC");
        } else if (sortOrder.equalsIgnoreCase("asc")) {
            if (columnToSort.equalsIgnoreCase("email")) {
                for (String a : actualResult) {
                    emailActualResult.add(a.split("@")[0]);
                }

                for (String e : expectedResult) {
                    emailExpectedResult.add(e.split("@")[0]);
                }
                Collections.sort(emailExpectedResult);

                for (int i = 0; i < emailActualResult.size(); i++) {
                    if (!emailActualResult.get(i).equalsIgnoreCase(emailExpectedResult.get(i))) {
                        isTheSortCorrect = false;
                        break;
                    } else {
                        isTheSortCorrect = true;
                    }
                }

                Assert.assertTrue(isTheSortCorrect, "The result is NOT sorted in ASC");
                System.out.println("emailActualResult"+emailActualResult);
                System.out.println("emailExpectedResult"+emailExpectedResult);
            } else {
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
    }

*/


    public ArrayList<String> getActualResult(WebDriver driver, String columnToSort, String pageName) {
        String tblSearchResult = "//div[@class='react-bs-container-body']//table";
        int rowCount = getElementsCount(tblSearchResult + "//tr");
        ArrayList<String> actualResult = new ArrayList<String>();

        for (int i = 1; i <= rowCount; i++) {
            switch (columnToSort) {
                case "#":
                    actualResult.add(getElement(tblSearchResult + "//tr[" + i + "]//td[1]").getText());
                    break;
                case "Name":
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
            }
        }
        System.out.println("actualResult: " + actualResult);
        return actualResult;
    }

    public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementInSeconds(int timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifyListPageSizesAndDefaultOption(WebDriver driver, String listPageSizes) {
        String lstPageSizes, actualSize, expectedSize;
        lstPageSizes = "//a[@role='menuitem']";
        List<WebElement> ddlPageSizes = driver.findElements(By.xpath(lstPageSizes));
        String[] pageSizes = listPageSizes.split(",");
        String defaultPageSize = getLblDefaultPageSize().getText();

        if (ddlPageSizes.size() > 0 && ddlPageSizes.size() == pageSizes.length) {
            if (defaultPageSize.equals(pageSizes[0])) {
                Assert.assertTrue(true);
                System.out.println("Default page size is 10");
            } else {
                Assert.assertTrue(false, "Default page size is not equals 10");
            }

            getLblDefaultPageSize().click();

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

    public WebObject getElement(String locator) {
        waitForElementToBeVisible(driver,getWebElement(locator));
        return findWebElement(locator);
    }

    public WebElement getWebElement(String locator) {
        SearchBy searchType = getSearchType(locator);
        WebElement element = null;
        try{
            switch (searchType){
                case XPath:
                    element = driver.findElement(By.xpath(locator));
                    break;
                case ID:
                    element = driver.findElement(By.id(getLocatorValue(locator)));
                    break;
                case Name:
                    element = driver.findElement(By.name(getLocatorValue(locator)));
                    break;
                case Css:
                    element = driver.findElement(By.cssSelector(getLocatorValue(locator)));
                    break;
            }

        }catch (Exception e) {
            System.out.println("No element found.");
            e.getMessage();
        }
        return element;
    }

    public int getElementsCount(String locator) {
        SearchBy searchType = getSearchType(locator);
        int elementCount = 0;
        try {
            switch (searchType) {
                case XPath:
                    elementCount = driver.findElements(By.xpath(locator)).size();
                    break;
                case ID:
                    elementCount = driver.findElements(By.id(getLocatorValue(locator))).size();
                    break;
                case Name:
                    elementCount = driver.findElements(By.name(getLocatorValue(locator))).size();
                    break;
                case Css:
                    elementCount = driver.findElements(By.cssSelector(getLocatorValue(locator))).size();
                    break;
            }
        } catch (Exception e) {
            System.out.println("No element found.");
            e.getMessage();
        }
        return elementCount;

    }

    public SearchBy getSearchType(String locator){
        SearchBy searchType = null;
        String locatorType;

        if (locator.startsWith("//")) {
            searchType = SearchBy.XPath;
        } else {
            locatorType = locator.split("=")[0];
            for (SearchBy sb : SearchBy.values()) {
                if (locatorType.equalsIgnoreCase(sb.name())) {
                    searchType = sb;
                    break;
                }
            }
        }
        return searchType;
    }

    public String getLocatorValue(String locator){
        String locatorValue = locator.split("=")[1];
        return locatorValue;
    }

    public void selectSizePerPageAndVerifyTableSize(WebDriver driver) {
        String tblSearchResult = "//div[@class='react-bs-container-body']//table//tbody//tr";
        String tblPackingList = "//button[contains(text(),'Mark')]//preceding::table[1]//tbody//tr";
        String lnkNext = "//ul//li[@title='next page']//a";
        String lnkFirstPage = "//ul//li[@title='1']//a";
        String btnSizePerPage = "//button[@id='pageDropDown']";
        String tblXpath = "";
        String drpSizeXpath = "//div[@class='react-bs-table-pagination']//ul[@class='dropdown-menu']//li//a";
        List<WebElement> drpSizes;
        WebElement next, firstPage;
        boolean isTableSizeCorrect = false;
        int rowCount;
        int pageNum = 1;
        String drpSizeSelected;

        if (driver.getTitle().contains("Packing")) {
            tblXpath = tblPackingList;
            lnkNext = "//button[contains(text(),'Mark')]//preceding::ul//li[@title='next page']//a";
            lnkFirstPage = "//button[contains(text(),'Mark')]//preceding::ul//li[@title='1']//a";
            drpSizeXpath = "//button[contains(text(),'Mark')]//preceding::div[@class='react-bs-table-pagination']//ul[@class='dropdown-menu']//li//a";
        } else {
            tblXpath = tblSearchResult;
        }
        drpSizes = driver.findElements(By.xpath(drpSizeXpath));

        for (WebElement drpSize : drpSizes) {
            getElement(btnSizePerPage).click();
            drpSize.click();

            if (getElementsCount(lnkNext) > 0) {
                next = getWebElement(lnkNext);

                drpSizeSelected = drpSize.getAttribute("data-page");
                rowCount = getElementsCount(tblXpath);
                System.out.print("drpSize = " + drpSizeSelected + " ");
                System.out.println("rowCount = " + rowCount + " pageNum = " + pageNum);

                if (rowCount > Integer.parseInt(drpSizeSelected) || rowCount < Integer.parseInt(drpSizeSelected)) {
                    isTableSizeCorrect = false;
                    break;
                } else {
                    while (next.isDisplayed()) {
                        next.click();
                        pageNum++;
                        rowCount = getElementsCount(tblXpath);
                        System.out.print("drpSize = " + drpSizeSelected + " ");
                        System.out.println("rowCount = " + rowCount + " pageNum = " + pageNum);
                        if (rowCount == Integer.parseInt(drpSizeSelected)) {
                            isTableSizeCorrect = true;
                        } else {
                            isTableSizeCorrect = false;
                            break;
                        }
                    }
                    pageNum = 1;

                    if (getElementsCount(lnkNext) <= 0) {
                        rowCount = getElementsCount(tblXpath);
                        if (rowCount <= Integer.parseInt(drpSizeSelected)) {
                            isTableSizeCorrect = true;
                        } else {
                            isTableSizeCorrect = false;
                        }
                    }
                    System.out.println();
                    firstPage = getWebElement(lnkFirstPage);
                    JavascriptExecutor ex=(JavascriptExecutor)driver;
                    ex.executeScript("arguments[0].click()", firstPage);
                }
                Assert.assertTrue(isTableSizeCorrect, "Row count is not equal to Page Size selected");
            } else {
                drpSizeSelected = drpSize.getAttribute("data-page");
                rowCount = getElementsCount(tblXpath);
                System.out.print("drpSize = " + drpSizeSelected + " ");
                System.out.println("rowCount = " + rowCount + " pageNum = " + pageNum);

                if (rowCount <= Integer.parseInt(drpSizeSelected)) {
                    isTableSizeCorrect = true;
                } else {
                    isTableSizeCorrect = false;
                    break;
                }
            }
            Assert.assertTrue(isTableSizeCorrect, "Row count is not equal to Page Size selected");
        }
    }

    public String btnSuccess = "//button[@class='btn btn-success']";
    public String btnCancel = "//button[@class='btn btn-primary']";
}
