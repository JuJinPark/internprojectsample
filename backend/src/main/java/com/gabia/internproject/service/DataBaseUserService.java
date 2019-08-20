package com.gabia.internproject.service;

import com.gabia.internproject.data.entity.user;
import com.gabia.internproject.data.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class DataBaseUserService implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<user> selectUserList() {
        return ImmutableList.copyOf(userRepository.findAll());
        userRepository.
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public user selectUser(int id) {
        return userRepository.findById(id).orElse(new user(0,"없음","없음"));
    }

    @Override
    public void insertUser(user user) {

    }

    @Override
    public void updateUser(user user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
