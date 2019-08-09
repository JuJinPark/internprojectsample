package com.gabia.internproject.service;

import com.gabia.internproject.data.entity.user;

import java.util.List;

public interface UserService {

    public List<user> selectUserList();

    public user selectUser(int id);

    public void insertUser(user user);

    public void updateUser(user user);

    public void deleteUser(int id);
}
