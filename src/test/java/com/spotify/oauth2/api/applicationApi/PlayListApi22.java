package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource22;
import com.spotify.oauth2.api.TokenManager22;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

public class PlayListApi22 {


  public static Response post22(Playlist requestPlaylist) {

return RestResource22.post22("/users/yrb4a05403h1y506v2hcjat7j/playlists", TokenManager22.getToken22(), requestPlaylist);
    }



    public static Response post22(String token, Playlist requestPlaylist) {

   return  RestResource22.post22("/users/yrb4a05403h1y506v2hcjat7j/playlists", TokenManager22.getToken22(), requestPlaylist);
    }


    public static  Response get22(String playListId) {

        return RestResource22.get22("/playlists/"+ playListId, TokenManager22.getToken22());
    }



    public static  Response put22(Playlist requestPlayList, String playListId) {

    return RestResource22.put22("/playlists/" + playListId, TokenManager22.getToken22(), requestPlayList);
    }

}
