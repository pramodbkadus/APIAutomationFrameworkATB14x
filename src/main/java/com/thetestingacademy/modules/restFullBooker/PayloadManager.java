package com.thetestingacademy.modules.restFullBooker;

import com.google.gson.Gson;
import com.thetestingacademy.pojos.restFullBooker.request.Auth;
import com.thetestingacademy.pojos.restFullBooker.request.Booking;
import com.thetestingacademy.pojos.restFullBooker.request.BookingDates;
import com.thetestingacademy.pojos.restFullBooker.response.BookingResponse;
import com.thetestingacademy.pojos.restFullBooker.response.TokenResponse;

public class PayloadManager {
    Gson gson;

    /* -----------------------------AUTH TOKEN-------------------------------------------*/
    // VALID BODY --> AUTH TOKEN
    //Serialization
    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the -> " + jsonPayloadString);
        return jsonPayloadString;

    }

    // RESPONSE --> AUTH TOKEN
    //DeSerialization
    public String getTokenFromJSON(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return  tokenResponse1.getToken();
    }


    /* -----------------------------CREATE BOOKING-------------------------------------------*/
    // VALID BODY --> CREATE BOOKING
    //Convert the Java Object into the JSON String to use as payload.
    //Serialization
    public String createBookingPayload(){
        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Kadus");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    // INVALID BODY --> CREATE BOOKING
    //Convert the Java Object into the JSON String to use as payload.
    //Serialization
    public String createBookingPayloadInvaild(){
        Booking booking = new Booking();
        booking.setFirstname("俊杰; 俊杰");
        booking.setLastname("俊杰; 俊杰");
        booking.setTotalprice(112);
        booking.setDepositpaid(false);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("5025-02-01");
        bookingdates.setCheckout("5025-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("俊杰; 俊杰");

        System.out.println(booking);

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    //Covert the JSON String to Java Object so that we can verify response.
    //DeSerialization
    public BookingResponse bookingResponse(String responseString){
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    /* -----------------------------GET BOOKING BY ID-------------------------------------------*/
    //DeSerialization
    public Booking getBookingByIdResponse(String responseString){
        gson = new Gson();
        Booking booking = gson.fromJson(responseString, Booking.class);
        return booking;
    }

    /* -----------------------------UPDATE BOOKING-------------------------------------------*/
    // VALID BODY --> UPDATE BOOKING
    //Serialization
    public String updateBookingPayload(){
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

}
