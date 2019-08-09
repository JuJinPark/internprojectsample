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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(value = "UserController")

public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "사용자 목록 조회", notes = "전체 사용자 목록을 조회하는 API.")
    public ResponseVO<?> getUsers() {
        ResponseVO<List<user>> resp = new ResponseVO<>();

//        List<UserVO> list = new ArrayList<>();
//        list.add(new UserVO("테스트", "010-1111-2222"));
//        list.add(new UserVO("테스터", "010-4444-2222"));
//        list.add(new UserVO("LSH", "010-9999-1111"));

        List<user> userssS=userService.selectUserList();
        List<user> userss =new ArrayList<>();
        userss.add(new user(1,"테스트", "010-1111-2222"));
        userss.add(new user(2,"테스터", "010-4444-2222"));
        userss.add(new user(3,"LSH", "010-9999-1111"));

        resp.setResponse(userssS);
        return resp;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "사용자 목록 조회", notes = "특정사용자 목록 조회")
    public ResponseVO<?> getUser(@PathVariable int id) {
        ResponseVO<user> resp = new ResponseVO<>();

//        List<UserVO> list = new ArrayList<>();
//        list.add(new UserVO("테스트", "010-1111-2222"));
//        list.add(new UserVO("테스터", "010-4444-2222"));
//        list.add(new UserVO("LSH", "010-9999-1111"));

        resp.setResponse(userService.selectUser(id));
        return resp;
    }

}