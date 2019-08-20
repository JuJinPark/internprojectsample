package com.gabia.internproject.controller;

import com.gabia.internproject.data.entity.user;
import com.gabia.internproject.payload.AuthResponse;
import com.gabia.internproject.util.CookieUtils;
import com.gabia.internproject.util.TokenProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;

@RestController
public class TokenController {

    @Autowired
    private TokenProvider tokenProvider;

    @RequestMapping("/token")
    @ApiOperation(value = "jwt토큰 조회", notes = "jwt토큰 조회")
     public ResponseEntity<?> getToken(HttpServletResponse response) {

        user user= new user(27,"나다","222");
        String token = tokenProvider.createToken(user);
        CookieUtils.addCookie(response,"accesstoken",token,2 * 60 * 60);
        return ResponseEntity.ok(new AuthResponse(token));


    }
}
