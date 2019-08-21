package com.gabia.internproject.service.OAuth;

public class OAuthServiceFactory {
    public static OAuthService getService(String serviceProvider){
        if("google".equals(serviceProvider)){
           return new OAuthService.Builder("898505969152-hkcf91qo3gkj0jfjtshni5duqbe0uqc0.apps.googleusercontent.com")
                   .apiSecret("6RBgEZXwPTXijKHmvgqfKGDo")
                   .callback("http://localhost:8080/oauth2/callback/google")
                   .scope("email")
                   .apiProvider(GoogleApi.instance())
                   .build();
        }else if("hiworks".equals(serviceProvider)){
            return new OAuthService.Builder("898505969152-hkcf91qo3gkj0jfjtshni5duqbe0uqc0.apps.googleusercontent.com")
                    .apiSecret("6RBgEZXwPTXijKHmvgqfKGDo")
                    .callback("http://localhost:8080/oauth2/callback/google")
                    .apiProvider(HiworksApi.instance())
                    .build();
        }else if("github".equals(serviceProvider)){
            return new OAuthService.Builder("3d69aa3156aa86f6eb84")
                    .apiSecret("9b1dc3460068922506d5c27b2d7155bd31f130c8")
                    .callback("http://localhost:8080/oauth2/callback/github")
                    .apiProvider(GitHubApi.instance())
                    .build();
        }
        return null;

    }
}
