package org.example.gmup.port;

import org.example.gmup.core.domain.User;

public interface SecurityContext {

    User getCurrentUser();

}
