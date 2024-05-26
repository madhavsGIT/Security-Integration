package com.acciojob.Security.Integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails user = User.withUsername("Samson")
                .password(passwordEncoder.encode("Samson@123"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("Madhav")
                .password(passwordEncoder.encode("Madhav@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection if necessary
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/helloAll").permitAll() // Allow all access to /helloAll
                        .requestMatchers("/helloAdmin", "/helloUser").authenticated() // Require authentication for /helloAdmin and /helloUser
                )
                .formLogin(formLogin -> formLogin // Configure form-based login with lambda
                        .loginPage("/login") // Specify a custom login page
                        .permitAll()
                );

        return httpSecurity.build();
    }
}
