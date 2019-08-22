package com.gabia.internproject.service.OAuth;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class OAuthService {
    private final String apiKey;
    private final String apiSecret;
    private final ApiProvider ApiProvider;
    private final String callback;
    private final String responseType;
    private final String scope;
    private final Map<String, String> additionalParams;

    public static class Builder {

        private final String apiKey;

        private  String apiSecret;
        private  String callback;
        private  String scope;
        private  String responseType = "code";
        private  ApiProvider ApiProvider;
        private  Map<String, String> additionalParams= new HashMap<>();

        public Builder(String apiKey) {
            this.apiKey = apiKey;

        }
        public Builder apiSecret(String apiSecret) {
            //emptycheck 로직
            this.apiSecret = apiSecret;
            return this;
        }
        public Builder callback(String callback) {
            //emptycheck 로직
            this.callback = callback;
            return this;
        }
        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }
        public Builder responseType(String responseType) {
            this.responseType = responseType;
            return this;
        }
        public Builder apiProvider(ApiProvider ApiProvider) {
            this.ApiProvider = ApiProvider;
            return this;
        }
        public Builder additionalParams(String key,String value) {

            this.additionalParams.put(key,value);
            return this;
        }
        public OAuthService build() {
            return new OAuthService(this);
        }

    }
    private OAuthService(Builder builder) {
        apiKey  = builder.apiKey;
        apiSecret     = builder.apiSecret;
        callback     = builder.callback;
        scope          = builder.scope;
        responseType       = builder.responseType;
        ApiProvider = builder.ApiProvider;
        additionalParams=builder.additionalParams;
    }


    public ApiProvider getApi() {
        return ApiProvider;
    }
    public String getResponseType() {
        return responseType;
    }
    public String getCallback() {
        return callback;
    }
    public String getApiKey() {
        return apiKey;
    }
    public String getScope() {
        return scope;
    }
    public Map<String, String> getAdditionalParams(){return additionalParams; }

    public OAuthAccessToken getAccessToken(String code)  {
        return null;
    }
    public String getAuthorizationUrl() throws UnsupportedEncodingException {
        return getAuthorizationUrl(null);
    }

    public String getAuthorizationUrl(String state) throws UnsupportedEncodingException {

        return ApiProvider.getAuthorizationUrl(getResponseType(), getApiKey(), getCallback(), getScope(), state, getAdditionalParams());
    }

//    public String getAuthorizationUrl(Map<String, String> additionalParams) throws UnsupportedEncodingException {
//
//        return getAuthorizationUrl(null,additionalParams);
//    }

}
