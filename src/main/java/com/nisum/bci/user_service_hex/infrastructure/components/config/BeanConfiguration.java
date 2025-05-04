package com.nisum.bci.user_service_hex.infrastructure.components.config;

import com.nisum.bci.user_service_hex.infrastructure.out.database.mapper.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public UserMapper productMapper(){
        return new UserMapper();
    }

}
