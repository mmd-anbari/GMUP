package org.example.gmup.port.inbound.user;

import org.example.gmup.core.model.dto.user.UserSignupDto;

public interface UserLoginUC {
    void login(UserSignupDto userSignupDto);
}
