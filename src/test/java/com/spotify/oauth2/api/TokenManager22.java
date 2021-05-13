package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

public class TokenManager22 {

    private static String access_token;
    static Instant expiry_time;

    public static String getToken22(){
        try{
            if(access_token == null || Instant.now().isAfter(expiry_time)) {
                Response response = renewToken22();
                access_token  = response.path("access_token");
                int expiry_time_in_sec = response.path("expires_in");
                expiry_time  = Instant.now().plusSeconds(expiry_time_in_sec - 300);  // he put this  "-300" sec because
            }else {                                                           // that we can get a new token 300 sec
                System.out.println("Token is good to use");           //before the old expires, so sometime it may be a case
            }                                                         // that Token got 1 sec to expire and after passing this
        }                                                           // validation and before our call it can expires so to
        catch (Exception e){                                         // avoid this situation he make a minus of   "-300"

            throw new RuntimeException("Failed to get token");
        }

         return  access_token;
    }


    private static Response renewToken22() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId22());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret22());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken22());
        formParams.put("grant_type", "refresh_token");

    Response response = RestResource22.postAccount22(formParams);

    if(response.statusCode() != 200) {
        throw  new RuntimeException("Renewing Token failed");
    }

    return  response.path("access");

    }
}
