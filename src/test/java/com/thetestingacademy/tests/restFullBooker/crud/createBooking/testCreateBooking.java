package com.thetestingacademy.tests.restFullBooker.crud.createBooking;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.pojos.restFullBooker.response.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class testCreateBooking extends BaseTest {

    @Test(groups = "reg", priority = 1)
    @Owner("Pramod Kadus")
    @Description("TC#1 - Verify that the Booking can be Created")
    public void testCreateBookingPOST_Positive(){
        //GIVEN & WHEN --> Part 1
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured
                .given(requestSpecification)
                .when()
                    .body(payloadManager.createBookingPayload()).log().all().post();

        //Extraction --> Part 2
        BookingResponse bookingResponse = payloadManager.bookingResponse(response.asString());

        //THEN --> Part 3
        assertActions.verifyStatusCode(response,200);
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Pramod");


    }

}
