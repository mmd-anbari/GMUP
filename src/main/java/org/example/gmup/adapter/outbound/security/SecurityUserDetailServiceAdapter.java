package org.example.gmup.adapter.outbound.security;

import lombok.RequiredArgsConstructor;
import org.example.gmup.core.domain.User;
import org.example.gmup.port.outbound.user.UserInformationPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.builder;

@Component
@RequiredArgsConstructor
public class SecurityUserDetailServiceAdapter implements UserDetailsService {

    private final UserInformationPort userInformationPort;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userInformationPort.getUserInformation(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("user by username " + username + " not found !");
        return UserSecurity.
                builder().
                 id(user.get().getId()).
                username(user.get().getUsername()).
                password(user.get().getPassword()).
                build();

    }
}
