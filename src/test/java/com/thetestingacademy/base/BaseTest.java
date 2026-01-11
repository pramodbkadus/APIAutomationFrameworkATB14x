package com.thetestingacademy.base;

import com.thetestingacademy.asserts.AssertActions;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.modules.VWO.VWOPayloadManager;
import com.thetestingacademy.modules.restFullBooker.PayloadManager;
import com.thetestingacademy.pojos.restFullBooker.response.TokenResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;

    public PayloadManager payloadManager;
    public VWOPayloadManager vwoPayloadManager;
    public JsonPath jsonPath;
    public AssertActions assertActions;

    @BeforeTest
    public void setup(){
        System.out.println("Starting of the Test");
        payloadManager = new PayloadManager();
        vwoPayloadManager = new VWOPayloadManager();
        assertActions = new AssertActions();

        /*
            requestSpecification = RestAssured.given();
            requestSpecification.baseUri(APIConstants.BASE_URL);
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.relaxedHTTPSValidation();
            requestSpecification.log().all();
        */

        // Using RequestSpecBuilder()\
            requestSpecification = RestAssured.given();
            requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }


    @AfterTest
    public void tearDown(){
        System.out.println("Finished the Test!");
    }
}
