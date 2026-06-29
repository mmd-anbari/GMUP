package org.example.gmup.adapter.outbound.user;

import lombok.RequiredArgsConstructor;
import org.example.gmup.adapter.outbound.entity.UserEntity;
import org.example.gmup.adapter.outbound.jpa.UserRepositoryJpa;
import org.example.gmup.core.domain.User;
import org.example.gmup.mapper.UserMapper;
import org.example.gmup.port.outbound.user.UserInformationPort;
import org.example.gmup.port.outbound.user.UserSignUpPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserSignUpPort , UserInformationPort  {

    private final UserRepositoryJpa userRepositoryJpa;
    private final UserMapper userMapper;


    @Override
    public void signUp(User user) {

        UserEntity userEntity = userMapper.toUserEntityFromUser(user);

        userRepositoryJpa.save(userEntity);
    }

    @Override
    public Optional<User> getUserInformation(String username) {
        Optional<UserEntity> userEntity = userRepositoryJpa.findUserEntityByUsername(username);
        return userEntity.map(userMapper::toDomainFromUserEntity);
    }

    @Override
    public boolean userNameExists(String userName) {
        return userRepositoryJpa.existsByUsername(userName);
    }

    public Optional<UserEntity> loadUserByUsername(String username) {
        return userRepositoryJpa.findUserEntityByUsername(username);
    }

}
