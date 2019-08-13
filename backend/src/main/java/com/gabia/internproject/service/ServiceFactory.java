package com.gabia.internproject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class ServiceFactory {




     public static UserService getType(ServiceType type){

        System.out.println(type+"===))))");
return type.getInstance();

//         switch (type) {
//
//             case HIWORKS:
//                    return new HiworksService();
//
//
//
//
//             case DATABASE:
//
//                return new DataBaseService();
//
//
//         }
//
//return null;

    }
}
