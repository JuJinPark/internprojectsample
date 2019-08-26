package com.gabia.internproject.controller;

import com.gabia.internproject.service.OAuth.*;
//import com.gabia.internproject.service.OAuth.redis.TokenRedisRepository;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
public class AuthController {

    OAuthService service;

//    @Autowired
//    private TokenRedisRepository tokenRedisRepository;
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> values;

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
        service= OAuthServiceFactory.getService(serviceProvider);

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
    public ResponseEntity<?> apiDefaultCallback(HttpSession session, @PathVariable String serviceProvider, @RequestParam String code) throws InterruptedException, ExecutionException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        OAuth20Service service=new ServiceBuilder("3d69aa3156aa86f6eb84")
//                .apiSecret("9b1dc3460068922506d5c27b2d7155bd31f130c8")
//                .callback("http://localhost:8080/oauth2/callback/github")
//                .build(GitHubApi.instance());

//        OAuthService service= OAuthServiceFactory.getService(serviceProvider);
//       OAuth2AccessToken accessToken = service.getAccessToken(code);

//        RestTemplate restTemplate = new RestTemplate();
//
//
//        Map ss=null;
//        String url="";
//        if("google".equals(serviceProvider)){
//            ParameterList parameters = new ParameterList();
//            parameters.add(OAuthConstants.CLIENT_ID,"898505969152-hkcf91qo3gkj0jfjtshni5duqbe0uqc0.apps.googleusercontent.com");
//            parameters.add(OAuthConstants.CLIENT_SECRET,"6RBgEZXwPTXijKHmvgqfKGDo");
//            parameters.add(OAuthConstants.CODE,code);
//            parameters.add(OAuthConstants.GRANT_TYPE,"authorization_code");
//            //parameters.add(OAuthConstants.ACCESS_TYPE,"offline");
//            parameters.add(OAuthConstants.REDIRECT_URI,"http://localhost:8080/login/callback/google");
//          //  parameters.add(OAuthConstants.PROMPT,"consent");
//          //  parameters.add(OAuthConstants.PROMPT,"consent");
//            url=parameters.appendToWithoutEncoding("https://www.googleapis.com/oauth2/v4/token");
//
//            System.out.println(url);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
//           ss=restTemplate.exchange(url, HttpMethod.POST,httpEntity,Map.class).getBody();
//
//        }else if("github".equals(serviceProvider)){
//            ParameterList parameters = new ParameterList();
//            parameters.add(OAuthConstants.CLIENT_ID,"3d69aa3156aa86f6eb84");
//            parameters.add(OAuthConstants.CLIENT_SECRET,"9b1dc3460068922506d5c27b2d7155bd31f130c8");
//            parameters.add(OAuthConstants.CODE,code);
//            url=parameters.appendToWithoutEncoding("https://github.com/login/oauth/access_token");
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
//            ss=restTemplate.exchange(url,HttpMethod.POST,httpEntity,Map.class).getBody();
//
//
//        }else {
//            url="https://api.hiworks.com/open/auth/accesstoken";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//           headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//
//            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//            map.add(OAuthConstants.AUTH_CODE.getText(),code);
//            map.add(OAuthConstants.CLIENT_ID.getText(),"f3xor1auc7d45n1zby6uapdggde9lqu65d5dee1e48d234.85217709.open.apps");
//            map.add(OAuthConstants.CLIENT_SECRET.getText(),"fpz4zdkdkdi60rp0tf9rp7ivhhvwjl4y");
//            map.add(OAuthConstants.GRANT_TYPE.getText(),"authorization_code");
//            map.add(OAuthConstants.ACCESS_TYPE.getText(),"offline");
//
//            HttpEntity<?> httpEntity = new HttpEntity<>(map,headers);
//
//
//            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
//                    .loadTrustMaterial(null, acceptingTrustStrategy)
//                    .build();
//
//            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//            CloseableHttpClient httpClient = HttpClients.custom()
//                    .setSSLSocketFactory(csf)
//                    .build();
//
//            HttpComponentsClientHttpRequestFactory requestFactory =
//                    new HttpComponentsClientHttpRequestFactory();
//
//            requestFactory.setHttpClient(httpClient);
//       restTemplate = new RestTemplate(requestFactory);
//        ss=restTemplate.exchange(url,HttpMethod.POST,httpEntity,Map.class).getBody();
//        }
//
//
//        if(ss.get("access_token")==null){
//
//            ss=(Map<String,String>)ss.get("data");
//        }

//
//        ObjectMapper mapper =  new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // jackson's objectmapper
//        Token token = mapper.convertValue(ss, Token.class);







//        System.out.println(token.getAccessToken()+"access_token");
//        System.out.println(token.getRefreshToken()+"refresh_token");
//        System.out.println(token.getExpiresIn()+"exprie_time");
//        System.out.println(token.getScope()+"scope");

//
//        OAuthRequest request = new OAuthRequest(Verb.GET,
//                "https://api.github.com/user");
//
//        service.signRequest(accessToken,request);
//
//        Response response = service.execute(request);

       //Token2 token=redisRepositoryTestservice();
       Token token= redisTemplateTest(code);

        return ResponseEntity.ok(token);


    }
    @GetMapping(value = {"/login/callback/{serviceProvider}"},params = {"auth_code"})
    @ApiOperation(value = "하이웍스 콜백", notes = "code 파라미터가 하이웍스만 다르다")
    public ResponseEntity<?> otherApiCallback(HttpSession session,@PathVariable String serviceProvider,@RequestParam String auth_code) throws InterruptedException, ExecutionException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

       return apiDefaultCallback(session,serviceProvider,auth_code);

    }


//    public Token2 redisRepositoryTestservice(String code){
//        Date date=new Date();
//        SimpleDateFormat dayTime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String time=dayTime.format(date);
//
//        Token2 token=tokenRedisRepository.findById("512").orElse(null);
//
//        if(token==null){
//            token= service.getAccessToken(code);
//            if(token!=null){
//                token.setScope(time);
//                token.setExpiresIn(512);
//                tokenRedisRepository.save(token);
//
//            }
//
//
//        }
//        return token;
//    }

    public Token redisTemplateTest(String code) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnsupportedEncodingException {

        Date date=new Date();
        SimpleDateFormat dayTime=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=dayTime.format(date);



        Token token= (Token) values.get("518");

        if(token==null){
            token= service.getAccessToken(code);
            if(token!=null){
                token.setScope(time);
                values.set("518",token);


            }


        }
        return token;
    }

}
