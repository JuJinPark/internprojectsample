package com.gabia.internproject.service.OAuth;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public abstract class ApiProvider {

    public abstract String getAccessTokenEndpoint();
    public String getRefreshTokenEndpoint() {
        return getAccessTokenEndpoint();
    }

    public abstract String getAuthorizationBaseUrl();
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
                                      Map<String, String> additionalParams) throws UnsupportedEncodingException {
        final ParameterList parameters = new ParameterList(additionalParams);
        parameters.add(OAuthConstants.RESPONSE_TYPE, responseType);
        parameters.add(OAuthConstants.CLIENT_ID, apiKey);

        if (callback != null) {
            parameters.add(OAuthConstants.REDIRECT_URI, callback);
        }

        if (scope != null) {
            parameters.add(OAuthConstants.SCOPE, scope);
        }

        if (state != null) {
            parameters.add(OAuthConstants.STATE, state);
        }

        return parameters.appendTo(getAuthorizationBaseUrl());
    }

}
