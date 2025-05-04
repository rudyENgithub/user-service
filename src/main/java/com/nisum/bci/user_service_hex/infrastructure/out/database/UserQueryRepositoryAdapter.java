package com.nisum.bci.user_service_hex.infrastructure.out.database;

import com.nisum.bci.user_service_hex.domain.model.UserQuery;
import com.nisum.bci.user_service_hex.domain.repository.UserQueryRepository;
import com.nisum.bci.user_service_hex.infrastructure.out.database.entities.UserEntity;
import com.nisum.bci.user_service_hex.infrastructure.out.database.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryAdapter implements UserQueryRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserQueryRepositoryAdapter.class);
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserQuery> findById(UUID id) {
        log.info("UserQueryRepositoryAdapter.findById BEGIN");
        Optional<UserEntity> userEntity = jpaUserRepository.findById(id);

        if(userEntity.isEmpty()) {
            return Optional.empty();
        }

        UserQuery userQuery = userMapper.toUserQuery(userEntity.get());
        log.info("UserQueryRepositoryAdapter.findById BEGIN");
        return Optional.of(userQuery);
    }

    @Override
    public Optional<UserQuery> findByEmail(String email) {
        log.info("UserQueryRepositoryAdapter.findByEmail BEGIN");
        Optional<UserEntity> userEntity = jpaUserRepository.findByEmailAndIsActiveIsTrue(email);

        if(userEntity.isEmpty()) {
            return Optional.empty();
        }

        UserQuery userQuery = userMapper.toUserQuery(userEntity.get());
        log.info("UserQueryRepositoryAdapter.findByEmail BEGIN");
        return Optional.of(userQuery);
    }


    @Override
    public List<UserQuery> findAllUsers() {
        return List.of();
    }


}
