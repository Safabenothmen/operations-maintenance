package tn.OperationsMaintenance.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tn.OperationsMaintenance.repository.UserRepository;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.entity.Admin;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("safabenothmen26@gmail.com") == null) {
                Admin admin = new Admin();
                admin.setNom("Safa ben othmen");
                admin.setEmail("safabenothmen26@gmail.com");
                admin.setMotDePasse("safa@safa"); // À hasher si tu as Spring Security
                admin.setRole(User.Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin créé avec succès !");
            }
        };
    }
}
