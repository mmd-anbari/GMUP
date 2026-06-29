package org.example.gmup.port.inbound.user;

import org.example.gmup.core.dto.SaveNewUserCommand;

public interface SignUpUC  {

    public void signUp(SaveNewUserCommand command);

}
