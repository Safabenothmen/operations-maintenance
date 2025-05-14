package tn.OperationsMaintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.OperationsMaintenance.entity.Employe;
import tn.OperationsMaintenance.entity.Technicien;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.repository.UserRepository;
@Service
public class EmployeService {
	@Autowired
	private UserRepository userRepository;
	
	
	//ajout employe 

public Employe ajouterEmploye(Employe employe) {
    employe.setRole(User.Role.EMPLOYE); 
    return userRepository.save(employe);
}
//liste d'mploye
public List<User> getAllEmployes() {
	System.out.println("ROLE ++++ "+ User.Role.EMPLOYE);
	return userRepository.findByRole(User.Role.EMPLOYE);
}
//modifier employe
public Employe modifierEmploye(int id, Employe updatedEmploye) {
    Employe existingEmploye = (Employe) userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

    if (updatedEmploye.getNom() != null) {
        existingEmploye.setNom(updatedEmploye.getNom());
    }
    if (updatedEmploye.getEmail() != null) {
        existingEmploye.setEmail(updatedEmploye.getEmail());
    }
    if (updatedEmploye.getMotDePasse() != null) {
        existingEmploye.setMotDePasse(updatedEmploye.getMotDePasse());
    }

    if (updatedEmploye.getDateEmbauche() != null) {
        existingEmploye.setDateEmbauche(updatedEmploye.getDateEmbauche());
    }

    return userRepository.save(existingEmploye);
}
public User suprimerEmploye(int id) {
    Optional<User> employe = userRepository.findById(id);
    if (employe.isPresent()) {
        User t = employe.get();
        userRepository.deleteById(id);
        return t;
    }
    return null; 
}
}
