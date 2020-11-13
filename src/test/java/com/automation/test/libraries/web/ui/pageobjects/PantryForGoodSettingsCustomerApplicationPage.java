package com.automation.test.libraries.web.ui.pageobjects;

import com.automation.framework.core.web.ui.object.BasePage;
import com.automation.framework.core.web.ui.object.WebObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class PantryForGoodSettingsCustomerApplicationPage extends BasePage {
    private String btnAddSection = "//button[@class='btn btn-success'][text()='Add Section']";
    //private String txtSectionName = "//div[@class='list-group']//input";
    private String txtSectionName = "css=div.list-group >div.list-group-item:last-child>div>input";
    private String iconSave = "//i[@class='fa fa-save']";
    private String iconDelete = "//i[@class='fa fa-times']";
    private String btnEdit = "//div[1]/span/i[contains(@class,'fa fa-edit')]";
    private String btnDelete = "//div[1]/span/i[contains(@class,'fa-trash-o')]";
    private String btnSave = "//div[@class='text-right']/button[@class='btn btn-success']";
    private String btnSaveConfirmation = "//button[@class='btn btn-sm btn-danger'][text()='Save']";
    private String btnCancelConfirmation = "//button[@class='btn btn-sm btn-primary'][text()='Cancel']";
    private String SectionItem = "//div[@class='list-group']//div";

    private String btnLinkField = "//button[@class='btn btn-primary'][text()='Link Field']";
    private String btnAddField = "//button[@class='btn btn-success'][text()='Add Field']";
    private String txtQuestionLabel = "//input[@id='label']";
    private String iconSaveField = "//button[@class='btn btn-default']/i[@class='fa fa-save']";
    private String iconDeleteField = "//button[@class='btn btn-default']/i[@class='fa fa-trash']";
    private String iconCancelField ="//button[@class='btn btn-default']/i[@class='fa fa-times']";

    private String lastField = "//div[@class='list-group-item'][last()]";
   // private String lastField = "//div/h4[@class='list-group-item-heading'][last()]";
    private String message = "//span[@class='help-block']";
    private String ddlLinkField ="//select[@class='form-control']";



    public PantryForGoodSettingsCustomerApplicationPage(WebDriver driver) {
        super(driver);
    }

    //Navigate to Edit Emails page
    public void goToCustomerApplicationPage(WebDriver driver) {
        driver.navigate().to("http://localhost:8080/settings/questionnaires");
        waitForElementInSeconds(3);
    }

    //Add a new section into Customer Application tab
    public void addSectionAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        getElement(btnAddSection).click();
        waitForElementInSeconds(3);
        String SectionName = "MySection" + r.nextInt();
        getElement(txtSectionName).sendKeys(SectionName, false);
        getElement(iconSave).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();
        System.out.println(SectionName);
        //Verify the section is added into the section list

        if (getElementsCount(txtSectionName) > 0) {
            Assert.assertTrue(false, "Failed to cancel");
            System.out.println(SectionName);
        } else {
            Assert.assertTrue(true, "Failed to cancel");
        }
        getElement(txtSectionName).getText();
    }

    //Cancel add a new section into Customer Application tab
    public void cancelAddSectionAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        getElement(btnAddSection).click();
        waitForElementInSeconds(3);
        String SectionName = "MySection" + r.nextInt();
        getElement(txtSectionName).sendKeys(SectionName, false);
        getElement(iconSave).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnCancelConfirmation).click();
        //Verify the section is added into the section list

        if (getElementsCount(txtSectionName) > 0) {
            Assert.assertTrue(false, "Failed to cancel");
        } else {
            Assert.assertTrue(true, "Failed to cancel");
            System.out.println("Section " + SectionName + " is not added.");
        }
    }

    //Update an existing section in Customer Application tab
    public void updateSectionAtCustomerApplicationTab(WebDriver driver) {

        Random r = new Random();
        getElement(btnEdit).click();
        getElement(txtSectionName).click();

        //  getElement(txtSectionNameEdit).click();

        Actions actions = new Actions(driver);

        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.sendKeys(Keys.DELETE).build().perform();
        String SectionName = "UpdatedSection" + r.nextInt();
        getElement(txtSectionName).sendKeys(SectionName, false);
        getElement(iconSave).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();
        System.out.println("Section name is: " + SectionName);

        //Verify the section is updated into the section list
        if (getElementsCount(txtSectionName) > 0) {
            Assert.assertTrue(false, "Failed to cancel");
            System.out.println(SectionName);
        } else {
            Assert.assertTrue(true, "Failed to cancel");
        }
    }

    //Cancel update an existing section in Customer Application tab
    public void cancelUpdateSectionAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        getElement(btnEdit).click();
        String UpdateSectionName = getElement(txtSectionName).getText();
        System.out.println(UpdateSectionName);
        getElement(txtSectionName).click();

        // getElement(txtSectionNameEdit).click();

        Actions actions = new Actions(driver);

        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.sendKeys(Keys.DELETE).build().perform();
        String SectionName = "UpdatedSection" + r.nextInt();

        getElement(txtSectionName).sendKeys(SectionName, false);
        getElement(iconSave).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnCancelConfirmation).click();


        //Verify the section is updated into the section list
        if (getElementsCount(txtSectionName) > 0) {
            Assert.assertTrue(false, "Failed to cancel");
            System.out.println(SectionName);
        } else {
            Assert.assertTrue(true, "Failed to cancel");
            System.out.println("The current section is not updated by " + SectionName);
        }
    }

    //Delete section from section list at Customer Application tab
    public void deleteSectionAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        String Item = getElement(SectionItem).getText();
        getElement(btnDelete).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();
        System.out.println("Section name is: " + Item);

        //Verify the section is not deleted from the section list
        if (getElement(SectionItem).getText().equalsIgnoreCase(Item)) {
            Assert.assertTrue(false, "Failed to delete");

        } else {
            Assert.assertTrue(true, "Deleted successfully");
        }
    }

    //Cancel delete section from section list at Customer Application tab
    public void cancelDeleteSectionAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        String Item = getElement(SectionItem).getText();
        getElement(btnDelete).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnCancelConfirmation).click();
        System.out.println("Cancel to delete section: " + Item);

        //Verify canceling deletion section from the section list
        if (getElement(SectionItem).getText().equalsIgnoreCase(Item)) {
            Assert.assertTrue(false, "Failed to cancel");

        } else {
            Assert.assertTrue(true, "Cancel to delete section successfully");
        }
    }

    //Link field
    public void linkFieldAtCustomerApplicationTab(WebDriver driver) {

        WebElement dropdownElement = driver.findElement(By.xpath("//select[@class='form-control']"));
        // create object for Select class
        Select dropdown = new Select(dropdownElement);
        // select an option from the dropdown options

        dropdown.selectByIndex(1);
        getElement(btnLinkField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();
    }

        //Cancel link field
    public void cancelLinkFieldAtCustomerApplicationTab(WebDriver driver) {

        WebElement dropdownElement = driver.findElement(By.xpath("//select[@class='form-control']"));
        // create object for Select class
        Select dropdown = new Select(dropdownElement);
        // select an option from the dropdown options

        dropdown.selectByIndex(1);

        getElement(btnLinkField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnCancelConfirmation).click();
    }

    //Add a new field into Customer Application tab
    public void addFieldAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        getElement(btnAddField).click();
        waitForElementInSeconds(3);
        String FieldName = "MyField" + r.nextInt();
        getElement(txtQuestionLabel).sendKeys(FieldName, false);
        getElement(iconSaveField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();

        //Verify the section is added into the section list

        if (getElementsCount(txtQuestionLabel) > 0) {
            Assert.assertTrue(false, "Failed to add");

        } else {
            Assert.assertTrue(true, "Add successfully");
            System.out.println(FieldName);
        }
    }

    //Cancel add a new field into Customer Application tab
    public void cancelAddFieldAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        getElement(btnAddField).click();
        waitForElementInSeconds(3);
        String FieldName = "MyField" + r.nextInt();
        getElement(txtQuestionLabel).sendKeys(FieldName, false);
        getElement(iconSaveField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnCancelConfirmation).click();

        //Verify the section is added into the section list

        if (getElementsCount(txtQuestionLabel) > 0) {
            Assert.assertTrue(false, "Failed to cancel adding field");

        } else {
            Assert.assertTrue(true, "Cancel add field successfully");
            System.out.println("Cancel add field successfully:" + FieldName);
        }
    }

    //Add new field without adding field name into Customer Application tab
    public void addFieldWithoutNameAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        getElement(btnAddField).click();
        waitForElementInSeconds(3);
        getElement(iconSaveField).click();
        String returnmessage = getElement(message).getText();
        String expectedmessage = "Label is required";
        Assert.assertEquals(expectedmessage, returnmessage);
    }

    //Edit field at Customer Application tab
    public void editFieldAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        String fieldName = getElement(lastField).getText();
        System.out.println("FieldName before updating: " +fieldName);
        getElement(lastField).click();
        String EditFieldName = "UpdateField" + r.nextInt();
        getElement(txtQuestionLabel).sendKeys(EditFieldName, false);
        getElement(iconSaveField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();

        //Verify the section is updated into the field list

        String ActualFieldName = getElement(lastField).getText();
            Assert.assertNotSame(ActualFieldName, fieldName);
            System.out.println("FieldName after updating:" +ActualFieldName);

    }

    //Edit field and type at Customer Application tab
    public void editFieldAndTypeAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        String fieldName = getElement(lastField).getText();
        System.out.println("FieldName before updating: " +fieldName);
        getElement(lastField).click();
        String EditFieldName = "UpdateField" + r.nextInt();
        getElement(txtQuestionLabel).sendKeys(EditFieldName, false);

        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='type']"));
        // create object for Select class
        Select dropdown = new Select(dropdownElement);
        // select an option from the dropdown options
        dropdown.selectByIndex(1);

        getElement(iconSaveField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();

        //Verify the field is updated into the field list
        String ActualFieldName = getElement(lastField).getText();
        Assert.assertNotSame(ActualFieldName, fieldName);
        System.out.println("FieldName after updating: " +ActualFieldName);

    }

    //Cancel  edit field into Customer Application tab

    public void cancelEditFieldAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        String fieldName = getElement(lastField).getText();
        System.out.println("FieldName before and after cancel update: " + fieldName);
        getElement(lastField).click();
        String EditFieldName = "UpdateField" + r.nextInt();
        getElement(txtQuestionLabel).sendKeys(EditFieldName, false);
        getElement(iconSaveField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnCancelConfirmation).click();
        //getElement(iconCancelField).click();
        Assert.assertTrue(true, "Cancel update field successfully");
    }

    //Delete field  from Customer Application tab
    public void deleteFieldAtCustomerApplicationTab(WebDriver driver) {
        String FieldName = getElement(lastField).getText();
        getElement(lastField).click();
        getElement(iconDeleteField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnSaveConfirmation).click();

        //Verify the field is deleted from the field list
        if (getElement(lastField).getText().equalsIgnoreCase(FieldName)) {
            Assert.assertTrue(false, "Failed to delete");

        } else {
            Assert.assertTrue(true, "Deleted successfully");
            System.out.println(FieldName);
        }
    }

    //Delete field  from Customer Application tab
    public void cancelDeleteFieldAtCustomerApplicationTab(WebDriver driver) {
        Random r = new Random();
        String FieldName = getElement(lastField).getText();
        getElement(lastField).click();
        getElement(iconDeleteField).click();
        getElement(btnSave).click();
        driver.switchTo().activeElement();
        getElement(btnCancelConfirmation).click();

        //Verify the field is deleted from the field list
        if (getElement(lastField).getText().equalsIgnoreCase(FieldName)) {
            Assert.assertTrue(false, "CANCEL to delete");

        } else {
            Assert.assertTrue(true, "Deleted successfully");
            System.out.println(FieldName);
        }
    }
}
