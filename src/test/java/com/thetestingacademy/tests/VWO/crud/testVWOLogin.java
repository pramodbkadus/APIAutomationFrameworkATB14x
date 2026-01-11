package com.thetestingacademy.tests.VWO.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.VWO.response.LoginResponse;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class testVWOLogin extends BaseTest {


    @Test
    public void testVWOLogin(){

        requestSpecification.baseUri(APIConstants.VWO_BASE_URL);
        requestSpecification.basePath(APIConstants.VWO_LOGIN_URL);

        response = RestAssured
                .given(requestSpecification).relaxedHTTPSValidation()
                .when().body(vwoPayloadManager.setLoginData()).log().all().post();

        LoginResponse loginResponse = vwoPayloadManager.getLoginData(response.asString());

        assertActions.verifyStatusCode(response,200);
    }
}
