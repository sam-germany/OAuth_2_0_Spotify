package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlayListApi22;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests {

    public Playlist getPlayList22(String name, String description, boolean _public) {

        return  Playlist.builder()
                              .name(name)
                              .description(description)
                              ._public(_public)
                              .build();
    }

    public void assertPlayListEqual22(Playlist respPlayList, Playlist req22){
        assertThat(respPlayList.getName(), equalTo(req22.getName()));
        assertThat(respPlayList.getDescription(), equalTo(req22.getDescription()));
        assertThat(respPlayList.get_public(), equalTo(req22.get_public()));
    }

    public void assertStatusCode33(int actualStatusCode, int expectedStatusCode){
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }

    public void assertError33(ErrorRoot responseErr, int expectedStatusCode, String expectedMsg) {
        assertThat(responseErr.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseErr.getError().getMessage(), equalTo(expectedMsg));
    }



    @Test
    public void  create_a_PlayList(){

        Playlist req22 = getPlayList22("New PlayList", "New playlist description", false);

        Response response = PlayListApi22.post22(req22);

        assertStatusCode33(response.statusCode(), 200);

        Playlist respPlayList = response.as(Playlist.class);

        assertPlayListEqual22(respPlayList, req22);

    }


    @Test
    public void  get_a_PlayList(){

        Playlist req22 = getPlayList22("New PlayList","New playlist description", false );

       Response response = PlayListApi22.get22("2npR5ljEsYDuzrbNzoDeIH");
        assertStatusCode33(response.statusCode(), 200);

       Playlist respPlayList = response.as(Playlist.class);

        assertPlayListEqual22(respPlayList, req22);
    }

    @Test
    public void  update_a_PlayList(){

        Playlist req22 = getPlayList22("New PlayList", "New playlist description", false);

        Response response = PlayListApi22.put22(req22,"2npR5ljEsYDuzrbNzoDeIH");

        assertStatusCode33(response.statusCode(), 200);

      }


    @Test
    public void  ERROR_case_should_not_be_able_to_create_playlist_without_name(){

        Playlist req22 = getPlayList22("", "New playlist description", false);

        Response response = PlayListApi22.post22(req22);

        assertStatusCode33(response.statusCode(), 200);

        ErrorRoot error =  response.as(ErrorRoot.class);

        assertError33(error, 400, "Missing required field: name");
     }


    @Test
    public void  negative_case_should_not_be_able_to_create_playlist_with_expired_Token(){
         String invalid_token = "12345";

        Playlist req22 = Playlist.builder()
                                       .name("New PlayList")
                                       .description("New playlist description")
                                       ._public(false)
                                       .build();

        Response response = PlayListApi22.post22(invalid_token, req22);
        assertThat(response.statusCode(), equalTo(401));

        ErrorRoot error = response.as(ErrorRoot.class);

        assertError33(error, 400, "Invalid access token");
    }
}
