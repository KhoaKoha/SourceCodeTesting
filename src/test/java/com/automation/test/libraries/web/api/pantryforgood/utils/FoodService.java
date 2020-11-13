package com.automation.test.libraries.web.api.pantryforgood.utils;

import com.automation.test.libraries.web.api.pantryforgood.models.Category;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

/**
 * Service class for Food Service
 */
public class FoodService extends PantryForGoodService {
    private static final Logger LOGGER = Logger.getLogger(FoodService.class);

    public static final String CATEGORIES_PATH = "api/foods";
    public static final String GET_CATEGORIES_PARAMS_TEMPLATE = "?Cookies=";

    /**
     * This method tries to get the list of categories and print out into the console
     * GET method also called with the Header setup correctly otherwise, it returns Unauthorized message
     */
    public void GetCategories() {
        super.SignIn(); // sign in first if not yet

        // get the categories
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String requestStr = PantryForGoodBaseTest.baseObject.getBaseURL() + CATEGORIES_PATH +
                GET_CATEGORIES_PARAMS_TEMPLATE +
                PantryForGoodBaseTest.baseObject.getCookiesConnectSid();

        Response response = given().auth().none().contentType(JSON)
                .when().headers("Cookie", PantryForGoodBaseTest.baseObject.getCookiesConnectSid()).get(requestStr);

        assert response.statusCode() == HttpStatus.SC_OK;

        ArrayList<Category> catList = gson.fromJson(new JsonParser().parse(response.body().prettyPrint()),
                new TypeToken<List<Category>>(){}.getType());

        // just printing
        for(Category cat: catList) {
            LOGGER.info("Category name:[" + cat.getCategory() + "]");
        }
    }
}
