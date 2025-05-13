package tn.OperationsMaintenance.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import tn.OperationsMaintenance.entity.Equipement;
import tn.OperationsMaintenance.entity.Intervention;
import tn.OperationsMaintenance.entity.Technicien;
import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.entity.User.Role;
import tn.OperationsMaintenance.repository.EquipementRepository;
import tn.OperationsMaintenance.repository.InterventionRepository;
import tn.OperationsMaintenance.repository.TechnicienRepository;
//import tn.OperationsMaintenance.repository.UserRepository;
import tn.OperationsMaintenance.repository.UserRepository;

@Service
public class InterventionService {
    
    private final InterventionRepository interventionRepository;
   // private final TechnicienRepository technicienRepository;
    private final UserRepository userRepository;
    private final EquipementRepository equipementRepository;

    @Autowired
    public InterventionService(InterventionRepository interventionRepository, 
                            UserRepository userRepository,
                             EquipementRepository equipementRepository
                             ) {
        this.interventionRepository = interventionRepository;
        this.userRepository  = userRepository;
        this.equipementRepository = equipementRepository;
    }
    
    
    public Intervention createIntervention(int technicienId, int equipementId, Intervention intervention) {
        User user = userRepository.findById(technicienId)
            .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
        
        // Vérification manuelle du rôle
        if (user.getRole() != Role.TECHNICIEN) {
            throw new IllegalArgumentException("L'utilisateur doit être un technicien");
        }
        
        Equipement equipement = equipementRepository.findById(equipementId)
            .orElseThrow(() -> new EntityNotFoundException("Équipement non trouvé"));
        
        intervention.setTechnicien(user);
        intervention.setEquipement(equipement);
        
        return interventionRepository.save(intervention);
    }



    public List<Intervention> getInterventionsByTechnicien(int technicienId) {
        return interventionRepository.findByTechnicienId(technicienId); 
    }

    //modifier intervention 
    
    public Intervention modifierIntervention(int interventionId, Intervention interventionModifs) {
        return interventionRepository.findById(interventionId)
            .map(interventionExistante -> {
                // Mettre à jour uniquement les champs non-nuls de interventionModifs
                if (interventionModifs.getTechnicien() != null) {
                    interventionExistante.setTechnicien(interventionModifs.getTechnicien());
                }
                
                if (interventionModifs.getEquipement() != null) {
                    interventionExistante.setEquipement(interventionModifs.getEquipement());
                }
                
                if (interventionModifs.getStatut() != null) {
                    interventionExistante.setStatut(interventionModifs.getStatut());
                }
                
                if (interventionModifs.getDate() != null) {
                    interventionExistante.setDate(interventionModifs.getDate());
                }
                
                if (interventionModifs.getCout() != 0) { // ou != null si cout est un Float/Double
                    interventionExistante.setCout(interventionModifs.getCout());
                }
                
                return interventionRepository.save(interventionExistante);
            })
            .orElse(null); // Retourne null si l'intervention n'existe pas
    }
    public void deleteIntervention(int id) {
        if (!interventionRepository.existsById(id)) {
            throw new EntityNotFoundException("Intervention introuvable avec l'id : " + id);
        }
        interventionRepository.deleteById(id);
    }

    public List<Intervention> getAllInterventions() {
        return interventionRepository.findAll();
    }
    public Intervention modifierStatut(int id, String nouveauStatut) {
        try {
            Intervention.Statut statutEnum = Intervention.Statut.valueOf(nouveauStatut);

            return interventionRepository.findById(id)
                    .map(intervention -> {
                        intervention.setStatut(statutEnum);
                        return interventionRepository.save(intervention);
                    })
                    .orElse(null);
        } catch (IllegalArgumentException e) {
            return null; // statut invalide
        }
    }




}



  
