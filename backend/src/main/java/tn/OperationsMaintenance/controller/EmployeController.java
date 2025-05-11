package tn.OperationsMaintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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


}
