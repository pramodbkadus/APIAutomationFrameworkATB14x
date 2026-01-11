package com.thetestingacademy.tests.restFullBooker.crud.createToken;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.restFullBooker.response.BookingResponse;
import com.thetestingacademy.pojos.restFullBooker.response.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class testCreateToken extends BaseTest {

    @Test(groups = "reg", priority = 1)
    @Owner("Pramod Kadus")
    @Description("TC#1 - Create Token and Verify")
    public void testCreateTokenPOST_Positive() {
        //GIVEN & WHEN --> Part 1
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .when()
                .body(payloadManager.setAuthPayload()).log().all().post();

        //Extraction --> Part 2
        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println("TOKEN: " + token);

        //THEN --> Part 3
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyStringKeyNotNull(token);
    }
}
