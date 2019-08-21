package com.gabia.internproject.service.OAuth;

public enum OAuthConstants {
     ACCESS_TOKEN("access_token"),
     CLIENT_ID("client_id"),
     CLIENT_SECRET("client_secret"),
     REDIRECT_URI("redirect_uri"),
     CODE("code"),
     REFRESH_TOKEN("refresh_token"),
     GRANT_TYPE("grant_type"),
     AUTHORIZATION_CODE ("authorization_code"),
     CLIENT_CREDENTIALS ("client_credentials"),
     STATE ("state"),
     USERNAME ("username"),
     PASSWORD ("password"),
     RESPONSE_TYPE ("response_type"),
     RESPONSE_TYPE_CODE ("code"),
     SCOPE ("scope");

    private String text;

    OAuthConstants(String text) {
        this.text = text;
    }
    public String getText() {
        return this.text;
    }

}
