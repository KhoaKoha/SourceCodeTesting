package com.automation.test.libraries.web.api.pantryforgood.utils;

import com.automation.framework.core.base.OrgBaseTest;
import com.automation.test.libraries.web.api.pantryforgood.models.PantryForGoodBaseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;

public class PantryForGoodBaseTest extends OrgBaseTest {
    public static PantryForGoodBaseObject baseObject = null;

    @BeforeMethod
    public void runBeforeTest() throws IOException {
        String content = new String(Files.readAllBytes(new File(OrgBaseTest.getDataFolderPath() +
                File.separator + PantryForGoodBaseObject.PANTRY_FOR_GOOD_INFORMATION_FILE_NAME).toPath()));

        JsonParser jParser = new JsonParser();
        JsonElement jsonEle = jParser.parse(content);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        baseObject = gson.fromJson(jsonEle, (Type) PantryForGoodBaseObject.class);
    }
}