package tn.OperationsMaintenance.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.OperationsMaintenance.entity.Equipement;
import tn.OperationsMaintenance.entity.Panne;
import tn.OperationsMaintenance.service.EquipementService;
import tn.OperationsMaintenance.service.PanneService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "http://localhost:4200")  


@RestController
@RequestMapping("/equipement")
public class EquipementController {
	private EquipementService equipementService;
	
	 public EquipementController(EquipementService equipementService) {
		 this.equipementService=equipementService;
	 }

	 //ajout equipement 
	
	 
@PostMapping("/ajout")
public ResponseEntity<Equipement> ajouterEquipement(@RequestBody Equipement equipement) {
    return ResponseEntity.ok(equipementService.ajouterEquipement(equipement));
    }


/*

	 @PutMapping("modifier/{id}")
	 public Equipement modifierEquipement(@PathVariable int id, @RequestBody Equipement newEquipement) {
	 	//TODO: process PUT request
	 	
	 	return equipementService.modifierEquipement(id, newEquipement);
	 }*/
	 
	 @GetMapping("/all")
	 public List<Equipement> getAllEq() {  
	     return equipementService.getAllEq();
	 }

	 
	 
}
