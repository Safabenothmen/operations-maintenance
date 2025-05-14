package tn.OperationsMaintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.OperationsMaintenance.entity.Intervention;
import tn.OperationsMaintenance.entity.Technicien;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.entity.User.Role;
import tn.OperationsMaintenance.repository.InterventionRepository;
//import tn.OperationsMaintenance.entity.User.Role;
import tn.OperationsMaintenance.repository.TechnicienRepository;
import tn.OperationsMaintenance.repository.UserRepository;
@Service
public class TechnicienService {
private TechnicienRepository technicienRepository;
@Autowired

private UserRepository userRepository;
private InterventionRepository interventionRepository;
 
//ajout technicien 

public Technicien ajouterTechnicien(Technicien technicien) {
    technicien.setRole(User.Role.TECHNICIEN); 
    return userRepository.save(technicien);
}



//get techncien 

public List<User> getAllTechniciens() {
	System.out.println("ROLE ++++ "+ User.Role.TECHNICIEN);
	return userRepository.findByRole(User.Role.TECHNICIEN);
}


//supprimer technicien 
public User suprimerTechnicien(int id) {
    Optional<User> technicien = userRepository.findById(id);
    if (technicien.isPresent()) {
        User t = technicien.get();
        userRepository.deleteById(id);
        return t;
    }
    return null; 
}
public List<Technicien> getTechniciensDisponibles(){
    return technicienRepository.findByDisponibiliteTrue();
}


//modifier techncien




public User modifierTechnicien(int id, Technicien updatedTech) {
    User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    // Vérifier si l'utilisateur a bien le rôle TECHNICIEN
    if (existingUser.getRole() == Role.TECHNICIEN) {
        if (updatedTech.getNom() != null) {
            existingUser.setNom(updatedTech.getNom());
        }
        if (updatedTech.getEmail() != null) {
            existingUser.setEmail(updatedTech.getEmail());
        }
        if (updatedTech.getMotDePasse() != null) {
            existingUser.setMotDePasse(updatedTech.getMotDePasse());
        }

        if (existingUser instanceof Technicien) {
            Technicien technicien = (Technicien) existingUser;

            if (updatedTech.getDisponibilite() != null) {
                technicien.setDisponibilite(updatedTech.getDisponibilite());
            }

            if (updatedTech.getCompetences() != null) {
                technicien.setCompetences(updatedTech.getCompetences());
            }
        }

        // Sauvegarder les modifications dans la base de données
        return userRepository.save(existingUser);
    } else {
        throw new RuntimeException("Cet utilisateur n'est pas un technicien");
    }
}
}


 


