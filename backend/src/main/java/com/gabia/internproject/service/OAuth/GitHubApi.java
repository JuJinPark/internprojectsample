package com.gabia.internproject.service.OAuth;

public class GitHubApi extends ApiProvider {
    protected GitHubApi() {
    }


        private static final GitHubApi INSTANCE = new GitHubApi();


    public static GitHubApi instance() {
        return INSTANCE;
    }
    @Override
    public String getAccessTokenEndpoint() {
        return "https://github.com/login/oauth/access_token";
    }

    @Override
    public String getAuthorizationBaseUrl() {
        return  "https://github.com/login/oauth/authorize";
    }
}
