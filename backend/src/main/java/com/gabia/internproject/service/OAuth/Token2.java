package com.gabia.internproject.service.OAuth;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabia.internproject.util.StringValidationChecker;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("token")
public class Token2 implements Serializable {



    @JsonProperty("access_token")
        private String accessToken;
    @JsonProperty("token_type")
        private String tokenType;
    @Id
    @JsonProperty("expires_in")
        private Integer expiresIn;
    @JsonProperty("refresh_token")
        private String refreshToken;



        private String scope;

        public Token2(String accessToken) {
            this(accessToken, null);
        }

        public Token2(String accessToken, String rawResponse) {
            this(accessToken, null, null, null, null, rawResponse);
        }

        public Token2(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope,
                      String rawResponse) {

            StringValidationChecker.checkNotNull(accessToken, "access_token can't be null");
            this.accessToken = accessToken;
            this.tokenType = tokenType;
            this.expiresIn = expiresIn;
            this.refreshToken = refreshToken;
            this.scope = scope;
        }
        public Token2(){};

        public String getAccessToken() {
            return accessToken;
        }

        public String setAccessToken(String accessToken) {
        return this.accessToken=accessToken;
    }

        public void setScope(String scope){
            this.scope=scope;
        }
        public void setExpiresIn(Integer expiresIn) {this.expiresIn=expiresIn;}

        public String getTokenType() {
            return tokenType;
        }

        public Integer getExpiresIn() {
            return expiresIn;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public String getScope() {
            return scope;
        }





}
