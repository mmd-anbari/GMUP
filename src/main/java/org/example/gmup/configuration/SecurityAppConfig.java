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
                .csrf(csrf -> csrf.disable()) // غیرفعال برای APIها (برای شروع کار خوبه)
                .authorizeHttpRequests(auth -> auth
                        // مسیرهای عمومی که همه دسترسی دارند
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/auth",           // صفحه ورود اختصاصی شما
                                "/",               // صفحه اصلی سایت (ایندکس)
                                "/users/signUp",   // API ثبت نام
                                "/login",          // API ورود
                                "/public/**"       // <--- این مسیر تمام ارورهای سرچ و دانلود فایل را حل می‌کند
                        ).permitAll()

                        // بقیه مسیرها (مثل /dashboard) نیاز به احراز هویت دارند
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth") // استفاده از صفحه لاگین اختصاصی خودت به جای صفحه پیش‌فرض اسپرینگ
                        .loginProcessingUrl("/login") // آدرسی که فرم لاگین دیتا رو بهش می‌فرسته (POST)
                        .defaultSuccessUrl("/dashboard", true) // بعد از لاگین موفق بره داشبورد
                );

        return http.build();
    }

}
