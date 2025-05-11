package tn.OperationsMaintenance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import tn.OperationsMaintenance.entity.Equipement;
import tn.OperationsMaintenance.entity.Intervention;
import tn.OperationsMaintenance.entity.Technicien;
//*import tn.OperationsMaintenance.entity.User;
import tn.OperationsMaintenance.repository.EquipementRepository;
import tn.OperationsMaintenance.repository.InterventionRepository;
import tn.OperationsMaintenance.repository.TechnicienRepository;
//import tn.OperationsMaintenance.repository.UserRepository;

@Service
public class InterventionService {
    
    private final InterventionRepository interventionRepository;
    private final TechnicienRepository technicienRepository;
    private final EquipementRepository equipementRepository;

    @Autowired
    public InterventionService(InterventionRepository interventionRepository, 
                             TechnicienRepository technicienRepository,
                             EquipementRepository equipementRepository
                             ) {
        this.interventionRepository = interventionRepository;
        this.technicienRepository = technicienRepository;
        this.equipementRepository = equipementRepository;
    }
    
    
    public Intervention ajouterIntervention(int equipementId, int technicienId, Intervention intervention) {
        Optional<Equipement> equipement = equipementRepository.findById(equipementId);
        Optional<Technicien> technicien = technicienRepository.findById(technicienId);

        if (equipement.isPresent() && technicien.isPresent()) {
            intervention.setEquipement(equipement.get());
            intervention.setTechnicien(technicien.get());
            return interventionRepository.save(intervention);
        } else {
            throw new RuntimeException("Équipement ou Technicien non trouvé.");
        }
    }
    
    
    
}



  /*  public Intervention ajoutIntervention(Intervention intervention, int technicienId, int equipementId) {
        Technicien technicien = technicienRepository.findById(technicienId);
        if (technicien == null) {
            throw new EntityNotFoundException("Technicien non trouvé");
        }
        intervention.setTechnicien(technicien);
        
       Equipement equipement = equipementRepository.findById(equipementId);
        if (equipement == null) {
            throw new EntityNotFoundException("Équipement non trouvé");
        }
        intervention.setEquipement(equipement);
        
        return interventionRepository.save(intervention);
    }
    
    
    
    public Intervention updateIntervention(Intervention nvIntervention, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));

        Intervention existIntervention = interventionRepository.findById(nvIntervention.getId())
                .orElseThrow(() -> new EntityNotFoundException("Intervention non trouvée"));

        if ("Admin".equals(user.getRole())) {
            if (nvIntervention.getDate() != null) {
                existIntervention.setDate(nvIntervention.getDate());
            }
            if (nvIntervention.getCout() != 0) {
                existIntervention.setCout(nvIntervention.getCout());
            }
        }

        if (nvIntervention.getStatut() != null) {
            existIntervention.setStatut(nvIntervention.getStatut());
        }

        return interventionRepository.save(existIntervention);
    }
*/