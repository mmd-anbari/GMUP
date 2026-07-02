package org.example.gmup.core.service.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gmup.core.domain.User;
import org.example.gmup.core.domain.exception.UserNotFoundException;
import org.example.gmup.port.inbound.user.GetUserProfileUC;
import org.example.gmup.port.outbound.user.UserInformationPort;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileService implements GetUserProfileUC {

    private UserInformationPort userInformationPort;

    @Override
    public User getUserProfile(long id) {
        Optional<User> user = userInformationPort.getUserInformation(id);
        return user.orElseThrow(
                ()-> new UserNotFoundException("user by id "+ id +"not found !")
        );
    }
}
