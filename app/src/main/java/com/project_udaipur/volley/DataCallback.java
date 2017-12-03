package com.project_udaipur.volley;

import org.json.JSONArray;
import org.json.JSONObject;


public abstract class DataCallback {
    public void onError(String message, Integer... statuscode) {

    }

    public void onResponse(JSONArray response) {

    }

    public void onError(String message){}

    public void onResponse(JSONObject response) {

    }
}
