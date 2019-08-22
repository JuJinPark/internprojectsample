package com.gabia.internproject.controller;

import com.gabia.internproject.data.entity.user;
import com.gabia.internproject.payload.ApiResponse;
import com.gabia.internproject.payload.AuthResponse;
import com.gabia.internproject.service.OAuth.*;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

@RestController
public class AuthController {



    @RequestMapping("/login/{serviceProvider}")
    @ApiOperation(value = "인증요청", notes = "인증요청")
     public void getToken(HttpServletResponse response,@PathVariable String serviceProvider) throws UnsupportedEncodingException {



//                service = new ServiceBuilder("898505969152-hkcf91qo3gkj0jfjtshni5duqbe0uqc0.apps.googleusercontent.com")
//                .apiSecret("6RBgEZXwPTXijKHmvgqfKGDo")
//                .callback("http://localhost:8080/oauth2/callback/google")
//                .scope("email")
//                .build(GoogleApi20.instance());
//
//        String authUrl = service.getAuthorizationUrl();
//
//        OAuth20Service service2=new ServiceBuilder("3d69aa3156aa86f6eb84")
//                .apiSecret("9b1dc3460068922506d5c27b2d7155bd31f130c8")
//                .callback("http://localhost:8080/oauth2/callback/github")
////                .scope("email")
//              .build(GitHubApi.instance());
//        String authUrl2 = service2.getAuthorizationUrl();
//

        OAuthService service= OAuthServiceFactory.getService(serviceProvider);

System.out.println(service.getAuthorizationUrl());
        response.setHeader("Location", service.getAuthorizationUrl());
        response.setStatus(302);
//        if(service==null){
//            return ResponseEntity.ok(new ApiResponse(false,"service not found"));
//        }
//
//
//      return ResponseEntity.ok(new ApiResponse(true,service.getAuthorizationUrl()));


    }

    @GetMapping(value = {"/login/callback/{serviceProvider}"},params = {"code"})
    @ApiOperation(value = "인증요청", notes = "인증요청")
    public ResponseEntity<?> apiDefaultCallback(@PathVariable String serviceProvider,@RequestParam String code) throws InterruptedException, ExecutionException, IOException {
//        OAuth20Service service=new ServiceBuilder("3d69aa3156aa86f6eb84")
//                .apiSecret("9b1dc3460068922506d5c27b2d7155bd31f130c8")
//                .callback("http://localhost:8080/oauth2/callback/github")
//                .build(GitHubApi.instance());

//        OAuthService service= OAuthServiceFactory.getService(serviceProvider);
//       OAuth2AccessToken accessToken = service.getAccessToken(code);

        RestTemplate restTemplate = new RestTemplate();


        Token ss=null;
        String url="";
        if("google".equals(serviceProvider)){
            ParameterList parameters = new ParameterList();
            parameters.add(OAuthConstants.CLIENT_ID,"898505969152-hkcf91qo3gkj0jfjtshni5duqbe0uqc0.apps.googleusercontent.com");
            parameters.add(OAuthConstants.CLIENT_SECRET,"6RBgEZXwPTXijKHmvgqfKGDo");
            parameters.add(OAuthConstants.CODE,code);
            parameters.add(OAuthConstants.GRANT_TYPE,"authorization_code");
            parameters.add(OAuthConstants.ACCESS_TYPE,"offline");
            parameters.add(OAuthConstants.REDIRECT_URI,"http://localhost:8080/login/callback/google");
            url=parameters.appendToWithoutEncoding("https://www.googleapis.com/oauth2/v4/token");

            System.out.println(url);

            ss=restTemplate.exchange(url,HttpMethod.POST,null,new ParameterizedTypeReference<Token>() {}).getBody();

        }else if("github".equals(serviceProvider)){
            ParameterList parameters = new ParameterList();
            parameters.add(OAuthConstants.CLIENT_ID,"3d69aa3156aa86f6eb84");
            parameters.add(OAuthConstants.CLIENT_SECRET,"9b1dc3460068922506d5c27b2d7155bd31f130c8");
            parameters.add(OAuthConstants.CODE,code);
            url=parameters.appendToWithoutEncoding("https://github.com/login/oauth/access_token");


            ss=restTemplate.exchange(url,HttpMethod.POST,null,new ParameterizedTypeReference<Token>() {}).getBody();


        }else {
            url="https://api.hiworks.com/open/auth/accesstoken";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add(OAuthConstants.AUTH_CODE.getText(),code);
            map.add(OAuthConstants.CLIENT_ID.getText(),"f3xor1auc7d45n1zby6uapdggde9lqu65d5dee1e48d234.85217709.open.apps");
            map.add(OAuthConstants.CLIENT_SECRET.getText(),"fpz4zdkdkdi60rp0tf9rp7ivhhvwjl4y");
            map.add(OAuthConstants.GRANT_TYPE.getText(),"authorization_code");
            map.add(OAuthConstants.ACCESS_TYPE.getText(),"offline");

            HttpEntity<?> httpEntity = new HttpEntity<>(map,headers);

            ss=restTemplate.exchange(url,HttpMethod.POST,httpEntity,new ParameterizedTypeReference<Token>() {}).getBody();
        }
        System.out.println(url);
System.out.println(ss.getAccessToken()+"access_token");
        System.out.println(ss.getRefreshToken()+"refresh_token");
        System.out.println(ss.getExpiresIn()+"exprie_time");


//        OAuthRequest request = new OAuthRequest(Verb.GET,
//                "https://api.github.com/user");
//
//        service.signRequest(accessToken,request);
//
//        Response response = service.execute(request);


        return ResponseEntity.ok(code);


    }
    @GetMapping(value = {"/login/callback/{serviceProvider}"},params = {"auth_code"})
    @ApiOperation(value = "하이웍스 콜백", notes = "code 파라미터가 하이웍스만 다르다")
    public ResponseEntity<?> otherApiCallback(@PathVariable String serviceProvider,@RequestParam String auth_code) throws InterruptedException, ExecutionException, IOException {

       return apiDefaultCallback(serviceProvider,auth_code);

    }
}
