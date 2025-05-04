package com.nisum.bci.user_service_hex.domain.repository;

import com.nisum.bci.user_service_hex.domain.model.UserCommand;
import com.nisum.bci.user_service_hex.domain.model.UserQuery;

import java.util.Optional;

public interface UserCommandRepository {
    UserQuery createUser(UserCommand userCommand);
    Optional<UserQuery> updateUser(UserCommand userCommand);
    void deleteUser(int id);
}
