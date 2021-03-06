package com.gabia.internproject.service;

import com.gabia.internproject.data.entity.user;
import com.gabia.internproject.data.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


public class HiworksUserService implements UserService {

    List<user> userRepository;

    HiworksUserService(){
        userRepository=new ArrayList<>();
        userRepository.add(new user(1,"api1","1111"));
        userRepository.add(new user(2,"api2","2222"));
        userRepository.add(new user(3,"api3","3333"));
        userRepository.add(new user(4,"api4","4444"));
    }

    @Override

    public List<user> selectUserList() {
        return userRepository;
    }

    @Override

    public user selectUser(int id) {

        for(user individual:userRepository){
            if(individual.getUser_no()==id){
                return individual;
            }
        }

        return new user(0,"없음데이터베이스","");
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
