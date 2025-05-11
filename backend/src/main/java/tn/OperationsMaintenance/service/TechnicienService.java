package tn.OperationsMaintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.OperationsMaintenance.entity.Technicien;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.entity.User.Role;
//import tn.OperationsMaintenance.entity.User.Role;
import tn.OperationsMaintenance.repository.TechnicienRepository;
import tn.OperationsMaintenance.repository.UserRepository;
@Service
public class TechnicienService {
private TechnicienRepository technicienRepository;
@Autowired

private UserRepository userRepository;


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
 public Technicien modifierTechnicien(int id ,Technicien newTechnicien) {
	 
     Technicien technicien = technicienRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Technicien non trouvé"));
			 technicien.setCompetences(newTechnicien.getCompetences());
			 technicien.setDisponibilite(newTechnicien.getDisponibilite());
		        return technicienRepository.save(technicien);

 }
 

}
