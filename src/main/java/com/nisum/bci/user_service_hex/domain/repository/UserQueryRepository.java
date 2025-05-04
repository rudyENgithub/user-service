package com.nisum.bci.user_service_hex.domain.repository;

import com.nisum.bci.user_service_hex.domain.model.UserCommand;
import com.nisum.bci.user_service_hex.domain.model.UserQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserQueryRepository {
    Optional<UserQuery> findById(UUID id);
    Optional<UserQuery> findByEmail(String email);
    List<UserQuery> findAllUsers();
}
