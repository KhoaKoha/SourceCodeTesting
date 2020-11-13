package com.automation.framework.core.web.ui.object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class WebObject implements WebDriver {
    private WebElement webElement;

    public WebObject(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getText() {
        return webElement.getText();
    }

    public String getValue() {
        return webElement.getAttribute("value");
    }

    public String getAttribute(String attValue) {
        return webElement.getAttribute(attValue);
    }

    public void click() {
        webElement.click();
    }

    public void clear(){
        webElement.clear();
    }

    public void sendKeys(String inputText, boolean submit) {
        webElement.clear();

        webElement.sendKeys(inputText);

        if (submit) {
            webElement.submit();
        }
    }

    public boolean isSelected() {
        if(webElement.isSelected()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void get(String s) {

    }

    @Override
    public String getCurrentUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public String getPageSource() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void quit() {

    }

    @Override
    public Set<String> getWindowHandles() {
        return null;
    }

    @Override
    public String getWindowHandle() {
        return null;
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }

    public void sendKeys(Keys enter) {
    }
}
