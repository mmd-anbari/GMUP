package org.example.gmup.port.inbound.user;

import org.example.gmup.core.domain.User;

public interface GetUserProfileUC {

    User getUserProfile(long id);

}
