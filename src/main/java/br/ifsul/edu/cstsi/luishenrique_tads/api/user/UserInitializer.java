package br.ifsul.edu.cstsi.luishenrique_tads.api.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserInitializer(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist
        if (repository.count() == 0) {
            // Create a regular user
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRole("USER");
            repository.save(user);

            // Create an admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            repository.save(admin);

            System.out.println("Users initialized successfully");
        }
    }
}