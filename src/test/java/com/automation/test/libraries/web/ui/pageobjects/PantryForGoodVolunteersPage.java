package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class PantryForGoodVolunteersPage extends BasePage {
    public PantryForGoodVolunteersPage(WebDriver driver){
        super(driver);
    }

    private String mnuVolunteers = "//a[@class='submenu-item']//span[contains(text(),'Volunteers')]";
    private String tblVolunteersList = "//div[@class='react-bs-container-body']//table";
    private String ddlSizePerPage = "//button[@id='pageDropDown']";
    private String lblVolunteerNameHeader = "//div//h1/span";
    private String btnDelete = "//button[@class='btn btn-danger']";
    private String dlgDelete = "//div[@class='modal-content']";
    private String btnDeleteOnDeleteDialog = "//button[@class='btn btn-sm btn-danger']";
    private String btnCancelOnDeleteDialog = "//button[@class='btn btn-sm btn-primary']";

    public WebObject getMnuVolunteers() {
        return findWebElement(mnuVolunteers);
    }

    public WebObject getTblVolunteersList() {
        return findWebElement(tblVolunteersList);
    }

    public WebObject getDdlSizePerPage() {
        return findWebElement(ddlSizePerPage);
    }

    public WebObject getLblVolunteerNameHeader() {
        return findWebElement(lblVolunteerNameHeader);
    }

    public WebObject getBtnDelete() {
        return findWebElement(btnDelete);
    }

    public WebObject getDlgDelete() {
        return findWebElement(dlgDelete);
    }

    public WebObject getBtnDeleteOnDeleteDialog() {
        return findWebElement(btnDeleteOnDeleteDialog);
    }

    public WebObject getBtnCancelOnDeleteDialog() {
        return findWebElement(btnCancelOnDeleteDialog);
    }

    public void goToVolunteersPage(){
        getMnuVolunteers().click();
        System.out.println("Volunteers page");
    }

    public void viewVolunteersInfo(WebDriver driver){
        List<WebElement> rows = driver.findElements(By.xpath(tblVolunteersList + "//tr"));

        if (rows != null && rows.size() > 0){
            String xpathFirstVolName = "//table//tr[1]/td[2]";
            WebElement firstVolunteerName = driver.findElement(By.xpath(xpathFirstVolName));
            System.out.println(firstVolunteerName.getText());

            String xpathFirstVolAddress = "//table//tr[1]/td[3]";
            String firstVolAddress = driver.findElement(By.xpath(xpathFirstVolAddress)).getText();
            if (firstVolAddress != null){
                String[] addressList = firstVolAddress.split(", ");
                String street = addressList[0];
                String townCity = addressList[1];
                String stateProvince = addressList[2];
                String zipPostCode = addressList[3];
            }


            String xpathFirstVolViewBtn = "//table//tr[1]/td[6]//a[@class='btn btn-info btn-sm']";
            WebElement firstVolViewBtn = driver.findElement(By.xpath(xpathFirstVolViewBtn));
            firstVolViewBtn.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getLblVolunteerNameHeader().getText());

            String table_VolunteerName = getLblVolunteerNameHeader().getText();
            String detail_VolunteerNameHeader = getLblVolunteerNameHeader().getText();

            Assert.assertEquals(table_VolunteerName,detail_VolunteerNameHeader);
            System.out.println("User is able to view Info of Volunteer: " + table_VolunteerName);
        } else {
            Assert.assertTrue(false);
        }
    }

    public void deleteAVolunteer (WebDriver driver){
        List<WebElement> rows = driver.findElements(By.xpath(tblVolunteersList + "//tr"));

        if (rows != null && rows.size() > 0){
            String xpathFirstVolName = "//table//tr[1]/td[2]";
            WebElement firstVolunteerName = driver.findElement(By.xpath(xpathFirstVolName));
            String name = firstVolunteerName.getText();
            System.out.println(firstVolunteerName.getText());

            String xpathFirstVolViewBtn = "//table//tr[1]/td[6]//a[@class='btn btn-info btn-sm']";
            WebElement firstVolViewBtn = driver.findElement(By.xpath(xpathFirstVolViewBtn));
            firstVolViewBtn.click();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            getBtnDelete().click();
            waitForElementInSeconds(3);

            driver.switchTo().activeElement();

            //Alert alert = driver.switchTo().alert();
            getBtnDeleteOnDeleteDialog().click();
            getBtnCancelOnDeleteDialog().click();

            getMnuVolunteers().click();
            searchAnItem(driver,name);
            verifySearchResultForNonExistingItem("No volunteers found");
        } else {
            Assert.assertTrue(false);
        }
    }
}
