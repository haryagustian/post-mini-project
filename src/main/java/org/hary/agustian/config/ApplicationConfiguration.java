package org.hary.agustian.config;

import lombok.extern.slf4j.Slf4j;
import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class ApplicationConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        log.info("CALL::BEAN::UserDetailsService");
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("USER NOT FOUND: (BEAN CONFIG)"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("CALL::BEAN::PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        log.info("CALL::BEAN::AuthenticationProvider");
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        log.info("CALL::BEAN::AuthenticationManager");
        return authenticationConfiguration.getAuthenticationManager();
    }
}
