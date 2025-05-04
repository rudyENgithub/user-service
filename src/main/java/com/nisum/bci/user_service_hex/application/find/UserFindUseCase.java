package com.nisum.bci.user_service_hex.application.find;

import com.nisum.bci.user_service_hex.domain.model.UserQuery;
import com.nisum.bci.user_service_hex.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFindUseCase {

    private final UserQueryRepository userQueryRepository;
    public List<UserQuery> findAllUsers(){

        return this.userQueryRepository.findAllUsers();
    }

    public UserQuery findById(UUID id){
        return this.userQueryRepository.findById(id).orElseThrow();
    }

    public UserQuery findAllUserByEmail(String email){
        return this.userQueryRepository.findByEmail(email).orElseThrow();
    }
}
