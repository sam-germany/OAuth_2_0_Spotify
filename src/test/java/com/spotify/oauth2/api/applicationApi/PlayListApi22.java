package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource22;
import com.spotify.oauth2.pojo.Playlist;
import io.restassured.response.Response;

public class PlayListApi22 {

    static String   access_token = "BQBWrqSqT0_AfQt3Lu_7wd56w8hcQNPw2OrtkCic1kN4Y7gK4HoPvPhTg371nLTO46lHMs6-6UCn6iX4ZqNUgnM8_yLpM8Ee4CNwhXNVVUdL6p9gly4Lg4SQuC802ti-JOTh-nEiNfyJVeTI9gZMbkQklG6goKadgF2OutD19LDX_BW3coY9K-4ts3Pcp54aO_6B5RHerfvQAciYvRUcFDVRrE0yc7QG-ZunrpuDH5sn";

  public static Response post22(Playlist requestPlaylist) {

   return RestResource22.post22("/users/yrb4a05403h1y506v2hcjat7j/playlists", access_token, requestPlaylist);
    }



    public static Response post22(String token, Playlist requestPlaylist) {

   return  RestResource22.post22("/users/yrb4a05403h1y506v2hcjat7j/playlists", token, requestPlaylist);
    }


    public static  Response get22(String playListId) {

        return RestResource22.get22("/playlists/"+ playListId, access_token);
    }



    public static  Response put22(Playlist requestPlayList, String playListId) {

    return RestResource22.put22("/playlists/" + playListId, access_token, requestPlayList);
    }

}
