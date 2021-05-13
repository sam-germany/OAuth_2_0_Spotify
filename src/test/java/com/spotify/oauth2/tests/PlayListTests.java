package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlayListApi22;
import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListTests {


    @Test
    public void  create_a_PlayList(){

        Playlist req22 = new Playlist().setName("New PlayList")
                                       .setDescription("New playlist description")
                                       .setPublic(false);

        Response response = PlayListApi22.post22(req22);
        assertThat(response.statusCode(), equalTo(200));

        Playlist respPlayList = response.as(Playlist.class);

        assertThat(respPlayList.getName(), equalTo(req22.getName()));
        assertThat(respPlayList.getDescription(), equalTo(req22.getDescription()));
        assertThat(respPlayList.getPublic(), equalTo(req22.getPublic()));
    }


    @Test
    public void  get_a_PlayList(){

        Playlist req22 = new Playlist().setName("New PlayList")
                .setDescription("New playlist description")
                .setPublic(false);

       Response response = PlayListApi22.get22("2npR5ljEsYDuzrbNzoDeIH");
       assertThat(response.statusCode(), equalTo(200));

       Playlist respPlayList = response.as(Playlist.class);

        assertThat(respPlayList.getName(), equalTo(req22.getName()));
        assertThat(respPlayList.getDescription(), equalTo(req22.getDescription()));
        assertThat(respPlayList.getPublic(), equalTo(req22.getPublic()));
    }

    @Test
    public void  update_a_PlayList(){

        Playlist req22 = new Playlist().setName("New PlayList")
                                       .setDescription("New playlist description")
                                       .setPublic(false);

        Response response = PlayListApi22.put22(req22,"2npR5ljEsYDuzrbNzoDeIH");
        assertThat(response.statusCode(), equalTo(200));

      }


    @Test
    public void  ERROR_case_should_not_be_able_to_create_playlist_without_name(){

        Playlist req22 = new Playlist().setName("")
                                       .setDescription("New playlist description")
                                       .setPublic(false);

        Response response = PlayListApi22.post22(req22);
        assertThat(response.statusCode(), equalTo(200));

        ErrorRoot error =  response.as(ErrorRoot.class);


    assertThat(error.getError().getStatus(), equalTo(400));
    assertThat(error.getError().getMessage(), equalTo("Missing required field: name"));

     }


    @Test
    public void  negative_case_should_not_be_able_to_create_playlist_with_expired_Token(){
         String invalid_token = "12345";

        Playlist req22 = new Playlist().setName("New PlayList")
                                       .setDescription("New playlist description")
                                       .setPublic(false);

        Response response = PlayListApi22.post22(invalid_token, req22);
        assertThat(response.statusCode(), equalTo(401));

        ErrorRoot error = response.as(ErrorRoot.class);

        assertThat(error.getError().getStatus(), equalTo(400));
        assertThat(error.getError().getMessage(), equalTo("Invalid access token"));
    }
}
