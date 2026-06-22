package org.example.gmup.core.service.User;

import org.example.gmup.core.model.User;
import org.example.gmup.core.model.dto.user.UserLoginDto;
import org.example.gmup.mapper.UserMapper;
import org.example.gmup.port.inbound.user.UserLoginUC;

public class UserLoginServiceImpl implements UserLoginUC {

    private UserMapper userMapper;

    @Override
    public void login(UserLoginDto userLoginDto) {

       User user = userMapper.toModel(userLoginDto);

    }
}
