package com.gabia.internproject.controller;

import com.gabia.internproject.data.entity.user;
import com.gabia.internproject.service.UserService;
import com.gabia.internproject.vo.ResponseVO;
import com.gabia.internproject.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/api/users")
@Api(value = "UserController")

public class UserController {
    @Autowired
    UserService userService;

    private HttpSession httpSession;
    private Object SessionConstants;

    public UserController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/me")
    public Map<String, Object> me(){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("profile", httpSession.getAttribute("LOGIN_USER"));
        return response;
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {


        return principal;
    }

//    @GetMapping
//    @ApiOperation(value = "사용자 목록 조회", notes = "전체 사용자 목록을 조회하는 API.")
//    public ResponseVO<?> getUsers() {
//        ResponseVO<List<user>> resp = new ResponseVO<>();
//        System.out.println(userService.getClass()+"---");
//        resp.setResponse(userService.selectUserList());
//        return resp;
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation(value = "사용자 목록 조회", notes = "특정사용자 목록 조회")
//    public ResponseVO<?> getUser(@PathVariable int id) {
//        ResponseVO<user> resp = new ResponseVO<>();
//        System.out.println(userService.getClass()+"-");
//        resp.setResponse(userService.selectUser(id));
//        return resp;
//    }

}