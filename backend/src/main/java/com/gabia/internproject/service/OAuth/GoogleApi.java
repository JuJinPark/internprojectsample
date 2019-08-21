package com.gabia.internproject.service.OAuth;

public class GoogleApi extends ApiProvider {
    protected GoogleApi() {
    }


        private static final GoogleApi INSTANCE = new GoogleApi();


    public static GoogleApi instance() {
        return INSTANCE;
    }
    @Override
    public String getAccessTokenEndpoint() {
        return "https://www.googleapis.com/oauth2/v4/token";
    }

    @Override
    public String getAuthorizationBaseUrl() {
        return "https://accounts.google.com/o/oauth2/auth";
    }
}
