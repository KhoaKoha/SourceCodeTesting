package com.automation.test.libraries.web.api.pantryforgood.models;

public class PantryForGoodBaseObject {
    public static final String PANTRY_FOR_GOOD_INFORMATION_FILE_NAME = "PantryForGoodInfo.json";

    private String baseURL;
    private String email;
    private String password;
    private String cookiesConnectSid;


    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCookiesConnectSid() {
        return cookiesConnectSid;
    }

    public void setCookiesConnectSid(String cookies) {
        this.cookiesConnectSid = cookies;
    }
}
