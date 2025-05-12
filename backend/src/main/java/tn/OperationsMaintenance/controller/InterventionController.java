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

@GetMapping("/technicien/{technicienId}")
public ResponseEntity<List<Intervention>> getInterventionsByTechnicien(@PathVariable int technicienId) {
    List<Intervention> interventions = interventionService.getInterventionsByTechnicien(technicienId);
    if (interventions.isEmpty()) {
        return ResponseEntity.noContent().build(); // Renvoie un 204 No Content si aucune intervention n'est trouvée
    }
    return ResponseEntity.ok(interventions); // Renvoie un 200 OK avec la liste des interventions
}
@PutMapping("/modifier/{interventionId}")
public ResponseEntity<Intervention> modifierIntervention(
        @PathVariable int interventionId,
        @RequestBody Intervention intervention
) {
    // Appel de la méthode dans le service pour mettre à jour l'intervention
    Intervention interventionModifiee = interventionService.modifierIntervention(interventionId, intervention);
    
    if (interventionModifiee == null) {
        return ResponseEntity.notFound().build(); // Si l'intervention n'a pas été trouvée
    }
    
    return ResponseEntity.ok(interventionModifiee); // Renvoie l'intervention mise à jour
}

@GetMapping("/all")
public ResponseEntity<List<Intervention>> getAllInterventions() {
    List<Intervention> interventions = interventionService.getAllInterventions();
    if (interventions.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(interventions);
}

@DeleteMapping("/supprimer/{id}")
public ResponseEntity<Void> deleteIntervention(@PathVariable int id) {
    interventionService.deleteIntervention(id);
    return ResponseEntity.noContent().build(); // 204 No Content
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
