package org.example.gmup.core.service.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gmup.core.domain.User;
import org.example.gmup.core.domain.exception.UserNameAlreadyExistsException;
import org.example.gmup.core.dto.SaveNewUserCommand;
import org.example.gmup.port.inbound.user.SignUpUC;
import org.example.gmup.port.outbound.user.UserInformationPort;
import org.example.gmup.port.outbound.user.UserSignUpPort;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpService implements SignUpUC {

    private UserSignUpPort userSignUpPort;
    private UserInformationPort userInformationPort;


    @Override
    public void signUp(SaveNewUserCommand command) {
        if(userInformationPort.userNameExists(command.username())){
            throw new UserNameAlreadyExistsException("username by "+ command.username()+ " already exists");
        }
        User user = new User(command.firstname() , command.lastname(), command.username(), command.password());
        userSignUpPort.signUp(user);
    }
}
