package com.example.fariha.editprofile;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Fariha on 10-08-2017.
 */

public class AccessToken {
    //Git access Token
    @SerializedName("access_token")
    String accessToken;

    @SerializedName("token_type")
    String tokenType;

    public String getAccessToken(){
        return accessToken;
    }

    public  String getTokenType(){
        return tokenType;
    }
}
