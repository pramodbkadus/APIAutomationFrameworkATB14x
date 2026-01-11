package com.thetestingacademy.tests.restFullBooker.e2e_integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.restFullBooker.request.Booking;
import com.thetestingacademy.pojos.restFullBooker.response.BookingResponse;
import com.thetestingacademy.pojos.restFullBooker.response.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class testIntegrationFlow1 extends BaseTest {

    @Test(groups = "qa", priority = 1)
    @Owner("Pramod Kadus")
    @Description("TC#INT1 - Step 1. Verify that the Auth Token can be created")
    public void testAuthToken(ITestContext iTestContext){

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .when().body(payloadManager.setAuthPayload()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println("TOKEN: " + token);

        assertActions.verifyStringKeyNotNull(token);

        iTestContext.setAttribute("token", token);
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Pramod Kadus")
    @Description("TC#INT1 - Step 1. Verify that the booking can be created")
    public void testCreateBooking(ITestContext iTestContext){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .when().body(payloadManager.createBookingPayload()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = payloadManager.bookingResponse(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Pramod");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());

        Integer bookingId = bookingResponse.getBookingid();
        iTestContext.setAttribute("bookingId", bookingId);
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Pramod Kadus")
    @Description("TC#INT1 - Step 2. Verify that Get the booking by ID")
    public void testVerifyBookingId(ITestContext iTestContext){

        Integer bookingId = (Integer) iTestContext.getAttribute("bookingId");
        String basePathGET = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+ bookingId;

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .relaxedHTTPSValidation()
                .when().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getBookingByIdResponse(response.asString());
        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertActions.verifyStringKey(booking.getFirstname(), "Pramod");
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Pramod Kadus")
    @Description("TC#INT1 - Step 3. Verify Updated booking by ID")
    public void testUpdateBookingById(ITestContext iTestContext){

        Integer bookingId = (Integer) iTestContext.getAttribute("bookingId");
        String token = (String) iTestContext.getAttribute("token");

        String basePathUPDATE = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+ bookingId;

        requestSpecification.basePath(basePathUPDATE);
        response = RestAssured
                .given(requestSpecification)
                    .relaxedHTTPSValidation()
                    .cookie("token",token)
                    .body(payloadManager.updateBookingPayload())
                .when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getBookingByIdResponse(response.asString());
        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertActions.verifyStringKey(booking.getFirstname(), "Lucky");
        assertActions.verifyStringKey(booking.getLastname(),"Dutta");
    }

    @Test(groups = "qa", priority = 5)
    @Owner("Pramod Kadus")
    @Description("TC#INT1 - Step 4. Delete the booking by ID")
    public void testDeleteBookingById(ITestContext iTestContext){

        Integer bookingId = (Integer) iTestContext.getAttribute("bookingId");
        String token = (String) iTestContext.getAttribute("token");

        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+ bookingId;

        requestSpecification.basePath(basePathDELETE).cookie("token",token);

        response = RestAssured.given(requestSpecification).relaxedHTTPSValidation().when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

    }

}
