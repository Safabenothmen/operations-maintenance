package tn.OperationsMaintenance.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.OperationsMaintenance.entity.Intervention;
import tn.OperationsMaintenance.service.InterventionService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")  

@RestController
@RequestMapping("/intervention")
public class InterventionController {
private InterventionService interventionService;

public InterventionController(InterventionService interventionService) {
	this.interventionService = interventionService;
}


//ajout intervention
@PostMapping("/ajouter/{equipementId}/{technicienId}")
public ResponseEntity<Intervention> ajouterIntervention(
        @PathVariable int equipementId,
        @PathVariable int technicienId,
        @RequestBody Intervention intervention
) {
    Intervention nouvelleIntervention = interventionService.ajouterIntervention(equipementId, technicienId, intervention);
    return ResponseEntity.ok(nouvelleIntervention);
}



/*
//ajout intervention 
@PostMapping("/ajout/{technicienId}/{equipementId}")
public ResponseEntity<Intervention> ajoutIntervention(@RequestBody Intervention intervention, @PathVariable int technicienId,@PathVariable int equipementId) {
    //TODO: process POST request
    
    return ResponseEntity.ok(interventionService.ajoutIntervention(intervention,technicienId,equipementId));
}*/
/*

@PutMapping("/modfifer")
public Intervention updateIntervention(
        @RequestBody Intervention intervention,
        @RequestHeader("X-User-Id") int userId) {
    
    return interventionService.updateIntervention(intervention, userId);
}*/

/*@DeleteMapping("/{id}")
public Intervention deleteIntervention(@PathVariable int id) {
	interventionService.deleteIntervention(id);
}

@GetMapping("/getall")
public List<Intervention> getAllInterventions() {
    return interventionService.getAllInterventions();
}
@GetMapping("/techniciens/{technicienId}")
public List<Intervention> getInterventionsByTechnicien(@PathVariable int technicienId) {
    return interventionService.getInterventionsByTechnicien(technicienId);
}

@GetMapping("/techniciens/{equipementId}")
public List<Intervention> getInterventionsByEquipement(@PathVariable int equipementId) {
    return interventionService.getInterventionsByTechnicien(equipementId);
}

*/
}
