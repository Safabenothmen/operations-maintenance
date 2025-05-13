package tn.OperationsMaintenance.service;

import java.util.List;

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
}
