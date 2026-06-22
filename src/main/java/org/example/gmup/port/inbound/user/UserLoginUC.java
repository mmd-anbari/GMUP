package org.example.gmup.port.inbound.user;

import org.example.gmup.core.model.dto.user.UserLoginDto;

public interface UserLoginUC {
    void login(UserLoginDto userLoginDto);
}
