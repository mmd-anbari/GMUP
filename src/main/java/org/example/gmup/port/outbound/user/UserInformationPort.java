package org.example.gmup.port.outbound.user;

import org.example.gmup.core.domain.User;

import java.util.Optional;

public interface UserInformationPort {

    Optional<User> getUserInformation(String userName);

    boolean userNameExists(String userName);

}
