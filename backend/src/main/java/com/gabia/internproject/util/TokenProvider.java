package com.gabia.internproject.util;


import com.gabia.internproject.data.entity.user;
import com.gabia.internproject.vo.UserVO;
import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    @Value("${app.auth.tokenSecret}")
    private String tokenSecret;
    @Value("${app.auth.tokenExpirationMsec}")
    private long tokenExpirationMsec;

    public String createToken(user user) {


        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenExpirationMsec);

        return Jwts.builder()
                .setSubject(Long.toString(user.getUser_no()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .claim("name",user.getName())
                .claim("phone",user.getPhone())
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public Object getClaims(String token,String name) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.get(name);
    }



    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
           // logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
          //  logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
          //  logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
          //  logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
