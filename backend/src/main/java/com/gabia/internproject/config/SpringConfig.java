package com.gabia.internproject.config;

import com.gabia.internproject.service.ServiceFactory;
import com.gabia.internproject.service.ServiceType;
import com.gabia.internproject.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserService setService() {
       // return ServiceType.HIWORKS.getInstance();
        return ServiceFactory.getType(ServiceType.HIWORKS);
    }

}







