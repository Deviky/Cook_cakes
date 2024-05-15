package com.idm.Cook_cakes.Security;



import com.idm.Cook_cakes.Services.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/addPost").authenticated()
                                .requestMatchers("/updatePost").authenticated()
                                .requestMatchers("/profile").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(loginConfigurer ->
                        loginConfigurer
                                .loginPage("/auth") // Своя страница входа
                                .loginProcessingUrl("/auth/submitlog") // Изменил адрес для обработки логина
                                .defaultSuccessUrl("/profile", true) // URL при успешной аутентификации
                                .failureUrl("/auth") // URL при ошибке аутентификации
                                .permitAll()
                )
                .logout(logoutConfigurer ->
                        logoutConfigurer
                                .logoutSuccessUrl("/") // URL при успешном выходе
                                .permitAll()
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                );

        return http.build();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
