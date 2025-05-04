package com.nisum.bci.user_service_hex.application.create;

import com.nisum.bci.user_service_hex.domain.model.UserCommand;
import com.nisum.bci.user_service_hex.domain.model.UserQuery;
import com.nisum.bci.user_service_hex.domain.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateUseCase {
    private final UserCommandRepository userQueryRepository;


    public UserQuery createUser(UserCommand userCommand){

        return userQueryRepository.createUser(userCommand);
    }

    public UserQuery updateUser(){
        return null;
    }

    public void deleteUser(String uuid){
    }


}
