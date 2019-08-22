package com.gabia.internproject.service.OAuth;

import com.gabia.internproject.util.StringValidationChecker;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {




        private String accessToken;

        private String tokenType;

        private Integer expiresIn;

        private String refreshToken;

        private String scope;

        public Token(String accessToken) {
            this(accessToken, null);
        }

        public Token(String accessToken, String rawResponse) {
            this(accessToken, null, null, null, null, rawResponse);
        }

        public Token(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope,
                                 String rawResponse) {

            StringValidationChecker.checkNotNull(accessToken, "access_token can't be null");
            this.accessToken = accessToken;
            this.tokenType = tokenType;
            this.expiresIn = expiresIn;
            this.refreshToken = refreshToken;
            this.scope = scope;
        }
        public Token(){};

        public String getAccessToken() {
            return accessToken;
        }

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
