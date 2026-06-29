package org.example.gmup.configuration;

import org.example.gmup.adapter.outbound.security.SecurityUserDetailServiceAdapter;
import org.example.gmup.port.outbound.user.UserInformationPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig  {

    @Bean
    public UserDetailsService userDetailsService(UserInformationPort userInformationPort) {
        return new SecurityUserDetailServiceAdapter(userInformationPort);
    }





}
