package com.example.cookapp;

import com.example.cookapp.api.APIService;
import com.example.cookapp.api.Client;

public class Utils {
    public static APIService getApi(){
        return Client.getClient().create(APIService.class);
    }
}
