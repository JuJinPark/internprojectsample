package com.gabia.internproject.service;

public enum ServiceType {
    HIWORKS{
        @Override
        public UserService getInstance() {
            return new HiworksUserService();
        }
    },
    DATABASE {
        @Override
        public UserService getInstance() {
            return new DataBaseUserService();
        }
    };


    public abstract UserService getInstance();

    }
