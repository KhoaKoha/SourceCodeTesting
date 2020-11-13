package com.automation.framework.core.datadriven.utils;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

public class JSONFileHelper {
    public static Object[] getDataFromFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(new File(filePath).toPath()));

        JsonParser jParser = new JsonParser();
        JsonElement jsonEle = jParser.parse(content);

        ArrayList<JsonElement> dataList = new ArrayList<>();

        if (jsonEle.isJsonArray()) {
            JsonArray arr = jsonEle.getAsJsonArray();

            for (JsonElement jsonElement : arr) {
                dataList.add(jsonElement);
            }
        } else {
            dataList.add(jsonEle);
        }

        return dataList.toArray();
    }

    public static <T> Object convertJsonElementToClass(Object data, Class<T> targetClass) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson((JsonElement)data, (Type) targetClass);
    }

    public static Map<String, String> convertJsonObjectToMap(Object data) {
        Gson gson = new Gson();
        return gson.fromJson((JsonElement)data, (Type) Map.class);
    }
}
