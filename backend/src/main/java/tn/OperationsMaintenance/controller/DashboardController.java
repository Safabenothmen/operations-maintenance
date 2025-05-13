package tn.OperationsMaintenance.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.OperationsMaintenance.entity.Intervention;
import tn.OperationsMaintenance.repository.EquipementRepository;
import tn.OperationsMaintenance.repository.InterventionRepository;
import tn.OperationsMaintenance.repository.PanneRepository;
@CrossOrigin(origins = "http://localhost:4200")  

@RestController
@RequestMapping("/api")
public class DashboardController {
@Autowired
private PanneRepository panneRepository;
@Autowired
private InterventionRepository interventionRepository;
@Autowired
private EquipementRepository equipementRepository;
@GetMapping("/dashboard-stats")
public Map<String, Object> getStats() {
    Map<String, Object> stats = new HashMap<>();

    stats.put("totalPannes", panneRepository.count());

    // Pannes en attente = celles qui n'ont pas encore d'intervention ou interventions non terminées
    stats.put("pannesEnAttente", panneRepository.countPannesEnAttente());

    // Pannes résolues = interventions terminées
    stats.put("pannesResolues", interventionRepository.countByStatut(Intervention.Statut.Terminee));

    stats.put("totalEquipements", equipementRepository.count());

    return stats;
}
}
