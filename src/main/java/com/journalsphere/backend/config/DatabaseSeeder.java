package com.journalsphere.backend.config;

import com.journalsphere.backend.model.User;
import com.journalsphere.backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(UserService userService){
        return args -> {
            if (userService.getAll().isEmpty()){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User admin = new User();
                admin.setUsername("Test Admin");
                admin.setEmail("admin@journalsphere.test");
                admin.setPassword(encoder.encode("Admin#123"));
                userService.save(admin);

                IO.println("✅ Default admin user created:");
                IO.println("Email: admin@journalsphere.com");
                IO.println("Password: Admin#123");

                User user = new User();
                user.setUsername("John Doe");
                user.setEmail("user@journalsphere.test");
                user.setPassword(encoder.encode("User#123"));
                userService.save(user);

                IO.println("✅ Default second test user created:");
                IO.println("Email: user@journalsphere.com");
                IO.println("Password: User#123");
            }
        };
    }
}
