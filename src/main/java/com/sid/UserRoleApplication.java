package com.sid;

import com.sid.entities.AppRole;
import com.sid.entities.AppUser;
import com.sid.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class UserRoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRoleApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {

        return args -> {
            userService.saveRole(new AppRole(null, "USER"));
            userService.saveRole(new AppRole(null, "ADMIN"));
            Stream.of("user1", "user2", "user3", "admin").forEach(name -> {
                userService.saveUser(name, "1234", "1234");
            });
        };


    }

    @Bean
    BCryptPasswordEncoder getBCPE() {

        return new BCryptPasswordEncoder();
    }
}
