package oauth2.social.demo.social.hiworks;


import oauth2.social.demo.social.SocialService;
import oauth2.social.demo.social.google.GoogleUserDetails;
import oauth2.social.demo.social.userconnection.UserConnection;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HiworksOAuth2ClientAuthenticationProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {

    private ObjectMapper mapper = new ObjectMapper();
    private SocialService socialService;

    public HiworksOAuth2ClientAuthenticationProcessingFilter(SocialService socialService) {
        super("/login/hiworks");
        this.socialService = socialService;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // super.successfulAuthentication(request, response, chain, authResult);
        // Nearly a no-op, but if there is a ClientTokenServices then the token will now be stored

        final OAuth2AccessToken accessToken = restTemplate.getAccessToken();
        final OAuth2Authentication auth = (OAuth2Authentication) authResult;
        final Object details = auth.getUserAuthentication().getDetails();
//
//        final GoogleUserDetails userDetails = mapper.convertValue(details, GoogleUserDetails.class);
//        userDetails.setAccessToken(accessToken);
//        final UserConnection userConnection = UserConnection.valueOf(userDetails);

    // final UsernamePasswordAuthenticationToken authenticationToken = socialService.doAuthentication(userConnection);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

//        private UsernamePasswordAuthenticationToken setAuthenticationToken(Object user) {
//            return new UsernamePasswordAuthenticationToken(user, null, getAuthorities("ROLE_USER"));
//        }
        final UsernamePasswordAuthenticationToken authenticationToken=  new UsernamePasswordAuthenticationToken("s", null, authorities);


        super.successfulAuthentication(request, response, chain, authenticationToken);

    }

}
