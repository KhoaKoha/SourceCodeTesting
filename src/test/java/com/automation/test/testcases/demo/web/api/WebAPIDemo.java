package com.automation.test.testcases.demo.web.api;

import com.automation.framework.core.base.OrgBaseTest;
import com.automation.framework.core.datadriven.utils.DataProviderClass;
import com.automation.framework.core.datadriven.utils.JSONFileHelper;
import com.automation.test.libraries.web.api.jsonplaceholder.models.Post;
import com.automation.test.libraries.web.api.jsonplaceholder.utils.PostMethods;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;

public class WebAPIDemo extends OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(WebAPIDemo.class);

    public WebAPIDemo() {
        // Rest API Demo: https://github.com/typicode/jsonplaceholder#how-to
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc003_VerifyGetPostObjectByIdSuccessfully(HashMap<String, String> data) {
        PostMethods postMethods = new PostMethods();
        Post postObj = postMethods.getPostObjectByID(data.get("post_object_id"));

        LOGGER.info(postObj);
    }

    @Test()
    public void tc004_VerifyCreateNewPostObjectSuccessfully() {
        PostMethods postMethods = new PostMethods();

        Post postObj = postMethods.createPostObject(
                new Post(1, 0, "Khanh Learn Auto", "This is very old.")
        );

        LOGGER.info(postObj);
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc005_VerifyCreateNewPostObjectFromJson(Object data) {
        PostMethods postMethods = new PostMethods();

        Post postObj = postMethods.createPostObject((Post)JSONFileHelper.convertJsonElementToClass(data, Post.class));

        LOGGER.info(postObj);
    }

    @Test(dataProvider = "CreateData", dataProviderClass = DataProviderClass.class)
    public void tc006_VerifyCreateNewPostObjectFromJsonSingleObject(Object data) {
        PostMethods postMethods = new PostMethods();

        Post postObj = postMethods.createPostObject((Post)JSONFileHelper.convertJsonElementToClass(data, Post.class));

        LOGGER.info(postObj);
    }
}
