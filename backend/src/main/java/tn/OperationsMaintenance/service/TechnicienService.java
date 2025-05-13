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
public Intervention modifierIntervention(int id, Intervention interventionModifiee) {
    Intervention interventionExistante = interventionRepository.findById(id).orElse(null);
    if (interventionExistante == null) {
        return null;
    }

    // Mise à jour des champs modifiables
    interventionExistante.setDate(interventionModifiee.getDate());
    interventionExistante.setCout(interventionModifiee.getCout());
    interventionExistante.setStatut(interventionModifiee.getStatut());
    interventionExistante.setEquipement(interventionModifiee.getEquipement());
    interventionExistante.setTechnicien(interventionModifiee.getTechnicien());

    return interventionRepository.save(interventionExistante);
}



public User modifierTechnicien(int id, Technicien updatedTech) {
    // Récupérer l'utilisateur avec l'ID donné
    User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

    // Vérifier si l'utilisateur a bien le rôle TECHNICIEN
    if (existingUser.getRole() == Role.TECHNICIEN) {
        // Mettre à jour uniquement les champs non nuls dans le JSON
        if (updatedTech.getNom() != null) {
            existingUser.setNom(updatedTech.getNom());
        }
        if (updatedTech.getEmail() != null) {
            existingUser.setEmail(updatedTech.getEmail());
        }
        if (updatedTech.getMotDePasse() != null) {
            existingUser.setMotDePasse(updatedTech.getMotDePasse());
        }

        // Si l'utilisateur est un technicien, mettre à jour ses informations spécifiques
        if (existingUser instanceof Technicien) {
            Technicien technicien = (Technicien) existingUser;

            // Mise à jour de la disponibilité si présente dans le JSON
            if (updatedTech.getDisponibilite() != null) {
                technicien.setDisponibilite(updatedTech.getDisponibilite());
            }

            // Mettre à jour les compétences seulement si elles sont fournies
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


 


