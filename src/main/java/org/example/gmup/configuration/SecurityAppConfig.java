package org.example.gmup.configuration;

import lombok.RequiredArgsConstructor;
import org.example.gmup.adapter.outbound.security.SecurityUserDetailServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityAppConfig {

    private final SecurityUserDetailServiceAdapter securityUserDetailServiceAdapter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(securityUserDetailServiceAdapter);
        return authProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // غیرفعال برای APIها
                .authorizeHttpRequests(auth -> auth
                        // مسیرهای عمومی
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html" , "/auth" , "/").permitAll()
                        .requestMatchers("/users/signUp").permitAll() // حتماً اینجا اضافه کن!
                        .requestMatchers("/login").permitAll()       // مسیر لاگین باز باشد

                        // بقیه مسیرها نیاز به احراز هویت دارند
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                );

        return http.build();
    }

}
