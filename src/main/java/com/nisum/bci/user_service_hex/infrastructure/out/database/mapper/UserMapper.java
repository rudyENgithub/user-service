package com.nisum.bci.user_service_hex.infrastructure.out.database.mapper;

import com.nisum.bci.user_service_hex.domain.model.UserCommand;
import com.nisum.bci.user_service_hex.domain.model.UserQuery;
import com.nisum.bci.user_service_hex.infrastructure.out.database.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    public UserQuery toUserQuery(UserEntity entity){
        return mapper.map(entity, UserQuery.class);
    }

    public UserEntity toEntity(UserCommand user){
        return mapper.map(user, UserEntity.class);
    }

}