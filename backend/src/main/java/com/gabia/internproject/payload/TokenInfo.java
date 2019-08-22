package com.gabia.internproject.payload;

import com.github.scribejava.core.model.OAuth2AccessToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TokenInfo {
    private String accessToken;


    private String tokenType;


    private Integer expiresIn;


    private String refreshToken;


    private String scope;

    TokenInfo(OAuth2AccessToken token){

    }



}
