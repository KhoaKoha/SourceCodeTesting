package com.automation.test.testcases.demo.web.api;

import com.automation.test.libraries.web.api.pantryforgood.utils.FoodService;
import com.automation.test.libraries.web.api.pantryforgood.utils.PantryForGoodBaseTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class PantryForGoodAPITestDemo extends PantryForGoodBaseTest {
    public PantryForGoodAPITestDemo() {}

    @Test()
    public void tc008_GetCategoriesSuccessfully() throws MalformedURLException {
        FoodService foodService = new FoodService();

        foodService.GetCategories();
    }
}
