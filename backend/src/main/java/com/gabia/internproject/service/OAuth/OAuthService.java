package com.gabia.internproject.service.OAuth;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class OAuthService {
    private final String apiKey;
    private final String apiSecret;
    private final ApiProvider apiProvider;
    private final String callback;
    private final String responseType;
    private final String scope;
    private final Map<String, String> additionalParams;
    private final Map<OAuthConstants, String> keyParmas=new HashMap<>();

    public static class Builder {

        private final String apiKey;

        private  String apiSecret;
        private  String callback;
        private  String scope;
        private  String responseType = "code";
        private  ApiProvider apiProvider;
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
        public Builder apiProvider(ApiProvider apiProvider) {
            this.apiProvider = apiProvider;
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
        keyParmas.put(OAuthConstants.CLIENT_ID,apiKey);

        apiSecret     = builder.apiSecret;
        keyParmas.put(OAuthConstants.CLIENT_SECRET,apiSecret);

        callback     = builder.callback;
        keyParmas.put(OAuthConstants.REDIRECT_URI,callback);

        scope          = builder.scope;
        keyParmas.put(OAuthConstants.SCOPE,scope);

        responseType       = builder.responseType;
        keyParmas.put(OAuthConstants.RESPONSE_TYPE,responseType);

        apiProvider = builder.apiProvider;
        additionalParams=builder.additionalParams;

    }


    public ApiProvider getApi() {
        return apiProvider;
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

    public Token getAccessToken(String code) throws UnsupportedEncodingException {

        OAuthRequest request = createAccessTokenRequest(code);
        return sendAccessTokenRequest(request);
    }

    private Token sendAccessTokenRequest(OAuthRequest request) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String,Object> response=restTemplate.exchange(request.getUrl(),request.getMethod(),request.getHttpEntity(),Map.class).getBody();
        return convertMaptoToken(response);

    }

    private Token convertMaptoToken(Map response){
        if(response.get("access_token")==null){
            response=(Map<String,Object>)response.get("data");
        }

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       return mapper.convertValue(response, Token.class);
    }

    private OAuthRequest createAccessTokenRequest(String code) throws UnsupportedEncodingException {
        keyParmas.put(getApi().getAuthCodeParameterName(),code);
        OAuthRequest request=new OAuthRequest(apiProvider.getAccessTokenEndpoint(),apiProvider.getAccessTokenVerb());
                    request.setHeaders(apiProvider.getRequiredHeadersForAccessToken(),keyParmas,additionalParams,apiProvider.getDefaultValue());
                    request.setBody(apiProvider.getRequiredBodyParametersForAccessToken(),keyParmas,additionalParams,apiProvider.getDefaultValue());
                    request.setParameters(apiProvider.getRequiredParametersForAccessToken(),keyParmas,additionalParams,apiProvider.getDefaultValue());
                    request.setUrlWithParamters();
                    request.setHttpEntity();

         return request;
    }




    public String getAuthorizationUrl() throws UnsupportedEncodingException {
        return getAuthorizationUrl(null);
    }

    public String getAuthorizationUrl(String state) throws UnsupportedEncodingException {

        return apiProvider.getAuthorizationUrl(getResponseType(), getApiKey(), getCallback(), getScope(), state, getAdditionalParams());
    }

//    public String getAuthorizationUrl(Map<String, String> additionalParams) throws UnsupportedEncodingException {
//
//        return getAuthorizationUrl(null,additionalParams);
//    }

}
