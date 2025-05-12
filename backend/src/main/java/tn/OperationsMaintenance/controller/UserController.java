package tn.OperationsMaintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        // Cherche l'utilisateur par email
        User user = userRepository.findByEmail(loginRequest.getEmail());

        // Vérifie que l'utilisateur existe et que le mot de passe est correct
        if (user != null && user.getMotDePasse().equals(loginRequest.getMotDePasse())) {
            // Créer une réponse JSON avec le rôle et un message
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Connexion réussie");
            response.put("UserId", user.getId());
            response.put("role", user.getRole().toString()); // Retourne le rôle de l'utilisateur
            response.put("nom", user.getNom()); // Ajoute le nom de l'utilisateur (si nécessaire)
            
            return ResponseEntity.ok(response);  // Retourne une réponse 200 OK avec les données
        } else {
            // Si l'authentification échoue
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Email ou mot de passe incorrect");
            return ResponseEntity.status(401).body(errorResponse);  // Retourne une réponse 401 Unauthorized
        }
    }

    // Classe interne pour le login request
    public static class LoginRequest {
        private String email;
        private String motDePasse;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMotDePasse() {
            return motDePasse;
        }

        public void setMotDePasse(String motDePasse) {
            this.motDePasse = motDePasse;
        }
    }
}
