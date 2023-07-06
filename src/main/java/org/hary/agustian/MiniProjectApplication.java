package org.hary.agustian;

import lombok.extern.slf4j.Slf4j;
import org.hary.agustian.enums.Role;
import org.hary.agustian.enums.Sex;
import org.hary.agustian.model.auth.AuthenticationResponse;
import org.hary.agustian.model.user.UserRequest;
import org.hary.agustian.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class MiniProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiniProjectApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService authenticationService
    ) {
        return args -> {
            UserRequest userRequestAdmin = new UserRequest(
                    "admin",
                    "admin",
                    "admin@email.org",
                    "admin",
                    Sex.FEMALE,
                    "123456789123",
                    "Jl. admin",
                    Role.ADMIN);
            AuthenticationResponse registerAdmin = authenticationService.register(userRequestAdmin);
            log.info("ADMIN::ACCESS-TOKEN : {}\nADMIN::REFRESH-TOKEN l {}", registerAdmin.getAccessToken(), registerAdmin.getRefreshToken());

            UserRequest userRequestManager = new UserRequest(
                    "manager",
                    "manager",
                    "manager@email.org",
                    "manager",
                    Sex.FEMALE,
                    "321987654321",
                    "Jl. manager",
                    Role.MANAGER);
            AuthenticationResponse registerManager = authenticationService.register(userRequestManager);
            log.info("MANAGER::ACCESS-TOKEN : {}\nMANAGER::REFRESH-TOKEN l {}", registerManager.getAccessToken(), registerManager.getRefreshToken());
        };
    }

}
