package com.gabia.internproject.controller;

import com.gabia.internproject.data.entity.user;
import com.gabia.internproject.payload.ApiResponse;
import com.gabia.internproject.payload.AuthResponse;
import com.gabia.internproject.service.OAuth.OAuthService;
import com.gabia.internproject.service.OAuth.OAuthServiceFactory;
import com.gabia.internproject.util.CookieUtils;
import com.gabia.internproject.util.TokenProvider;
import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

@RestController
public class AuthController {



    @RequestMapping("/oauth2/{serviceProvider}")
    @ApiOperation(value = "인증요청", notes = "인증요청")
     public ResponseEntity<?> getToken(HttpServletResponse response,@PathVariable String serviceProvider) throws UnsupportedEncodingException {



//                service = new ServiceBuilder("898505969152-hkcf91qo3gkj0jfjtshni5duqbe0uqc0.apps.googleusercontent.com")
//                .apiSecret("6RBgEZXwPTXijKHmvgqfKGDo")
//                .callback("http://localhost:8080/oauth2/callback/google")
//                .scope("email")
//                .build(GoogleApi20.instance());
//
//        String authUrl = service.getAuthorizationUrl();
//
        OAuth20Service service2=new ServiceBuilder("3d69aa3156aa86f6eb84")
                .apiSecret("9b1dc3460068922506d5c27b2d7155bd31f130c8")
                .callback("http://localhost:8080/oauth2/callback/github")
//                .scope("email")
              .build(GitHubApi.instance());
        String authUrl2 = service2.getAuthorizationUrl();


//        OAuthService service= OAuthServiceFactory.getService(serviceProvider);
//        if(service==null){
//            return ResponseEntity.ok(new ApiResponse(false,"service not found"));
//        }

        return ResponseEntity.ok(new ApiResponse(true,service2.getAuthorizationUrl()));


    }

    @RequestMapping("/oauth2/callback/{serviceProvider}")
    @ApiOperation(value = "인증요청", notes = "인증요청")
    public ResponseEntity<?> getToken(@PathVariable String serviceProvider,@RequestParam String code) throws InterruptedException, ExecutionException, IOException {
        OAuth20Service service=new ServiceBuilder("3d69aa3156aa86f6eb84")
                .apiSecret("9b1dc3460068922506d5c27b2d7155bd31f130c8")
                .callback("http://localhost:8080/oauth2/callback/github")
                .build(GitHubApi.instance());

        OAuth2AccessToken accessToken = service.getAccessToken(code);
        OAuthRequest request = new OAuthRequest(Verb.GET,
                "https://api.github.com/user");

        service.signRequest(accessToken,request);

        Response response = service.execute(request);


        return ResponseEntity.ok(new ApiResponse(true,response.getBody()+"--"+request.getHeaders()));


    }
}
