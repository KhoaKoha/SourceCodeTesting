package com.automation.test.libraries.web.api.pantryforgood.utils;

import com.automation.test.libraries.web.api.pantryforgood.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

/**
 * Base Service class for Pantry For Good
 */
public class PantryForGoodService {
    public static final String API_SIGN_IN_PATH = "api/auth/signin";
    public static final String COOKIES_CONNECT_SID_LABEL = "connect.sid";

    /**
     * This method tries to log in to the API system and store the Cookie connect string into a global variable
     * We can call the method as many time as we need, but it only runs 1 one when the test starts
     * TODO: Need to improve this method to save the cookies into a text file to reuse later, also check if it expired
      */
    public void SignIn() {
        // check if the cookies connection information exists or not
        if (PantryForGoodBaseTest.baseObject != null &&
                !PantryForGoodBaseTest.baseObject.getCookiesConnectSid().isEmpty()) {
            return;
        }

        // try to sign in and save the cookies information
        assert PantryForGoodBaseTest.baseObject != null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Response response = given().relaxedHTTPSValidation().auth().none().contentType(JSON)
                .body(gson.toJson(PantryForGoodBaseTest.baseObject))
                .when().log().headers().post(PantryForGoodBaseTest.baseObject.getBaseURL() + API_SIGN_IN_PATH);

        // make sure the user can sign in successfully
        assert response.statusCode() == HttpStatus.SC_OK;

        PantryForGoodBaseTest.baseObject.setCookiesConnectSid(COOKIES_CONNECT_SID_LABEL + "=" +
                response.getCookie("connect.sid"));

        // verify response data
        JsonObject jsonObj = new JsonParser().parse(response.body().prettyPrint()).getAsJsonObject();

        User returnedUser = gson.fromJson(jsonObj, User.class);

        assert returnedUser.getEmail().equals(PantryForGoodBaseTest.baseObject.getEmail());
    }
}
