package tn.OperationsMaintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.OperationsMaintenance.entity.Technicien;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.service.TechnicienService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@CrossOrigin(origins = "http://localhost:4200")  

@RestController


public class TechnicienController {
    @Autowired

private TechnicienService technicienService;

public TechnicienController(TechnicienService technicienService) {
	this.technicienService = technicienService;
}

@PostMapping("technicien/ajout")


public ResponseEntity<User> ajouterTechnicien(@RequestBody Technicien technicien) {
    User saved = technicienService.ajouterTechnicien(technicien);
    return ResponseEntity.ok(saved);
}



//get all techniciens
@GetMapping("/techniciens")
public ResponseEntity<List<User>> getAllTechniciens() {
    List<User> techniciens = technicienService.getAllTechniciens();
    return ResponseEntity.ok(techniciens);
}


//supprimer by id 
@DeleteMapping("/supprimerTechnicien/{id}")
public User supprimerTechnicien(@PathVariable int id) {
    return technicienService.suprimerTechnicien(id);
}

@GetMapping("/technciens/{id}")
public String getMethodName(@RequestParam String param) {
    return new String();
}

@GetMapping("/techniciens/disponibles")
public ResponseEntity<List<Technicien>> getTechniciensDisponibles() {
    return ResponseEntity.ok(technicienService.getTechniciensDisponibles());
}
//modifieTechncien
@PutMapping("/modifierTechnicien/{id}")
public ResponseEntity<Technicien> modifierTechnicien(@PathVariable int id, @RequestBody Technicien updatedTech) {
    Technicien utilisateurModifie = (Technicien) technicienService.modifierTechnicien(id, updatedTech);
    return ResponseEntity.ok(utilisateurModifie);
}





}


