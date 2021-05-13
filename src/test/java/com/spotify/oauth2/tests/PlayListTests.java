package com.spotify.oauth2.tests;

import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "BQBWrqSqT0_AfQt3Lu_7wd56w8hcQNPw2OrtkCic1kN4Y7gK4HoPvPhTg371nLTO46lHMs6-6UCn6iX4ZqNUgnM8_yLpM8Ee4CNwhXNVVUdL6p9gly4Lg4SQuC802ti-JOTh-nEiNfyJVeTI9gZMbkQklG6goKadgF2OutD19LDX_BW3coY9K-4ts3Pcp54aO_6B5RHerfvQAciYvRUcFDVRrE0yc7QG-ZunrpuDH5sn";

    @BeforeClass
    public void beforeClass() {
    RequestSpecBuilder reqSBuilder = new RequestSpecBuilder()
                                                        .setBaseUri("https://api.spotify.com")
                                                        .setBasePath("/v1")
                                                        .addHeader("Authorization", "Bearer "+ access_token)
                                                        .setContentType(ContentType.JSON)
                                                        .log(LogDetail.ALL);

              requestSpecification = reqSBuilder.build();

    ResponseSpecBuilder   resSBuilder = new ResponseSpecBuilder()
                                                         .log(LogDetail.ALL);

              responseSpecification = resSBuilder.build();
    }


    @Test
    public void  create_a_PlayList(){

        Playlist req22 = new Playlist();
        req22.setName("New PlayList");
        req22.setDescription("New playlist description");
        req22.setPublic(false);

    Playlist response = RestAssured.given(requestSpecification)
                                       .body(req22)
                                   .when()
                                       .post("/users/yrb4a05403h1y506v2hcjat7j/playlists")
                                   .then().spec(responseSpecification)
                                       .assertThat()
                                       .statusCode(201)
                                       .extract()
                                       .response()
                                       .as(Playlist.class);

        assertThat(response.getName(), equalTo(req22.getName()));
        assertThat(response.getDescription(), equalTo(req22.getDescription()));
        assertThat(response.getPublic(), equalTo(req22.getPublic()));
    }


    @Test
    public void  get_a_PlayList(){

        Playlist req22 = new Playlist();
        req22.setName("New PlayList");
        req22.setDescription("New playlist description");
        req22.setPublic(false);

    Playlist response = RestAssured.given(requestSpecification)
                                   .when()
                                        .post("/playlists/2npR5ljEsYDuzrbNzoDeIH")
                                   .then().spec(responseSpecification)
                                        .assertThat()
                                        .statusCode(200)
                                        .extract()
                                        .response()
                                        .as(Playlist.class);

        assertThat(response.getName(), equalTo(req22.getName()));
        assertThat(response.getDescription(), equalTo(req22.getDescription()));
        assertThat(response.getPublic(), equalTo(req22.getPublic()));
    }

    @Test
    public void  update_a_PlayList(){

        Playlist req22 = new Playlist();
        req22.setName("New PlayList");
        req22.setDescription("New playlist description");
        req22.setPublic(false);

    Playlist response = RestAssured.given(requestSpecification)
                                   .when()
                                        .put("/playlists/2npR5ljEsYDuzrbNzoDeIH")
                                   .then().spec(responseSpecification)
                                        .assertThat()
                                        .statusCode(200)
                                        .extract()
                                        .response()
                                        .as(Playlist.class);

        assertThat(response.getName(), equalTo(req22.getName()));
        assertThat(response.getDescription(), equalTo(req22.getDescription()));
        assertThat(response.getPublic(), equalTo(req22.getPublic()));
      }

    @Test
    public void  ERROR_case_should_not_be_able_to_create_playlist_without_name(){

        Playlist req22 = new Playlist();
        req22.setName("");
        req22.setDescription("New playlist description");
        req22.setPublic(false);

    ErrorRoot error = RestAssured.given(requestSpecification)
                                      .body(req22)
                                 .when()
                                      .post("/users/yrb4a05403h1y506v2hcjat7j/playlists")
                                 .then().spec(responseSpecification)
                                      .assertThat()
                                      .statusCode(400)
                                      .extract()
                                      .as(ErrorRoot.class);

    assertThat(error.getError().getStatus(), equalTo(400));
    assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

     }


    @Test
    public void  negative_case_should_not_be_able_to_create_playlist_with_expired_Token(){
        String payload = "{\n" +
                         "  \"name\": \"New Playlist\",\n" +
                         "  \"description\": \"New playlist description\",\n" +
                         "  \"public\": false\n" +
                         "}";

        RestAssured.given()
                       .baseUri("https://api.spotify.com")
                       .basePath("/v1")
                       .header("Authorization", "Bearer " + "1234")
                       .contentType(ContentType.JSON)
                       .log().all()
                       .body(payload)
                   .when()
                       .post("/users/yrb4a05403h1y506v2hcjat7j/playlists")
                   .then().spec(responseSpecification)
                       .assertThat()
                       .statusCode(401)
                       .contentType(ContentType.JSON)
                       .body("error.status", equalTo(401),
              "error.message", equalTo("Invalid access token"));
    }
}
