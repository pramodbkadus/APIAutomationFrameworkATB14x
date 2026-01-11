package com.thetestingacademy.modules.VWO;

import com.google.gson.Gson;
import com.thetestingacademy.pojos.VWO.request.LoginRequest;
import com.thetestingacademy.pojos.VWO.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VWOPayloadManager {
    private static final Logger log = LoggerFactory.getLogger(VWOPayloadManager.class);
    Gson gson;

    /* -----------------------------VWO LOGIN-------------------------------------------*/
    // VALID REQUEST BODY
    //Serialization
    public String setLoginData(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("contact+aug@thetestingacademy.com");
        loginRequest.setPassword("TtxkgQ!s$rJBk85");

        gson = new Gson();
        String jsonPayloadString = gson.toJson(loginRequest);
        System.out.println("Payload Login to the -> " + jsonPayloadString);
        return jsonPayloadString;
    }

    // RESPONSE BODY
    //DeSerialization
    public LoginResponse getLoginData(String responseString){
        gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(responseString, LoginResponse.class);
        return loginResponse;
    }

}