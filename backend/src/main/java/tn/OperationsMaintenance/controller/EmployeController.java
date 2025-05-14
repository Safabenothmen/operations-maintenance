package tn.OperationsMaintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.OperationsMaintenance.entity.Employe;
import tn.OperationsMaintenance.entity.Technicien;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.service.EmployeService;

@CrossOrigin(origins = "http://localhost:4200")  

@RestController

public class EmployeController {
	@Autowired
	private EmployeService employeService;

	public EmployeController(EmployeService employeService) {
		this.employeService = employeService;
	}
	
	@PostMapping("employe/ajout")
	public ResponseEntity<User> ajouterEmploye(@RequestBody Employe employe) {
	    User saved = employeService.ajouterEmploye(employe);
	    return ResponseEntity.ok(saved);
	}
	@GetMapping("/employe")
	public ResponseEntity<List<User>> getAllEmployes() {
	    List<User> employe = employeService.getAllEmployes();
	    return ResponseEntity.ok(employe);
	}

	@PutMapping("/modifierEmploye/{id}")
	public ResponseEntity<Employe> modifierEmploye(@PathVariable int id, @RequestBody Employe updatedEmploye) {
	    Employe employeModifie = employeService.modifierEmploye(id, updatedEmploye);
	    return ResponseEntity.ok(employeModifie);
	}
	@DeleteMapping("/supprimerEmploye/{id}")
	public User supprimerTechnicien(@PathVariable int id) {
	    return employeService.suprimerEmploye(id);
	}

}
