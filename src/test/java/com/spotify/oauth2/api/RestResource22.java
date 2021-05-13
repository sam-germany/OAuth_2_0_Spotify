package com.spotify.oauth2.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

public class RestResource22 {


    public static Response post22(String path, String token, Object request22) {

        return RestAssured.given(SpecBuilder22.getRequestSpec33())
                              .body(request22)
                              .header("Authorization", "Bearer "+ token)
                          .when()
                              .post(path)
                          .then().spec(SpecBuilder22.getResponseSpec33())
                              .extract()
                              .response();
    }

    public static Response get22(String path, String token) {

        return RestAssured.given(SpecBuilder22.getRequestSpec33())
                              .header("Authorization", "Bearer "+ token)
                          .when()
                              .get(path)
                          .then().spec(SpecBuilder22.getResponseSpec33())
                              .extract()
                              .response();
    }


    public static  Response put22(String path, String token, Object request22) {

        return   RestAssured.given(SpecBuilder22.getRequestSpec33())
                .body(request22)
                .header("Authorization", "Bearer "+ token)
                .when()
                .put(path)
                .then().spec(SpecBuilder22.getResponseSpec33())
                .extract()
                .response();
    }

    public static Response postAccount22(HashMap<String, String> formParams) {

         return  RestAssured.given(SpecBuilder22.getAccountRequestSpec())
                            .when()
                                 .post("/api/token")
                            .then().spec(SpecBuilder22.getResponseSpec33())
                                 .extract()
                                 .response();

    }

}
