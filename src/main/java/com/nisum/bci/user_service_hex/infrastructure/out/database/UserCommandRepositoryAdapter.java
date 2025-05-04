package com.nisum.bci.user_service_hex.infrastructure.out.database;

import com.nisum.bci.user_service_hex.domain.model.PhoneCommand;
import com.nisum.bci.user_service_hex.domain.model.UserCommand;
import com.nisum.bci.user_service_hex.domain.model.UserQuery;
import com.nisum.bci.user_service_hex.domain.repository.UserCommandRepository;
import com.nisum.bci.user_service_hex.infrastructure.components.exceptions.DuplicateDataException;
import com.nisum.bci.user_service_hex.infrastructure.components.jwt.JwtTokenService;
import com.nisum.bci.user_service_hex.infrastructure.out.database.entities.PhoneEntity;
import com.nisum.bci.user_service_hex.infrastructure.out.database.entities.UserEntity;
import com.nisum.bci.user_service_hex.infrastructure.out.database.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.nisum.bci.user_service_hex.infrastructure.components.utils.constants.GeneralField.PHONE_NUMBER_EXISTS;
import static com.nisum.bci.user_service_hex.infrastructure.components.utils.constants.GeneralField.USER_EXISTS;

@Repository
@RequiredArgsConstructor
public class UserCommandRepositoryAdapter implements UserCommandRepository {

    private final JpaUserRepository jpaUserRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private static final Logger log = LoggerFactory.getLogger(UserCommandRepositoryAdapter.class);
    @Override
    public UserQuery createUser(UserCommand userCommand) {
        log.info("UserCommandRepositoryAdapter.createUser BEGIN");
        existUser(userCommand);
        UserEntity userEntity = userMapper.toEntity(userCommand);
        userEntity.setPassword(passwordEncoder.encode(userCommand.getPassword()));
        userEntity.setLastLogin(LocalDateTime.now());
        userEntity.setCreated(LocalDateTime.now());
        userEntity.setPhones(setUserPhones(userCommand, userEntity));
        jpaUserRepository.save(userEntity);
        userEntity.setToken(jwtTokenService.generateToken(userEntity.getId()));
        jpaUserRepository.updateToken(userEntity.getToken(), userEntity.getId());
        log.info("UserCommandRepositoryAdapter.createUser END");
        return userMapper.toUserQuery(userEntity);
    }

    @Override
    public Optional<UserQuery> updateUser(UserCommand userCommand) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(int id) {

    }

    private static List<PhoneEntity> setUserPhones(UserCommand userCommand, UserEntity user) {
        return userCommand.getPhones().stream().map(phoneDto -> {
            PhoneEntity phone = new PhoneEntity();
            phone.setNumber(phoneDto.getPhoneNumber());
            phone.setCityCode(phoneDto.getCityCode());
            phone.setCountryCode(phoneDto.getCountryCode());
            phone.setUser(user);
            phone.setCreated(LocalDateTime.now());
            return phone;
        }).toList();
    }

    private void existUser(UserCommand userCommand){
        if(jpaUserRepository.findByEmailAndIsActiveIsTrue(userCommand.getEmail()).isPresent()){
            throw new DuplicateDataException(USER_EXISTS);
        }
        existPhoneNumber(userCommand.getPhones());
    }

    private void existPhoneNumber(List<PhoneCommand> phones) {
        Set<String> uniquePhones = new HashSet<>();
        for (PhoneCommand phone : phones) {
            if (!uniquePhones.add(phone.getPhoneNumber())) {
                throw new DuplicateDataException(PHONE_NUMBER_EXISTS);
            }
        }
    }
}
