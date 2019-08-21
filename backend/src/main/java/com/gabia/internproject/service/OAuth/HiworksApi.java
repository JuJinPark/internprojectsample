package com.gabia.internproject.service.OAuth;

public class HiworksApi extends ApiProvider {
    protected HiworksApi() {
    }


        private static final HiworksApi INSTANCE = new HiworksApi();


    public static HiworksApi instance() {
        return INSTANCE;
    }
    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.hiworks.com/open/auth/authform";
    }

    @Override
    public String getAuthorizationBaseUrl() {
        return "https://accounts.google.com/o/oauth2/auth";
    }
}
